package co.edu.unicesi.sami.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the competenciasterminales database table.
 * 
 */
@Entity
@Table(name="competenciasterminales")
public class CompetenciaTerminal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String descripcion;

	private String nombre;

	private int tipo;

	//bi-directional many-to-one association to CompetenciaEspecifica
	@OneToMany(mappedBy="competenciasterminale")
	private List<CompetenciaEspecifica> competenciasespecificas;

	//bi-directional many-to-one association to CompetenciaGeneral
    @ManyToOne
	@JoinColumn(name="idCompetenciaGeneral")
	private CompetenciaGeneral competenciasgenerale;

	//bi-directional many-to-many association to Programa
    @ManyToMany
	@JoinTable(
		name="competenciasterminales_programas"
		, joinColumns={
			@JoinColumn(name="FK_IdCompetenciaTerminal")
			}
		, inverseJoinColumns={
			@JoinColumn(name="FK_CodigoPrograma")
			}
		)
	private List<Programa> programas;

    public CompetenciaTerminal() {
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

	public int getTipo() {
		return this.tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public List<CompetenciaEspecifica> getCompetenciasespecificas() {
		return this.competenciasespecificas;
	}

	public void setCompetenciasespecificas(List<CompetenciaEspecifica> competenciasespecificas) {
		this.competenciasespecificas = competenciasespecificas;
	}
	
	public CompetenciaGeneral getCompetenciasgenerale() {
		return this.competenciasgenerale;
	}

	public void setCompetenciasgenerale(CompetenciaGeneral competenciasgenerale) {
		this.competenciasgenerale = competenciasgenerale;
	}
	
	public List<Programa> getProgramas() {
		return this.programas;
	}

	public void setProgramas(List<Programa> programas) {
		this.programas = programas;
	}
	
}