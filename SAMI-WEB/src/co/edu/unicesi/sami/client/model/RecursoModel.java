package co.edu.unicesi.sami.client.model;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BaseModel;

import co.edu.unicesi.sami.bo.MaterialBO;
import co.edu.unicesi.sami.bo.RecursoBO;
import co.edu.unicesi.sami.bo.UnidadBO;

public class RecursoModel extends BaseModel implements Serializable {
	public RecursoModel() {

	}

	public RecursoModel(int id, String fuente, String idioma, String autor,
			String titulo, String ano, String ciudad, String editorial,
			String ruta) {
		set("fuente", fuente);
		set("idioma", idioma);
		set("id", id);
		set("autor", autor);
		set("titulo", titulo);
		set("ano", ano);
		set("ciudad", ciudad);
		set("editorial", editorial);
	}

	public int getId() {
		return (Integer) get("id");
	}

	public String getAutor() {
		return (String) get("autor");
	}

	public String getTitulo() {
		return (String) get("titulo");
	}

	public String getAno() {
		return (String) get("ano");
	}

	public String getCiudad() {
		return (String) get("ciudad");
	}

	public String getEditorial() {
		return (String) get("editorial");
	}

	public void setId(int id) {
		set("id", id);
	}
	
	public void setFuente(String fuente) {
		set("fuente", fuente);
	}

	public void setIdioma(String idioma) {
		set("idioma", idioma);
	}

	public void setAutor(String autor) {
		set("autor", autor);
	}

	public void setTitulo(String titulo) {
		set("titulo", titulo);
	}

	public void setAno(String ano) {
		set("ano", ano);
	}

	public void setCiudad(String ciudad) {
		set("ciudad", ciudad);
	}

	public void setEditorial(String editorial) {
		set("editorial", editorial);
	}

	public static RecursoModel toModelFromBO(RecursoBO bo) {
		// TODO Auto-generated method stub
		RecursoModel uModel = new RecursoModel();
		uModel.setId(bo.getId());
		uModel.setFuente(bo.getMaterial().getFuente());
		uModel.setIdioma(bo.getMaterial().getIdioma());
		uModel.setAutor(bo.getMaterial().getAutor());
		uModel.setTitulo(bo.getMaterial().getTitulo());
		uModel.setAno(bo.getMaterial().getAno());
		uModel.setCiudad(bo.getMaterial().getCiudad());
		uModel.setEditorial(bo.getMaterial().getEditorial());
		return uModel;
	}

}
