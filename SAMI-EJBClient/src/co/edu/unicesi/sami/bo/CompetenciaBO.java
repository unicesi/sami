package co.edu.unicesi.sami.bo;

import java.io.Serializable;

public class CompetenciaBO implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String nombre;
	private String descripcion;
	
	public CompetenciaBO(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	

}
