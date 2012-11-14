package co.edu.unicesi.sami.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the competenciasgenerales database table.
 * 
 */
@Entity
@Table(name="competenciasgenerales")
public class CompetenciaGeneral implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String descripción;

	private String nombre;

	//bi-directional many-to-many association to Programa
    @ManyToMany
	@JoinTable(
		name="competenciasgenerales_programas"
		, joinColumns={
			@JoinColumn(name="FK_IdCompetenciaGeneral")
			}
		, inverseJoinColumns={
			@JoinColumn(name="FK_CodigoPrograma")
			}
		)
	private List<Programa> programas;

	//bi-directional many-to-one association to CompetenciaTerminal
	@OneToMany(mappedBy="competenciasgenerale")
	private List<CompetenciaTerminal> competenciasterminales;

    public CompetenciaGeneral() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripción() {
		return this.descripción;
	}

	public void setDescripción(String descripción) {
		this.descripción = descripción;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Programa> getProgramas() {
		return this.programas;
	}

	public void setProgramas(List<Programa> programas) {
		this.programas = programas;
	}
	
	public List<CompetenciaTerminal> getCompetenciasterminales() {
		return this.competenciasterminales;
	}

	public void setCompetenciasterminales(List<CompetenciaTerminal> competenciasterminales) {
		this.competenciasterminales = competenciasterminales;
	}
	
}