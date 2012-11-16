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

	private int puntaje;

	//bi-directional many-to-one association to BloqueCompetenciaEspecifica
	@OneToMany(mappedBy="competenciasespecificasPrograma")
	private List<BloqueCompetenciaEspecifica> bloquesCompetenciasespecificas;

	//bi-directional many-to-one association to CompetenciaEspecifica
    @ManyToOne
	@JoinColumn(name="FK_IdCompetenciaEspecifica")
	private CompetenciaEspecifica competenciasespecifica;

	//bi-directional many-to-one association to Programa
    @ManyToOne
	@JoinColumn(name="FK_CodigoPrograma")
	private Programa programa;

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
	
	public int getPuntaje() {
		return this.puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public List<BloqueCompetenciaEspecifica> getBloquesCompetenciasespecificas() {
		return this.bloquesCompetenciasespecificas;
	}

	public void setBloquesCompetenciasespecificas(List<BloqueCompetenciaEspecifica> bloquesCompetenciasespecificas) {
		this.bloquesCompetenciasespecificas = bloquesCompetenciasespecificas;
	}
	
	public CompetenciaEspecifica getCompetenciasespecifica() {
		return this.competenciasespecifica;
	}

	public void setCompetenciasespecifica(CompetenciaEspecifica competenciasespecifica) {
		this.competenciasespecifica = competenciasespecifica;
	}
	
	public Programa getPrograma() {
		return this.programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
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