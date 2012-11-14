package co.edu.unicesi.sami.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the bloques database table.
 * 
 */
@Entity
@Table(name="bloques")
public class Bloque implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String descripcion;

	private String nombre;

	//bi-directional many-to-many association to Materia
    @ManyToMany
	@JoinTable(
		name="bloques_materias"
		, joinColumns={
			@JoinColumn(name="FK_IdBloque")
			}
		, inverseJoinColumns={
			@JoinColumn(name="FK_CodigoMateria")
			}
		)
	private List<Materia> materias;

	//bi-directional many-to-one association to Programa
    @ManyToOne
	@JoinColumn(name="FK_CodigoPrograma")
	private Programa programa;

	//bi-directional many-to-one association to BloqueCompetenciaEspecifica
	@OneToMany(mappedBy="bloque")
	private List<BloqueCompetenciaEspecifica> bloquesCompetenciasespecificas;

    public Bloque() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Materia> getMaterias() {
		return this.materias;
	}

	public void setMaterias(List<Materia> materias) {
		this.materias = materias;
	}
	
	public Programa getPrograma() {
		return this.programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}
	
	public List<BloqueCompetenciaEspecifica> getBloquesCompetenciasespecificas() {
		return this.bloquesCompetenciasespecificas;
	}

	public void setBloquesCompetenciasespecificas(List<BloqueCompetenciaEspecifica> bloquesCompetenciasespecificas) {
		this.bloquesCompetenciasespecificas = bloquesCompetenciasespecificas;
	}
	
}