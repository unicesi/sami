package co.edu.unicesi.sami.client.model;

import java.io.Serializable;
import java.util.List;

import co.edu.unicesi.sami.bo.TrabajoAsignadoBO;

import com.extjs.gxt.ui.client.data.BaseModel;

public class TrabajoAsignadoModel extends BaseModel implements Serializable{
	private int id;
    private String contenido;
    private String encargado;
    private String tipo;
    
	private static final long serialVersionUID = 1L;
    
    public TrabajoAsignadoModel( )
    {
        
    }
    
    public TrabajoAsignadoModel(int id, String contenido, String encargado, String tipo )
    {
    	set("id", id);
		set("contenido", contenido);
		set("encargado", encargado);
		set("tipo", tipo);
    }

    public int getId( )
    {
		return (Integer) get("id");
    }

    public String getContenido( )
    {

		return (String) get("contenido");
    }

    public String getEncargado( )
    {
    	return (String) get("encargado");
    }

    public String getTipo( )
    {
    	return (String) get("tipo");
    }

    public void setId( int id )
    {
    	set("id", id);
    }

    public void setContenido( String contenido )
    {
    	set("contenido", contenido);
    }

    public void setEncargado( String encargado )
    {
    	set("encargado", encargado);
    }

    public void setTipo( String tipo )
    {
    	set("tipo", tipo);
    }

	public static TrabajoAsignadoModel toModelFromBO(
			TrabajoAsignadoBO bo) {
		TrabajoAsignadoModel t = new TrabajoAsignadoModel();
		t.setContenido(bo.getContenido());
		t.setEncargado(bo.getEncargado());
		t.setId(bo.getId());
		t.setTipo(bo.getTipo());
		return null;
	}
}
