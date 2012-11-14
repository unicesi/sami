package co.edu.unicesi.sami.client.model;

import java.io.Serializable;
import java.util.List;

import co.edu.unicesi.sami.bo.MateriaBO;

import com.extjs.gxt.ui.client.data.BaseModel;

public class MateriaModel extends BaseModel implements Serializable
{
    
    public MateriaModel()
    {
        
    }
    
    public MateriaModel(int id, String nombre, int codigo)
    {
        set( "id", id );
        set("nombre", nombre);
        set("codigo", codigo);
    }

    public int getId( )
    {
        return (Integer) get("id");
    }

    public int getCodigo( )
    {
        return (Integer) get("codigo");
    }

    public String getNombre( )
    {
        return (String) get( "nombre" );
    }    

    public void setId( int id )
    {
        set("id", id);
    }

    public void setCodigo( int codigo )
    {
        set("codigo", codigo);
    }

    public void setNombre( String nombre )
    {
        set( "nombre", nombre );
    }       
    
    public static MateriaModel toModelFromBO(MateriaBO bo)
    {
        MateriaModel cModel = new MateriaModel( );
        cModel.setId( bo.getId( ));
        cModel.setCodigo( bo.getCodigo( ) );
        cModel.setNombre( bo.getNombre( ) );
        
        return cModel;
    }
}
