package co.edu.unicesi.sami.bo;

import java.io.Serializable;
import java.util.List;

public class SesionBO implements Serializable
{
    private int id;
    private int numero;
    private String nombre;
    private int idUnidad;
    private int numeroUnidad;
    private String nombreUnidad;
    private String contenidoUnidad;
    private List<TrabajoAsignadoBO> trabajosAsignados;
    
	private static final long serialVersionUID = 1L;
    
    public SesionBO( )
    {

    }

    public int getId( )
    {
        return id;
    }

    public int getNumero( )
    {
        return numero;
    }

    public String getNombre( )
    {
        return nombre;
    }

    public int getIdUnidad( )
    {
        return idUnidad;
    }

    public int getNumeroUnidad( )
    {
        return numeroUnidad;
    }

    public String getNombreUnidad( )
    {
        return nombreUnidad;
    }

    public String getContenidoUnidad( )
    {
        return contenidoUnidad;
    }

    public List<TrabajoAsignadoBO> getTrabajosAsignados( )
    {
        return trabajosAsignados;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    public void setNumero( int numero )
    {
        this.numero = numero;
    }

    public void setNombre( String nombre )
    {
        this.nombre = nombre;
    }

    public void setIdUnidad( int idUnidad )
    {
        this.idUnidad = idUnidad;
    }

    public void setNumeroUnidad( int numeroUnidad )
    {
        this.numeroUnidad = numeroUnidad;
    }

    public void setNombreUnidad( String nombreUnidad )
    {
        this.nombreUnidad = nombreUnidad;
    }

    public void setContenidoUnidad( String contenidoUnidad )
    {
        this.contenidoUnidad = contenidoUnidad;
    }

    public void setTrabajosAsignados( List<TrabajoAsignadoBO> trabajosAsignados )
    {
        this.trabajosAsignados = trabajosAsignados;
    }            
}
