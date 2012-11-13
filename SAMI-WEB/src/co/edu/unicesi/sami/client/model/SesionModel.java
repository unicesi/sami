package co.edu.unicesi.sami.client.model;

import java.io.Serializable;
import java.util.List;

import co.edu.unicesi.sami.bo.SesionBO;
import co.edu.unicesi.sami.bo.UnidadBO;

import com.extjs.gxt.ui.client.data.BaseModel;

public class SesionModel extends BaseModel implements Serializable{
    
    public SesionModel( )
    {

    }
    
	public SesionModel(int id, int numero, String nombre) {
		set("id", id);
		set("numero", numero);
		set("nombre", nombre);
	}

	public int getId() {
		return (Integer) get("id");
	}

	public int getNumero() {
		return (Integer) get("numero");
	}

	public String getNombre() {
		return (String) get("nombre");
	}
	
	public void setId(int id) {
		set("id", id);
	}

	public void setNumero(int numero) {
		set("numero", numero);
	}

	public void setNombre(String nombre) {
		set("nombre", nombre);
	}

	public static SesionModel toModelFromBO(SesionBO bo) {
		// TODO Auto-generated method stub
		SesionModel sModel = new SesionModel();
		sModel.setId(bo.getId());
		sModel.setNombre(bo.getNombre());
		sModel.setNumero(bo.getNumero());
		return sModel;
	}         
}

