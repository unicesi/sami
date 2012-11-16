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

	//bi-directional many-to-one association to CompetenciaEspecificaPrograma
	@OneToMany(mappedBy="competenciasespecifica")
	private List<CompetenciaEspecificaPrograma> competenciasespecificasProgramas;

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
	
	public List<CompetenciaEspecificaPrograma> getCompetenciasespecificasProgramas() {
		return this.competenciasespecificasProgramas;
	}

	public void setCompetenciasespecificasProgramas(List<CompetenciaEspecificaPrograma> competenciasespecificasProgramas) {
		this.competenciasespecificasProgramas = competenciasespecificasProgramas;
	}
	
}