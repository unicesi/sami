package co.edu.unicesi.sami.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the competenciasespecificas database table.
 * 
 */
@Entity
@Table(name="competenciasespecificas")
public class CompetenciaEspecifica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String descripcion;

	private String nombre;

	//bi-directional many-to-one association to CompetenciaTerminal
    @ManyToOne
	@JoinColumn(name="idCompetenciaTerminal")
	private CompetenciaTerminal competenciasterminale;

	//bi-directional many-to-many association to Programa
    @ManyToMany
	@JoinTable(
		name="competenciasespecificas_programas"
		, joinColumns={
			@JoinColumn(name="FK_idCompetenciaEspecifica")
			}
		, inverseJoinColumns={
			@JoinColumn(name="FK_CodigoPrograma")
			}
		)
	private List<Programa> programas;

    public CompetenciaEspecifica() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public CompetenciaTerminal getCompetenciasterminale() {
		return this.competenciasterminale;
	}

	public void setCompetenciasterminale(CompetenciaTerminal competenciasterminale) {
		this.competenciasterminale = competenciasterminale;
	}
	
	public List<Programa> getProgramas() {
		return this.programas;
	}

	public void setProgramas(List<Programa> programas) {
		this.programas = programas;
	}
	
}