package co.edu.unicesi.sami.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the calificaciones database table.
 * 
 */
@Entity
@Table(name="calificaciones")
public class Calificacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CalificacionPK id;

	private float puntaje;

	//bi-directional many-to-one association to CompetenciaEspecificaPrograma
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="FK_CodigoPrograma", referencedColumnName="FK_CodigoPrograma"),
		@JoinColumn(name="FK_IdCompetenciaEspecifica", referencedColumnName="FK_idCompetenciaEspecifica")
		})
	private CompetenciaEspecificaPrograma competenciasespecificasPrograma;

    public Calificacion() {
    }

	public CalificacionPK getId() {
		return this.id;
	}

	public void setId(CalificacionPK id) {
		this.id = id;
	}
	
	public float getPuntaje() {
		return this.puntaje;
	}

	public void setPuntaje(float puntaje) {
		this.puntaje = puntaje;
	}

	public CompetenciaEspecificaPrograma getCompetenciasespecificasPrograma() {
		return this.competenciasespecificasPrograma;
	}

	public void setCompetenciasespecificasPrograma(CompetenciaEspecificaPrograma competenciasespecificasPrograma) {
		this.competenciasespecificasPrograma = competenciasespecificasPrograma;
	}
	
}