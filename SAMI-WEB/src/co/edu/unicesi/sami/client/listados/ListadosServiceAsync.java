package co.edu.unicesi.sami.client.listados;

import java.util.List;

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

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ListadosServiceAsync
{
    public void listarMaterialesPorCurso(String codigoCurso, AsyncCallback<List<MaterialBO>> callback);
    public void listarMetasTerminalesPorCurso(String codigoCurso, AsyncCallback<List<MetaTerminalBO>> callback);
    public void listarObjEspecificosPorCurso(String codigoCurso, AsyncCallback<List<ObjetivoEspecificoBO>> callback);
    public void listarObjEspecificosPorMetaTerminal(int idMetaTerminal, AsyncCallback<List<ObjetivoEspecificoBO>> callback);    
    public void listarObjEspecificosPorUnidad(int idUnidad, AsyncCallback<List<ObjetivoEspecificoBO>> callback);
    public void listarObjTerminalesPorCurso(String codigoCurso, AsyncCallback<List<ObjetivoTerminalBO>> callback);
    public void listarObjTerminalesPorUnidad(int idUnidad, AsyncCallback<List<ObjetivoTerminalBO>> callback);
    public void listarObjTerminalesCursoMenosUnidad(String codigoCurso, int idUnidad, AsyncCallback<List<ObjetivoTerminalBO>> callback);
    public void listarRecursosPorSaber(int idSaber, AsyncCallback<List<RecursoBO>> callback);
    public void listarSaberesPorCurso(String codigoCurso, AsyncCallback<List<SaberBO>> callback);
    public void listarSaberesPorObjetivoEspecifico(int idObjEspecifico, AsyncCallback<List<SaberBO>> callback);
    public void listarSesionesPorUnidad(int idUnidad, AsyncCallback<List<SesionBO>> callback);
    public void listarTrabajosAsignadosPorSesion(int idSesion, AsyncCallback<List<TrabajoAsignadoBO>> callback);
    public void listarRecursosAsignadosPorTrabajoAsignado(int idTrabajoAsignado, AsyncCallback<List<RecursoAsignadoBO>> callback);
    public void listarUnidadesPorCurso(String codigoCurso, AsyncCallback<List<UnidadBO>> callback);
    public void listarRecursosPorCurso(String codigoCurso, AsyncCallback<List<RecursoBO>> callback);
}
