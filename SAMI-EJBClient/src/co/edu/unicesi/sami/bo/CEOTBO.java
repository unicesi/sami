package co.edu.unicesi.sami.bo;

import java.io.Serializable;

public class CEOTBO implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String nombre;
	private int idObjTerminal;
	private String nombreObjTerminal;
	private String introduce;
	private String ensena;
	private String aplica;
	
	public CEOTBO(){
		
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
	public int getIdObjTerminal() {
		return idObjTerminal;
	}
	public void setIdObjTerminal(int idObjTerminal) {
		this.idObjTerminal = idObjTerminal;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombreObjTerminal() {
		return nombreObjTerminal;
	}
	public void setNombreObjTerminal(String nombreObjTerminal) {
		this.nombreObjTerminal = nombreObjTerminal;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getEnsena() {
		return ensena;
	}
	public void setEnsena(String ensena) {
		this.ensena = ensena;
	}
	public String getAplica() {
		return aplica;
	}
	public void setAplica(String aplica) {
		this.aplica = aplica;
	}
	

}
