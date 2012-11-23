package co.edu.unicesi.sami.server;


import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

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
import co.edu.unicesi.sami.client.listados.ListadosService;
import co.edu.unicesi.sami.listados.GestionListadosRemote;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ListadosServiceImpl extends RemoteServiceServlet implements ListadosService
{
    
    private InitialContext context;
    private GestionListadosRemote listadosService;
    
    public ListadosServiceImpl( )
    {
        try
        {
            context = new InitialContext( );
            listadosService = (GestionListadosRemote) context.lookup( "java:global/SAMI-EAR/SAMI-EJB/GestionListadosBean!co.edu.unicesi.sami.listados.GestionListadosRemote" );
        }
        catch( NamingException e )
        {
            e.printStackTrace( );
        }
    }
    
    @Override
    public List<MaterialBO> listarMaterialesPorCurso( String codigoCurso )
    {
        return listadosService.listarMaterialesPorCurso( codigoCurso );
    }

    @Override
    public List<MetaTerminalBO> listarMetasTerminalesPorCurso( String codigoCurso )
    {
        return listadosService.listarMetasTerminalesPorCurso( codigoCurso );
    }

    @Override
    public List<ObjetivoEspecificoBO> listarObjEspecificosPorCurso( String codigoCurso )
    {
        return listadosService.listarObjEspecificosPorCurso( codigoCurso );
    }

    @Override
    public List<ObjetivoEspecificoBO> listarObjEspecificosPorMetaTerminal( int idMetaTerminal )
    {
        return listadosService.listarObjEspecificosPorMetaTerminal( idMetaTerminal );
    }

    @Override
    public List<ObjetivoEspecificoBO> listarObjEspecificosPorUnidad( int idUnidad )
    {
        return listadosService.listarObjEspecificosPorUnidad( idUnidad );
    }
    
    

    @Override
    public List<ObjetivoTerminalBO> listarObjTerminalesPorCurso( String codigoCurso )
    {
        return listadosService.listarObjTerminalesPorCurso( codigoCurso );
    }

    @Override
    public List<RecursoBO> listarRecursosPorSaber( int idSaber )
    {
        return listadosService.listarRecursosPorSaber( idSaber );
    }

    @Override
    public List<SaberBO> listarSaberesPorCurso(String codigoCurso)
    {
        return listadosService.listarSaberesPorCurso( codigoCurso );
    }
    
    @Override
    public List<SaberBO> listarSaberesPorObjetivoEspecifico( int idObjEspecifico )
    {
        return listadosService.listarSaberesPorObjetivoEspecifico( idObjEspecifico );
    }

    @Override
    public List<SesionBO> listarSesionesPorUnidad( int idUnidad )
    {
        return listadosService.listarSesionesPorUnidad( idUnidad );
    }

    @Override
    public List<TrabajoAsignadoBO> listarTrabajosAsignadosPorSesion( int idSesion )
    {
        return listadosService.listarTrabajosAsignadosPorSesion( idSesion );
    }

    @Override
    public List<RecursoAsignadoBO> listarRecursosAsignadosPorTrabajoAsignado( int idTrabajoAsignado )
    {
        return listadosService.listarRecursosAsignadosPorTrabajoAsignado( idTrabajoAsignado );
    }

    @Override
    public List<UnidadBO> listarUnidadesPorCurso( String codigoCurso )
    {
        return listadosService.listarUnidadesPorCurso( codigoCurso );
    }

	@Override
	public List<ObjetivoTerminalBO> listarObjTerminalesPorUnidad(int idUnidad) {
		return listadosService.listarObjTerminalesPorUnidad(idUnidad);
	}

	@Override
	public List<ObjetivoTerminalBO> listarObjTerminalesCursoMenosUnidad(
			String idCurso, int idUnidad) {
		return listadosService.listarObjTerminalesCursoMenosUnidad(idCurso, idUnidad);
	}

	@Override
	public List<RecursoBO> listarRecursosPorCurso(String codigoCurso) {
		// TODO Auto-generated method stub
		return listadosService.listarRecursosPorCurso(codigoCurso);
	}

	@Override
	public List<CompetenciaBO> listarCompetencias() {
		// TODO Auto-generated method stub
		return listadosService.listarCompetencias();
	}

}