package co.edu.unicesi.sami.client.model;

import java.io.Serializable;

import co.edu.unicesi.sami.bo.CompetenciaBO;
import co.edu.unicesi.sami.bo.ObjetivoTerminalBO;

import com.extjs.gxt.ui.client.data.BaseModel;

public class CompetenciaModel extends BaseModel implements Serializable
{   
    public CompetenciaModel( )
    {
        
    }
    
    public CompetenciaModel(int id, String nombre, String descripcion)
    {
        set("id", id);
        set("nombre", nombre);
        set("descripcion", descripcion);
    }

    public int getId( )
    {
        return (Integer) get("id");
    }

    public String getNombre( )
    {
        return (String) get("nombre");
    }

    public String getDescripcion( )
    {
        return (String) get("descripcion");
    }
   
    public void setId( int id )
    {
        set("id", id);
    }

    public void setNombre( String nombre )
    {
        set("nombre", nombre);
    }

    public void setDescripcion( String descripcion )
    {
        set("descripcion", descripcion);
    }
    
    public static CompetenciaModel toModelFromBO(CompetenciaBO bo)
    {
        CompetenciaModel cModel = new CompetenciaModel( );
           
        return cModel;
    }
}