package co.edu.unicesi.sami.client.model;

import java.io.Serializable;
import java.util.List;

import co.edu.unicesi.sami.bo.MateriaBO;

import com.extjs.gxt.ui.client.data.BaseModel;

public class MateriaModel extends BaseModel implements Serializable {

	public MateriaModel() {

	}

	public MateriaModel(String nombre, String codigo, String descripcion,
			int creditos) {
		set("nombre", nombre);
		set("codigo", codigo);
		set("descripcion", descripcion);
		set("creditos", creditos);
	}

	public String getCodigo() {
		return (String) get("codigo");
	}

	public String getNombre() {
		return (String) get("nombre");
	}
	
	public String getDescripcion() {
		return (String) get("descripcion");
	}

	public int getCreditos() {
		return (Integer) get("creditos");
	}

	public void setCodigo(String codigo) {
		set("codigo", codigo);
	}

	public void setNombre(String nombre) {
		set("nombre", nombre);
	}

	public void setDescripcion(String descripcion) {
		set("descripcion", descripcion);
	}

	public void setCreditos(int creditos) {
		set("creditos", creditos);
	}

	public static MateriaModel toModelFromBO(MateriaBO bo) {
		MateriaModel cModel = new MateriaModel();
		cModel.setCodigo(bo.getCodigo());
		cModel.setNombre(bo.getNombre());
		cModel.setCreditos(bo.getCreditos());
		cModel.setDescripcion(bo.getDescripcion());

		return cModel;
	}
}
