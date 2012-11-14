package co.edu.unicesi.sami.client.curso;

import java.util.List;

import co.edu.unicesi.sami.bo.MateriaBO;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("CursoService")
public interface CursoService extends RemoteService
{
    public int agregarCurso(MateriaBO curso);
    public int editarCurso(MateriaBO curso);
    public MateriaBO buscarCurso(int idCurso);
    public List<MateriaBO> listarCursos();
}
