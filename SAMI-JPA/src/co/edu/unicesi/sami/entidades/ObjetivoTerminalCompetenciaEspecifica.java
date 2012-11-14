package co.edu.unicesi.sami.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the objetivosterminales_competenciasespecificas database table.
 * 
 */
@Entity
@Table(name="objetivosterminales_competenciasespecificas")
public class ObjetivoTerminalCompetenciaEspecifica implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ObjetivoTerminalCompetenciaEspecificaPK id;

	private String aplica;

	private String enseña;

	private String introduce;

	//bi-directional many-to-one association to CompetenciaEspecificaPrograma
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="FK_CodigoPrograma", referencedColumnName="FK_CodigoPrograma"),
		@JoinColumn(name="FK_IdCompetenciaEspecifica", referencedColumnName="FK_idCompetenciaEspecifica")
		})
	private CompetenciaEspecificaPrograma competenciasespecificasPrograma;

	//bi-directional many-to-one association to ObjetivoTerminal
    @ManyToOne
	@JoinColumn(name="FK_IdObjetivoTerminal")
	private ObjetivoTerminal objetivosTerminale;

    public ObjetivoTerminalCompetenciaEspecifica() {
    }

	public ObjetivoTerminalCompetenciaEspecificaPK getId() {
		return this.id;
	}

	public void setId(ObjetivoTerminalCompetenciaEspecificaPK id) {
		this.id = id;
	}
	
	public String getAplica() {
		return this.aplica;
	}

	public void setAplica(String aplica) {
		this.aplica = aplica;
	}

	public String getEnseña() {
		return this.enseña;
	}

	public void setEnseña(String enseña) {
		this.enseña = enseña;
	}

	public String getIntroduce() {
		return this.introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public CompetenciaEspecificaPrograma getCompetenciasespecificasPrograma() {
		return this.competenciasespecificasPrograma;
	}

	public void setCompetenciasespecificasPrograma(CompetenciaEspecificaPrograma competenciasespecificasPrograma) {
		this.competenciasespecificasPrograma = competenciasespecificasPrograma;
	}
	
	public ObjetivoTerminal getObjetivosTerminale() {
		return this.objetivosTerminale;
	}

	public void setObjetivosTerminale(ObjetivoTerminal objetivosTerminale) {
		this.objetivosTerminale = objetivosTerminale;
	}
	
}