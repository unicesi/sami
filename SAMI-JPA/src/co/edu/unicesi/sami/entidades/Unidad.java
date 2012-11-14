package co.edu.unicesi.sami.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the unidades database table.
 * 
 */
@Entity
@Table(name="unidades")
public class Unidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String contenido;

	private String nombre;

	private int numero;

	//bi-directional many-to-one association to Sesion
	@OneToMany(mappedBy="unidade")
	private List<Sesion> sesiones;

	//bi-directional many-to-one association to Materia
    @ManyToOne
	@JoinColumn(name="materias_codigo")
	private Materia materia;

	//bi-directional many-to-one association to MetaTerminal
	@OneToMany(mappedBy="unidade")
	private List<MetaTerminal> metasTerminales;

    public Unidad() {
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

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public List<Sesion> getSesiones() {
		return this.sesiones;
	}

	public void setSesiones(List<Sesion> sesiones) {
		this.sesiones = sesiones;
	}
	
	public Materia getMateria() {
		return this.materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}
	
	public List<MetaTerminal> getMetasTerminales() {
		return this.metasTerminales;
	}

	public void setMetasTerminales(List<MetaTerminal> metasTerminales) {
		this.metasTerminales = metasTerminales;
	}
	
}