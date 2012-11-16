package co.edu.unicesi.sami.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the objetivosterminales_competenciasespecificas database table.
 * 
 */
@Embeddable
public class ObjetivoTerminalCompetenciaEspecificaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int FK_IdObjetivoTerminal;

	private String FK_CodigoPrograma;

	private int FK_IdCompetenciaEspecifica;

    public ObjetivoTerminalCompetenciaEspecificaPK() {
    }
	public int getFK_IdObjetivoTerminal() {
		return this.FK_IdObjetivoTerminal;
	}
	public void setFK_IdObjetivoTerminal(int FK_IdObjetivoTerminal) {
		this.FK_IdObjetivoTerminal = FK_IdObjetivoTerminal;
	}
	public String getFK_CodigoPrograma() {
		return this.FK_CodigoPrograma;
	}
	public void setFK_CodigoPrograma(String FK_CodigoPrograma) {
		this.FK_CodigoPrograma = FK_CodigoPrograma;
	}
	public int getFK_IdCompetenciaEspecifica() {
		return this.FK_IdCompetenciaEspecifica;
	}
	public void setFK_IdCompetenciaEspecifica(int FK_IdCompetenciaEspecifica) {
		this.FK_IdCompetenciaEspecifica = FK_IdCompetenciaEspecifica;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ObjetivoTerminalCompetenciaEspecificaPK)) {
			return false;
		}
		ObjetivoTerminalCompetenciaEspecificaPK castOther = (ObjetivoTerminalCompetenciaEspecificaPK)other;
		return 
			(this.FK_IdObjetivoTerminal == castOther.FK_IdObjetivoTerminal)
			&& this.FK_CodigoPrograma.equals(castOther.FK_CodigoPrograma)
			&& (this.FK_IdCompetenciaEspecifica == castOther.FK_IdCompetenciaEspecifica);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.FK_IdObjetivoTerminal;
		hash = hash * prime + this.FK_CodigoPrograma.hashCode();
		hash = hash * prime + this.FK_IdCompetenciaEspecifica;
		
		return hash;
    }
}