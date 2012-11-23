package co.edu.unicesi.sami.client.model;

import java.io.Serializable;

import co.edu.unicesi.sami.bo.CEOTBO;
import co.edu.unicesi.sami.bo.CompetenciaBO;

import com.extjs.gxt.ui.client.data.BaseModel;

public class CEOTModel extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public CEOTModel(){
		
	}
	public CEOTModel(int id,String nombre,String nombreObjTerminal,String introduce,String ense�a,String aplica)
    {
        set("id", id);
        set("nombre", nombre);
        set("nombreObjTerminal", nombreObjTerminal);
        set("introduce",introduce);
        set("ense�a",ense�a);
        set("aplica",aplica);
    }

    public int getId( )
    {
        return (Integer) get("id");
    }

    public String getNombre( )
    {
        return (String) get("nombre");
    }

    public String getNombreObjTerminal( )
    {
        return (String) get("nombreObjTerminal");
    }
    
    public String getIntroduce()
    {
    	return (String) get("introduce");
    }
    
    public String getEnse�a()
    {
    	return (String) get("ense�a");
    }
    
    public String getAplica()
    {
    	return (String) get("aplica");
    }
   
    public void setId( int id )
    {
        set("id", id);
    }

    public void setNombre( String nombre )
    {
        set("nombre", nombre);
    }

    public void setNombreObjTerminal( String nombreObjTerminal )
    {
        set("nombreObjTerminal", nombreObjTerminal);
    }
    
    public void setIntroduce(String introduce){
    	set("introduce",introduce);
    }
    
    public void setEnse�a(String ense�a){
    	set("ense�a",ense�a);
    }
    
    public void setAplica(String aplica){
    	set("aplica",aplica);
    }
    
    public static CEOTModel toModelFromBO(CEOTBO bo)
    {
        CEOTModel cModel = new CEOTModel( );
        cModel.setId(bo.getId());
        cModel.setNombre(bo.getNombre());
        cModel.setNombreObjTerminal(bo.getNombreObjTerminal());
        cModel.setIntroduce(bo.getIntroduce());
        cModel.setEnse�a(bo.getEnse�a());
        cModel.setAplica(bo.getAplica());
        return cModel;
    }

}
