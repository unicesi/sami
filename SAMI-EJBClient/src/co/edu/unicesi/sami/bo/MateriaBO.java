package co.edu.unicesi.sami.bo;

import java.io.Serializable;
import java.util.List;

public class MateriaBO implements Serializable
{
    private String codigo;
    private String nombre;
    private String descripcion;  
    private int creditos;
    private int idObjGeneral;
    private String contenidoObjGeneral;
    private List<Integer> unidades;
    private List<Integer> objTerminales;
    private List<Integer> materiales;    

	private static final long serialVersionUID = 1L;
    
    public MateriaBO()
    {
        
    }

    public String getCodigo( )
    {
        return codigo;
    }

    public String getNombre( )
    {
        return nombre;
    }
    
    public String getDescripcion( )
    {
        return descripcion;
    }
    
    public int getCreditos( )
    {
        return creditos;
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

    public void setCodigo( String codigo )
    {
        this.codigo = codigo;
    }

    public void setNombre( String nombre )
    {
        this.nombre = nombre;
    }
    
    public void setDescripcion( String descripcion )
    {
        this.descripcion = descripcion;
    }
    
    public void setCreditos( int creditos )
    {
        this.creditos = creditos;
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
