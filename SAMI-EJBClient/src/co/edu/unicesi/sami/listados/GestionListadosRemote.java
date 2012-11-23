package co.edu.unicesi.sami.listados;

import java.util.List;

import javax.ejb.Remote;

import co.edu.unicesi.sami.bo.CompetenciaBO;
import co.edu.unicesi.sami.bo.MaterialBO;
import co.edu.unicesi.sami.bo.MetaTerminalBO;
import co.edu.unicesi.sami.bo.ObjetivoEspecificoBO;
import co.edu.unicesi.sami.bo.ObjetivoTerminalBO;
import co.edu.unicesi.sami.bo.RecursoAsignadoBO;
import co.edu.unicesi.sami.bo.RecursoBO;
import co.edu.unicesi.sami.bo.SaberBO;
import co.edu.unicesi.sami.bo.SesionBO;
import co.edu.unicesi.sami.bo.TrabajoAsignadoBO;
import co.edu.unicesi.sami.bo.UnidadBO;

@Remote
public interface GestionListadosRemote {
	public List<MaterialBO> listarMaterialesPorCurso(String codigoCurso);

	public List<MetaTerminalBO> listarMetasTerminalesPorCurso(String codigoCurso);

	public List<ObjetivoEspecificoBO> listarObjEspecificosPorCurso(
			String codigoCurso);

	public List<ObjetivoEspecificoBO> listarObjEspecificosPorMetaTerminal(
			int idMetaTerminal);

	public List<ObjetivoEspecificoBO> listarObjEspecificosPorUnidad(int idUnidad);

	public List<ObjetivoTerminalBO> listarObjTerminalesPorUnidad(int idUnidad);

	public List<ObjetivoTerminalBO> listarObjTerminalesCursoMenosUnidad(
			String codigoCurso, int idUnidad);

	public List<ObjetivoTerminalBO> listarObjTerminalesPorCurso(
			String codigoCurso);

	public List<RecursoBO> listarRecursosPorSaber(int idSaber);

	public List<SaberBO> listarSaberesPorCurso(String codigoCurso);

	public List<SaberBO> listarSaberesPorObjetivoEspecifico(int idObjEspecifico);

	public List<SesionBO> listarSesionesPorUnidad(int idUnidad);

	public List<TrabajoAsignadoBO> listarTrabajosAsignadosPorSesion(int idSesion);

	public List<RecursoAsignadoBO> listarRecursosAsignadosPorTrabajoAsignado(
			int idTrabajoAsignado);

	public List<UnidadBO> listarUnidadesPorCurso(String codigoCurso);

	public List<RecursoBO> listarRecursosPorCurso(String codigoCurso);
	public List<CompetenciaBO> listarCompetencias();
}
