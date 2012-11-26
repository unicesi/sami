package co.edu.unicesi.sami.competencias;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//import co.edu.unicesi.sami.bo.CEOTBO;
import co.edu.unicesi.sami.bo.CEOTBO;
import co.edu.unicesi.sami.bo.MetaTerminalBO;
import co.edu.unicesi.sami.bo.ObjetivoEspecificoBO;
import co.edu.unicesi.sami.bo.ObjetivoGeneralBO;
import co.edu.unicesi.sami.bo.ObjetivoTerminalBO;
import co.edu.unicesi.sami.bo.UnidadBO;
import co.edu.unicesi.sami.entidades.Materia;
import co.edu.unicesi.sami.entidades.MetaTerminal;
import co.edu.unicesi.sami.entidades.ObjetivoEspecifico;
import co.edu.unicesi.sami.entidades.ObjetivoGeneral;
import co.edu.unicesi.sami.entidades.ObjetivoTerminal;
import co.edu.unicesi.sami.entidades.ObjetivoTerminalCompetenciaEspecifica;
import co.edu.unicesi.sami.entidades.ObjetivoTerminalCompetenciaEspecificaPK;
import co.edu.unicesi.sami.entidades.Saber;
import co.edu.unicesi.sami.entidades.Sesion;
import co.edu.unicesi.sami.entidades.Unidad;

/**
 * Session Bean implementation class GestionCompetenciasBean
 */
@Stateless
public class GestionCompetenciasBean implements GestionCompetenciasRemote,
		GestionCompetenciasLocal {

	@PersistenceContext
	EntityManager em;

	@Override
	public int agregarUnidad(UnidadBO unidad) {
		Unidad entidad = new Unidad();
		int resp = -1;

		try {
			Materia curso = em.find(Materia.class, unidad.getCodigoCurso());
			
			for (int i = 0; i < curso.getUnidades().size(); i++) {
				
				Unidad uniAux = curso.getUnidades().get(i);
				
				if(uniAux.getNombre().equals(unidad.getNombre()) &&
				   uniAux.getNumero() == unidad.getNumero())
				resp = 3;
			}
			
			if(resp != 3)
			{
				entidad.setNumero(unidad.getNumero());
				entidad.setNombre(unidad.getNombre());
				entidad.setContenido(unidad.getContenido());
				entidad.setMateria(curso);
				entidad.setMetasTerminales(new ArrayList<MetaTerminal>());
				entidad.setSesiones(new ArrayList<Sesion>());
				
				em.persist(entidad);
				em.flush();
				resp = 0;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp;
	}

	@Override
	public int editarUnidad(UnidadBO unidad) {
		int resp = -1;
		try {
			Unidad entidad = em.find(Unidad.class, unidad.getId());

			entidad.setNumero(unidad.getNumero());
			entidad.setNombre(unidad.getNombre());
			entidad.setContenido(unidad.getContenido());

			em.merge(entidad);
			em.flush();
			resp = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp;
	}

	@Override
	public UnidadBO buscarUnidad(int idUnidad) {
		Unidad unidad = em.find(Unidad.class, idUnidad);
		em.refresh(unidad);
		UnidadBO bo = new UnidadBO();

		bo.setId(idUnidad);
		bo.setNumero(unidad.getNumero());
		bo.setNombre(unidad.getNombre());
		bo.setContenido(unidad.getContenido());

		bo.setCodigoCurso(unidad.getMateria().getCodigo());
		bo.setNombreCurso(unidad.getMateria().getNombre());

		List<Integer> metasTerminales = new ArrayList<Integer>();
		for (MetaTerminal mt : unidad.getMetasTerminales()) {
			metasTerminales.add(mt.getId());
		}
		bo.setMetasTerminales(metasTerminales);

		List<Integer> sesiones = new ArrayList<Integer>();
		for (Sesion s : unidad.getSesiones()) {
			sesiones.add(s.getId());
		}
		bo.setSesiones(sesiones);

		return bo;
	}

	@Override
	public int agregarObjGeneral(ObjetivoGeneralBO objGeneral) {
		ObjetivoGeneral entidad = new ObjetivoGeneral();
		int resp = -1;

		try {
			Materia curso = em.find(Materia.class, objGeneral.getCodigoCurso());

			entidad.setContenido(objGeneral.getContenido());
			entidad.setMateria(curso);

			em.persist(entidad);
			em.flush();
			resp = 0;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp;
	}

	@Override
	public int editarObjGeneral(ObjetivoGeneralBO objGeneral) {
		int resp = -1;
		try {
			ObjetivoGeneral entidad = em.find(ObjetivoGeneral.class,
					objGeneral.getId());

			entidad.setContenido(objGeneral.getContenido());

			em.merge(entidad);
			em.flush();
			resp = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp;
	}

	@Override
	public ObjetivoGeneralBO buscarObjGeneral(String codigoCurso) {
		try {
			Materia curso = em.find(Materia.class, codigoCurso);
			em.refresh(curso);
			ObjetivoGeneralBO bo = new ObjetivoGeneralBO();

			bo.setId(curso.getObjetivosGenerales().get(0).getId());
			bo.setContenido(curso.getObjetivosGenerales().get(0).getContenido());

			bo.setCodigoCurso(curso.getCodigo());
			bo.setNombreCurso(curso.getNombre());

			return bo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int agregarObjTerminal(ObjetivoTerminalBO objTerminal) {
		ObjetivoTerminal entidad = new ObjetivoTerminal();
		int resp = -1;

		try {
			Materia curso = em
					.find(Materia.class, objTerminal.getCodigoCurso());
			
			for (int i = 0; i < curso.getObjetivosTerminales().size(); i++) {
				
				ObjetivoTerminal otaux = curso.getObjetivosTerminales().get(i);
				
				if(otaux.getNombre().equals(objTerminal.getNombre()))
					resp = 3;
				
			}
			
			if(resp != 3)
			{
				entidad.setNombre(objTerminal.getNombre());
				entidad.setContenido(objTerminal.getContenido());
				entidad.setMateria(curso);
				entidad.setMetasTerminales(new ArrayList<MetaTerminal>());
	
				em.persist(entidad);
				em.flush();
				resp = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp;
	}

	@Override
	public int editarObjTerminal(ObjetivoTerminalBO objTerminal) {
		int resp = -1;
		try {
			ObjetivoTerminal entidad = em.find(ObjetivoTerminal.class,
					objTerminal.getId());

			entidad.setNombre(objTerminal.getNombre());
			entidad.setContenido(objTerminal.getContenido());

			em.merge(entidad);
			em.flush();
			resp = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp;
	}

	@Override
	public ObjetivoTerminalBO buscarObjTerminal(int idObjTerminal) {
		ObjetivoTerminal objTerminal = em.find(ObjetivoTerminal.class,
				idObjTerminal);
		em.refresh(objTerminal);

		ObjetivoTerminalBO bo = new ObjetivoTerminalBO();

		bo.setId(idObjTerminal);
		bo.setNombre(objTerminal.getNombre());
		bo.setContenido(objTerminal.getContenido());

		bo.setCodigoCurso(objTerminal.getMateria().getCodigo());
		bo.setNombreCurso(objTerminal.getMateria().getNombre());

		List<Integer> metasTerminales = new ArrayList<Integer>();
		for (MetaTerminal mt : objTerminal.getMetasTerminales()) {
			metasTerminales.add(mt.getId());
		}
		bo.setMetasTerminales(metasTerminales);

		return bo;
	}

	@Override
	public int agregarObjEspecifico(ObjetivoEspecificoBO objEspecifico) {
		ObjetivoEspecifico entidad = new ObjetivoEspecifico();
		int resp = -1;

		try {
			MetaTerminal metaTerminal = em.find(MetaTerminal.class,
					objEspecifico.getIdMetaTerminal());
			
			for (int i = 0; i < metaTerminal.getObjetivosEspecificos().size(); i++) {
				
				ObjetivoEspecifico oeaux = metaTerminal.getObjetivosEspecificos().get(i);
				
				if(oeaux.getNombre().equals(objEspecifico.getNombre()))
					resp = 3;
				
			}
			
			if(resp != 3)
			{
				entidad.setNombre(objEspecifico.getNombre());
				entidad.setContenido(objEspecifico.getContenido());
				entidad.setMetasTerminale(metaTerminal);
				entidad.setSaberes(new ArrayList<Saber>());
	
				em.persist(entidad);
				em.flush();
				resp = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp;
	}

	@Override
	public int editarObjEspecifico(ObjetivoEspecificoBO objEspecifico) {
		int resp = -1;
		try {
			ObjetivoEspecifico entidad = em.find(ObjetivoEspecifico.class,
					objEspecifico.getId());

			entidad.setNombre(objEspecifico.getNombre());
			entidad.setContenido(objEspecifico.getContenido());

			em.merge(entidad);
			em.flush();
			resp = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp;
	}

	@Override
	public ObjetivoEspecificoBO buscarObjEspecifico(int idObjEspecifico) {
		ObjetivoEspecifico objEspecifico = em.find(ObjetivoEspecifico.class,
				idObjEspecifico);
		em.refresh(objEspecifico);

		ObjetivoEspecificoBO bo = new ObjetivoEspecificoBO();

		bo.setId(idObjEspecifico);
		bo.setNombre(objEspecifico.getNombre());
		bo.setContenido(objEspecifico.getContenido());

		bo.setIdMetaTerminal(objEspecifico.getMetasTerminale().getId());
		bo.setIdObjTerminal(objEspecifico.getMetasTerminale()
				.getObjetivosTerminale().getId());
		bo.setIdUnidad(objEspecifico.getMetasTerminale().getUnidade().getId());

		List<Integer> saberes = new ArrayList<Integer>();
		for (Saber s : objEspecifico.getSaberes()) {
			saberes.add(s.getId());
		}
		bo.setSaberes(saberes);

		return bo;
	}

	@Override
	public int agregarMetaTerminal(MetaTerminalBO metaTerminal) {
		MetaTerminal entidad = new MetaTerminal();
		int resp = -1;
		try {
			Unidad unidad = em.find(Unidad.class, metaTerminal.getIdUnidad());
			ObjetivoTerminal objTerminal = em.find(ObjetivoTerminal.class,
					metaTerminal.getIdObjTerminal());

			entidad.setObjetivosTerminale(objTerminal);
			entidad.setUnidade(unidad);
			entidad.setObjetivosEspecificos(new ArrayList<ObjetivoEspecifico>());

			em.persist(entidad);
			em.flush();
			resp = 0;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp;
	}

	@Override
	public int eliminarMetaTerminal(MetaTerminalBO metaTerminal) {
		int resp = -1;
		try {
			MetaTerminal entidad = em.find(MetaTerminal.class,
					metaTerminal.getId());

			em.remove(entidad);
			em.flush();
			resp = 2;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp;
	}

	@Override
	public MetaTerminalBO buscarMetaTerminal(int idMetaTerminal) {
		MetaTerminal metaTerminal = em.find(MetaTerminal.class, idMetaTerminal);
		em.refresh(metaTerminal);

		MetaTerminalBO bo = new MetaTerminalBO();

		bo.setId(idMetaTerminal);

		bo.setIdUnidad(metaTerminal.getUnidade().getId());
		bo.setNumeroUnidad(metaTerminal.getUnidade().getNumero());
		bo.setNombreUnidad(metaTerminal.getUnidade().getNombre());
		bo.setContenidoUnidad(metaTerminal.getUnidade().getContenido());

		bo.setIdObjTerminal(metaTerminal.getObjetivosTerminale().getId());
		bo.setNombreObjTerminal(metaTerminal.getObjetivosTerminale()
				.getNombre());
		bo.setContenidoObjTerminal(metaTerminal.getObjetivosTerminale()
				.getContenido());

		List<Integer> objEspecificos = new ArrayList<Integer>();
		for (ObjetivoEspecifico oe : metaTerminal.getObjetivosEspecificos()) {
			objEspecificos.add(oe.getId());
		}
		bo.setObjEspecificos(objEspecificos);

		return bo;
	}
	
	@Override
	public int agregarCEOT(CEOTBO ceot) {
		ObjetivoTerminalCompetenciaEspecifica entidad = new ObjetivoTerminalCompetenciaEspecifica();
		int resp = 0;
		try {
			ObjetivoTerminal ot = em.find(ObjetivoTerminal.class, ceot.getIdObjTerminal());

			for (int i = 0; i < ot.getObjetivosterminalesCompetenciasespecificas().size(); i++) {
				
				ObjetivoTerminalCompetenciaEspecifica compAux = ot.getObjetivosterminalesCompetenciasespecificas().get(i);
				
				if(compAux.getIntroduce().equals(ceot.getIntroduce()))
					resp = -1;
			}
			
			if(resp != -1)
			{
			entidad.setObjetivosTerminale(ot);
			ObjetivoTerminalCompetenciaEspecificaPK otcepk=new ObjetivoTerminalCompetenciaEspecificaPK();
			otcepk.setFK_IdCompetenciaEspecifica(ceot.getId());
			otcepk.setFK_IdObjetivoTerminal(ceot.getIdObjTerminal());
			otcepk.setFK_CodigoPrograma("01");
			entidad.setId(otcepk);
			entidad.setIntroduce(ceot.getIntroduce());
			entidad.setEnseña(ceot.getEnsena());
			entidad.setAplica(ceot.getAplica());

			em.persist(entidad);
			em.flush();
			resp = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp;
	}

}
