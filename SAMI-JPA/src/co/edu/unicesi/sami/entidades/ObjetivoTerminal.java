package co.edu.unicesi.sami.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the objetivos_terminales database table.
 * 
 */
@Entity
@Table(name="objetivos_terminales")
public class ObjetivoTerminal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String contenido;

	private String nombre;

	//bi-directional many-to-one association to MetaTerminal
	@OneToMany(mappedBy="objetivosTerminale")
	private List<MetaTerminal> metasTerminales;

	//bi-directional many-to-one association to Materia
    @ManyToOne
	@JoinColumn(name="FK_CodigoMateria")
	private Materia materia;

	//bi-directional many-to-one association to ObjetivoTerminalCompetenciaEspecifica
	@OneToMany(mappedBy="objetivosTerminale")
	private List<ObjetivoTerminalCompetenciaEspecifica> objetivosterminalesCompetenciasespecificas;

    public ObjetivoTerminal() {
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<MetaTerminal> getMetasTerminales() {
		return this.metasTerminales;
	}

	public void setMetasTerminales(List<MetaTerminal> metasTerminales) {
		this.metasTerminales = metasTerminales;
	}
	
	public Materia getMateria() {
		return this.materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}
	
	public List<ObjetivoTerminalCompetenciaEspecifica> getObjetivosterminalesCompetenciasespecificas() {
		return this.objetivosterminalesCompetenciasespecificas;
	}

	public void setObjetivosterminalesCompetenciasespecificas(List<ObjetivoTerminalCompetenciaEspecifica> objetivosterminalesCompetenciasespecificas) {
		this.objetivosterminalesCompetenciasespecificas = objetivosterminalesCompetenciasespecificas;
	}
	
}