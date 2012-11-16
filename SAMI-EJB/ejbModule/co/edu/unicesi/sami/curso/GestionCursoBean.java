package co.edu.unicesi.sami.curso;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.unicesi.sami.bo.MateriaBO;
import co.edu.unicesi.sami.bo.MetaTerminalBO;
import co.edu.unicesi.sami.entidades.Materia;
import co.edu.unicesi.sami.entidades.Material;
import co.edu.unicesi.sami.entidades.MetaTerminal;
import co.edu.unicesi.sami.entidades.ObjetivoGeneral;
import co.edu.unicesi.sami.entidades.ObjetivoTerminal;
import co.edu.unicesi.sami.entidades.Saber;
import co.edu.unicesi.sami.entidades.Unidad;

/**
 * Session Bean implementation class GestionCursoBean
 */
@Stateless
public class GestionCursoBean implements GestionCursoRemote, GestionCursoLocal {

	@PersistenceContext
	EntityManager em;

	@Override
	public int agregarCurso(MateriaBO curso) {
		Materia entidad = new Materia();
		int resp = -1;
		try {
			entidad.setCodigo(curso.getCodigo());
			entidad.setNombre(curso.getNombre());
			entidad.setCreditos(curso.getCreditos());
			entidad.setDescripcion(curso.getDescripcion());
			entidad.setUnidades(new ArrayList<Unidad>());
			entidad.setObjetivosTerminales(new ArrayList<ObjetivoTerminal>());

			em.persist(entidad);
			em.flush();
			resp = 0;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp;

	}

	@Override
	public int editarCurso(MateriaBO curso) {
		int resp = -1;
		try {
			Materia entidad = em.find(Materia.class, curso.getCodigo());

			entidad.setNombre(curso.getNombre());
			entidad.setCodigo(curso.getCodigo());
			entidad.setCreditos(curso.getCreditos());
			entidad.setDescripcion(curso.getDescripcion());

			em.merge(entidad);
			em.flush();
			resp = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp;
	}

	@Override
	public MateriaBO buscarCurso(int idCurso) {
		Materia curso = em.find(Materia.class, idCurso);
		em.refresh(curso);

		MateriaBO bo = new MateriaBO();

		bo.setCodigo(curso.getCodigo());
		bo.setNombre(curso.getNombre());
		bo.setDescripcion(curso.getDescripcion());
		bo.setCreditos(curso.getCreditos());
		bo.setContenidoObjGeneral(curso.getObjetivosGenerales().get(0)
				.getContenido());

		List<Integer> unidades = new ArrayList<Integer>();
		for (Unidad u : curso.getUnidades()) {
			unidades.add(u.getId());
		}
		bo.setUnidades(unidades);

		List<Integer> objTerminales = new ArrayList<Integer>();
		for (ObjetivoTerminal ot : curso.getObjetivosTerminales()) {
			objTerminales.add(ot.getId());
		}
		bo.setObjTerminales(objTerminales);

		List<Integer> materiales = new ArrayList<Integer>();
		for (Material m : curso.getMateriales()) {
			materiales.add(m.getId());
		}
		bo.setMateriales(materiales);

		return bo;
	}

	@Override
	public List<MateriaBO> listarCursos() {
		String query = "SELECT c FROM Materia c";
		Query q = em.createQuery(query);
		List<Materia> cursos = q.getResultList();

		List<MateriaBO> cursosBO = new ArrayList<MateriaBO>();

		for (Materia c : cursos) {
			MateriaBO bo = new MateriaBO();
			bo.setCodigo(c.getCodigo());
			bo.setNombre(c.getNombre());
			bo.setDescripcion(c.getDescripcion());
			bo.setCreditos(c.getCreditos());

			cursosBO.add(bo);
		}

		return cursosBO;
	}
}
