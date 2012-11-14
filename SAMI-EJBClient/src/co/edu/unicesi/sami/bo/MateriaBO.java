package co.edu.unicesi.sami.bo;

import java.io.Serializable;
import java.util.List;

public class MateriaBO implements Serializable
{
    private int id;
    private int codigo;
    private String nombre;
    private int idObjGeneral;
    private String contenidoObjGeneral;
    private List<Integer> unidades;
    private List<Integer> objTerminales;
    private List<Integer> materiales;    

	private static final long serialVersionUID = 1L;
    
    public MateriaBO()
    {
        
    }

    public int getId( )
    {
        return id;
    }

    public int getCodigo( )
    {
        return codigo;
    }

    public String getNombre( )
    {
        return nombre;
    }

    public int getIdObjGeneral( )
    {
        return idObjGeneral;
    }

    public String getContenidoObjGeneral( )
    {
        return contenidoObjGeneral;
    }

    public List<Integer> getUnidades( )
    {
        return unidades;
    }

    public List<Integer> getObjTerminales( )
    {
        return objTerminales;
    }

    public List<Integer> getMateriales( )
    {
        return materiales;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    public void setCodigo( int codigo )
    {
        this.codigo = codigo;
    }

    public void setNombre( String nombre )
    {
        this.nombre = nombre;
    }
    public void setIdObjGeneral( int idObjGeneral )
    {
        this.idObjGeneral = idObjGeneral;
    }

    public void setContenidoObjGeneral( String contenidoObjGeneral )
    {
        this.contenidoObjGeneral = contenidoObjGeneral;
    }
    
    public void setUnidades( List<Integer> unidades )
    {
        this.unidades = unidades;
    }

    public void setObjTerminales( List<Integer> objTerminales )
    {
        this.objTerminales = objTerminales;
    }

    public void setMateriales( List<Integer> materiales )
    {
        this.materiales = materiales;
    }
    
    
    
}
