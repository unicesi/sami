package co.edu.unicesi.sami.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the objetivosespecificos_competenciasespecificas database table.
 * 
 */
@Entity
@Table(name="objetivosespecificos_competenciasespecificas")
public class ObjetivoEspecificoCompetenciaEspecifica implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ObjetivoEspecificoCompetenciaEspecificaPK id;

	private String aplica;

	private String enseña;

	private String introduce;

	//bi-directional many-to-one association to ObjetivoEspecifico
    @ManyToOne
	@JoinColumn(name="FK_IdObjetivoEspecifico")
	private ObjetivoEspecifico objetivosEspecifico;

	//bi-directional many-to-one association to CompetenciaEspecificaPrograma
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="FK_CodigoPrograma", referencedColumnName="FK_CodigoPrograma"),
		@JoinColumn(name="FK_IdCompetenciaEspecifica", referencedColumnName="FK_idCompetenciaEspecifica")
		})
	private CompetenciaEspecificaPrograma competenciasespecificasPrograma;

    public ObjetivoEspecificoCompetenciaEspecifica() {
    }

	public ObjetivoEspecificoCompetenciaEspecificaPK getId() {
		return this.id;
	}

	public void setId(ObjetivoEspecificoCompetenciaEspecificaPK id) {
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

	public ObjetivoEspecifico getObjetivosEspecifico() {
		return this.objetivosEspecifico;
	}

	public void setObjetivosEspecifico(ObjetivoEspecifico objetivosEspecifico) {
		this.objetivosEspecifico = objetivosEspecifico;
	}
	
	public CompetenciaEspecificaPrograma getCompetenciasespecificasPrograma() {
		return this.competenciasespecificasPrograma;
	}

	public void setCompetenciasespecificasPrograma(CompetenciaEspecificaPrograma competenciasespecificasPrograma) {
		this.competenciasespecificasPrograma = competenciasespecificasPrograma;
	}
	
}