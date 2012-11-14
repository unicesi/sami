package co.edu.unicesi.sami.bo;

import java.io.Serializable;
import java.util.List;

public class MaterialBO implements Serializable
{
    private int id;
    private String fuente;
	private String idioma;
    private String autor;
    private String titulo;
    private String ano;
    private String ciudad;
    private String editorial;
    private int idCurso;
    private int codigoCurso;
    private String nombreCurso;    
    private List<Integer> recursos;    

	private static final long serialVersionUID = 1L;
    
    public MaterialBO()
    {
        
    }

    public int getId( )
    {
        return id;
    }

    public String getFuente() {
		return fuente;
	}

	public void setFuente(String fuente) {
		this.fuente = fuente;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

    public int getIdCurso( )
    {
        return idCurso;
    }

    public int getCodigoCurso( )
    {
        return codigoCurso;
    }

    public String getNombreCurso( )
    {
        return nombreCurso;
    }

    public List<Integer> getRecursos( )
    {
        return recursos;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    public void setIdCurso( int idCurso )
    {
        this.idCurso = idCurso;
    }

    public void setCodigoCurso( int codigoCurso )
    {
        this.codigoCurso = codigoCurso;
    }

    public void setNombreCurso( String nombreCurso )
    {
        this.nombreCurso = nombreCurso;
    }

    public void setRecursos( List<Integer> recursos )
    {
        this.recursos = recursos;
    }    
}
