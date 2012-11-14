package co.edu.unicesi.sami.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the materias database table.
 * 
 */
@Entity
@Table(name="materias")
public class Materia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigo;

	private int creditos;

	private String descripcion;

	private String nombre;

	private String semestre;

	//bi-directional many-to-many association to Bloque
	@ManyToMany(mappedBy="materias")
	private List<Bloque> bloques;

	//bi-directional many-to-one association to Material
	@OneToMany(mappedBy="materia")
	private List<Material> materiales;

	//bi-directional many-to-one association to MateriaCompetenciaEspecifica
	@OneToMany(mappedBy="materia")
	private List<MateriaCompetenciaEspecifica> materiasCompetenciasespecificas;

	//bi-directional many-to-one association to ObjetivoGeneral
	@OneToMany(mappedBy="materia")
	private List<ObjetivoGeneral> objetivosGenerales;

	//bi-directional many-to-one association to ObjetivoTerminal
	@OneToMany(mappedBy="materia")
	private List<ObjetivoTerminal> objetivosTerminales;

	//bi-directional many-to-one association to Unidad
	@OneToMany(mappedBy="materia")
	private List<Unidad> unidades;

    public Materia() {
    }

	public int getCodigo() {
		return this.codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCreditos() {
		return this.creditos;
	}

	public void setCreditos(int creditos) {
		this.creditos = creditos;
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

	public String getSemestre() {
		return this.semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public List<Bloque> getBloques() {
		return this.bloques;
	}

	public void setBloques(List<Bloque> bloques) {
		this.bloques = bloques;
	}
	
	public List<Material> getMateriales() {
		return this.materiales;
	}

	public void setMateriales(List<Material> materiales) {
		this.materiales = materiales;
	}
	
	public List<MateriaCompetenciaEspecifica> getMateriasCompetenciasespecificas() {
		return this.materiasCompetenciasespecificas;
	}

	public void setMateriasCompetenciasespecificas(List<MateriaCompetenciaEspecifica> materiasCompetenciasespecificas) {
		this.materiasCompetenciasespecificas = materiasCompetenciasespecificas;
	}
	
	public List<ObjetivoGeneral> getObjetivosGenerales() {
		return this.objetivosGenerales;
	}

	public void setObjetivosGenerales(List<ObjetivoGeneral> objetivosGenerales) {
		this.objetivosGenerales = objetivosGenerales;
	}
	
	public List<ObjetivoTerminal> getObjetivosTerminales() {
		return this.objetivosTerminales;
	}

	public void setObjetivosTerminales(List<ObjetivoTerminal> objetivosTerminales) {
		this.objetivosTerminales = objetivosTerminales;
	}
	
	public List<Unidad> getUnidades() {
		return this.unidades;
	}

	public void setUnidades(List<Unidad> unidades) {
		this.unidades = unidades;
	}
	
}