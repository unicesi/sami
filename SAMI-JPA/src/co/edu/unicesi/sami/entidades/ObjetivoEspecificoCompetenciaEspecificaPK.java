package co.edu.unicesi.sami.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the objetivosespecificos_competenciasespecificas database table.
 * 
 */
@Embeddable
public class ObjetivoEspecificoCompetenciaEspecificaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int FK_IdObjetivoEspecifico;

	private int FK_CodigoPrograma;

	private int FK_IdCompetenciaEspecifica;

    public ObjetivoEspecificoCompetenciaEspecificaPK() {
    }
	public int getFK_IdObjetivoEspecifico() {
		return this.FK_IdObjetivoEspecifico;
	}
	public void setFK_IdObjetivoEspecifico(int FK_IdObjetivoEspecifico) {
		this.FK_IdObjetivoEspecifico = FK_IdObjetivoEspecifico;
	}
	public int getFK_CodigoPrograma() {
		return this.FK_CodigoPrograma;
	}
	public void setFK_CodigoPrograma(int FK_CodigoPrograma) {
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
		if (!(other instanceof ObjetivoEspecificoCompetenciaEspecificaPK)) {
			return false;
		}
		ObjetivoEspecificoCompetenciaEspecificaPK castOther = (ObjetivoEspecificoCompetenciaEspecificaPK)other;
		return 
			(this.FK_IdObjetivoEspecifico == castOther.FK_IdObjetivoEspecifico)
			&& (this.FK_CodigoPrograma == castOther.FK_CodigoPrograma)
			&& (this.FK_IdCompetenciaEspecifica == castOther.FK_IdCompetenciaEspecifica);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.FK_IdObjetivoEspecifico;
		hash = hash * prime + this.FK_CodigoPrograma;
		hash = hash * prime + this.FK_IdCompetenciaEspecifica;
		
		return hash;
    }
}