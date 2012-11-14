package co.edu.unicesi.sami.curso;

import java.util.List;
import javax.ejb.Remote;
import co.edu.unicesi.sami.bo.MateriaBO;

@Remote
public interface GestionCursoRemote {
	public int agregarCurso(MateriaBO curso);
    public int editarCurso(MateriaBO curso);
    public MateriaBO buscarCurso(int idCurso);
    public List<MateriaBO> listarCursos();
}
