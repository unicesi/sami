package co.edu.unicesi.sami.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the objetivos_generales database table.
 * 
 */
@Entity
@Table(name="objetivos_generales")
public class ObjetivoGeneral implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String contenido;

	//bi-directional many-to-one association to Materia
    @ManyToOne
	@JoinColumn(name="materias_codigo")
	private Materia materia;

    public ObjetivoGeneral() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContenido() {
		return this.contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Materia getMateria() {
		return this.materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}
	
}