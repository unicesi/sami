package co.edu.unicesi.sami.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the programas database table.
 * 
 */
@Entity
@Table(name="programas")
public class Programa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String codigo;

	private String descripcion;

	private String nombre;

	//bi-directional many-to-one association to Bloque
	@OneToMany(mappedBy="programa")
	private List<Bloque> bloques;

	//bi-directional many-to-one association to CompetenciaEspecificaPrograma
	@OneToMany(mappedBy="programa")
	private List<CompetenciaEspecificaPrograma> competenciasespecificasProgramas;

	//bi-directional many-to-many association to CompetenciaGeneral
	@ManyToMany(mappedBy="programas")
	private List<CompetenciaGeneral> competenciasgenerales;

	//bi-directional many-to-one association to CompetenciaTerminalPrograma
	@OneToMany(mappedBy="programa")
	private List<CompetenciaTerminalPrograma> competenciasterminalesProgramas;

    public Programa() {
    }

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	public List<Bloque> getBloques() {
		return this.bloques;
	}

	public void setBloques(List<Bloque> bloques) {
		this.bloques = bloques;
	}
	
	public List<CompetenciaEspecificaPrograma> getCompetenciasespecificasProgramas() {
		return this.competenciasespecificasProgramas;
	}

	public void setCompetenciasespecificasProgramas(List<CompetenciaEspecificaPrograma> competenciasespecificasProgramas) {
		this.competenciasespecificasProgramas = competenciasespecificasProgramas;
	}
	
	public List<CompetenciaGeneral> getCompetenciasgenerales() {
		return this.competenciasgenerales;
	}

	public void setCompetenciasgenerales(List<CompetenciaGeneral> competenciasgenerales) {
		this.competenciasgenerales = competenciasgenerales;
	}
	
	public List<CompetenciaTerminalPrograma> getCompetenciasterminalesProgramas() {
		return this.competenciasterminalesProgramas;
	}

	public void setCompetenciasterminalesProgramas(List<CompetenciaTerminalPrograma> competenciasterminalesProgramas) {
		this.competenciasterminalesProgramas = competenciasterminalesProgramas;
	}
	
}