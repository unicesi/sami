package co.edu.unicesi.sami.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the competenciasespecificas_programas database table.
 * 
 */
@Entity
@Table(name="competenciasespecificas_programas")
public class CompetenciaEspecificaPrograma implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CompetenciaEspecificaProgramaPK id;

	//bi-directional many-to-one association to BloqueCompetenciaEspecifica
	@OneToMany(mappedBy="competenciasespecificasPrograma")
	private List<BloqueCompetenciaEspecifica> bloquesCompetenciasespecificas;

	//bi-directional many-to-one association to Calificacion
	@OneToMany(mappedBy="competenciasespecificasPrograma")
	private List<Calificacion> calificaciones;

	//bi-directional many-to-one association to MateriaCompetenciaEspecifica
	@OneToMany(mappedBy="competenciasespecificasPrograma")
	private List<MateriaCompetenciaEspecifica> materiasCompetenciasespecificas;

	//bi-directional many-to-one association to ObjetivoEspecificoCompetenciaEspecifica
	@OneToMany(mappedBy="competenciasespecificasPrograma")
	private List<ObjetivoEspecificoCompetenciaEspecifica> objetivosespecificosCompetenciasespecificas;

	//bi-directional many-to-one association to ObjetivoTerminalCompetenciaEspecifica
	@OneToMany(mappedBy="competenciasespecificasPrograma")
	private List<ObjetivoTerminalCompetenciaEspecifica> objetivosterminalesCompetenciasespecificas;

    public CompetenciaEspecificaPrograma() {
    }

	public CompetenciaEspecificaProgramaPK getId() {
		return this.id;
	}

	public void setId(CompetenciaEspecificaProgramaPK id) {
		this.id = id;
	}
	
	public List<BloqueCompetenciaEspecifica> getBloquesCompetenciasespecificas() {
		return this.bloquesCompetenciasespecificas;
	}

	public void setBloquesCompetenciasespecificas(List<BloqueCompetenciaEspecifica> bloquesCompetenciasespecificas) {
		this.bloquesCompetenciasespecificas = bloquesCompetenciasespecificas;
	}
	
	public List<Calificacion> getCalificaciones() {
		return this.calificaciones;
	}

	public void setCalificaciones(List<Calificacion> calificaciones) {
		this.calificaciones = calificaciones;
	}
	
	public List<MateriaCompetenciaEspecifica> getMateriasCompetenciasespecificas() {
		return this.materiasCompetenciasespecificas;
	}

	public void setMateriasCompetenciasespecificas(List<MateriaCompetenciaEspecifica> materiasCompetenciasespecificas) {
		this.materiasCompetenciasespecificas = materiasCompetenciasespecificas;
	}
	
	public List<ObjetivoEspecificoCompetenciaEspecifica> getObjetivosespecificosCompetenciasespecificas() {
		return this.objetivosespecificosCompetenciasespecificas;
	}

	public void setObjetivosespecificosCompetenciasespecificas(List<ObjetivoEspecificoCompetenciaEspecifica> objetivosespecificosCompetenciasespecificas) {
		this.objetivosespecificosCompetenciasespecificas = objetivosespecificosCompetenciasespecificas;
	}
	
	public List<ObjetivoTerminalCompetenciaEspecifica> getObjetivosterminalesCompetenciasespecificas() {
		return this.objetivosterminalesCompetenciasespecificas;
	}

	public void setObjetivosterminalesCompetenciasespecificas(List<ObjetivoTerminalCompetenciaEspecifica> objetivosterminalesCompetenciasespecificas) {
		this.objetivosterminalesCompetenciasespecificas = objetivosterminalesCompetenciasespecificas;
	}
	
}