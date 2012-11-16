package co.edu.unicesi.sami.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the materiales database table.
 * 
 */
@Entity
@Table(name="materiales")
public class Material implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String ano;

	private String autor;

	private String ciudad;

	private String editorial;

	private String fuente;

	private String idioma;

	private String titulo;

	//bi-directional many-to-one association to Materia
    @ManyToOne
	@JoinColumn(name="FK_CodigoMateria")
	private Materia materia;

	//bi-directional many-to-one association to Recurso
	@OneToMany(mappedBy="materiale")
	private List<Recurso> recursos;

    public Material() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAno() {
		return this.ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getAutor() {
		return this.autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getEditorial() {
		return this.editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public String getFuente() {
		return this.fuente;
	}

	public void setFuente(String fuente) {
		this.fuente = fuente;
	}

	public String getIdioma() {
		return this.idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Materia getMateria() {
		return this.materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}
	
	public List<Recurso> getRecursos() {
		return this.recursos;
	}

	public void setRecursos(List<Recurso> recursos) {
		this.recursos = recursos;
	}
	
}