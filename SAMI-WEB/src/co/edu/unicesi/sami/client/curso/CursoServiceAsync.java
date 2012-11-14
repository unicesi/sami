package co.edu.unicesi.sami.client.curso;

import java.util.List;

import co.edu.unicesi.sami.bo.MateriaBO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CursoServiceAsync
{
    public void agregarCurso(MateriaBO curso, AsyncCallback<Integer> callback);
    public void editarCurso(MateriaBO curso, AsyncCallback<Integer> callback);
    public void buscarCurso(int idCurso, AsyncCallback<MateriaBO> callback);
    public void listarCursos(AsyncCallback<List<MateriaBO>> callback);
}
