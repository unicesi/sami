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
	private int codigo;

	private String descripcion;

	private String nombre;

	//bi-directional many-to-one association to Bloque
	@OneToMany(mappedBy="programa")
	private List<Bloque> bloques;

	//bi-directional many-to-many association to CompetenciaEspecifica
	@ManyToMany(mappedBy="programas")
	private List<CompetenciaEspecifica> competenciasespecificas;

	//bi-directional many-to-many association to CompetenciaGeneral
	@ManyToMany(mappedBy="programas")
	private List<CompetenciaGeneral> competenciasgenerales;

	//bi-directional many-to-many association to CompetenciaTerminal
	@ManyToMany(mappedBy="programas")
	private List<CompetenciaTerminal> competenciasterminales;

	//bi-directional many-to-many association to Usuario
	@ManyToMany(mappedBy="programas")
	private List<Usuario> usuarios;

    public Programa() {
    }

	public int getCodigo() {
		return this.codigo;
	}

	public void setCodigo(int codigo) {
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
	
	public List<CompetenciaEspecifica> getCompetenciasespecificas() {
		return this.competenciasespecificas;
	}

	public void setCompetenciasespecificas(List<CompetenciaEspecifica> competenciasespecificas) {
		this.competenciasespecificas = competenciasespecificas;
	}
	
	public List<CompetenciaGeneral> getCompetenciasgenerales() {
		return this.competenciasgenerales;
	}

	public void setCompetenciasgenerales(List<CompetenciaGeneral> competenciasgenerales) {
		this.competenciasgenerales = competenciasgenerales;
	}
	
	public List<CompetenciaTerminal> getCompetenciasterminales() {
		return this.competenciasterminales;
	}

	public void setCompetenciasterminales(List<CompetenciaTerminal> competenciasterminales) {
		this.competenciasterminales = competenciasterminales;
	}
	
	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
}