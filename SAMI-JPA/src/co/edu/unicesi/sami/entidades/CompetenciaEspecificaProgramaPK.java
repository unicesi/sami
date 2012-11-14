package co.edu.unicesi.sami.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the competenciasespecificas_programas database table.
 * 
 */
@Embeddable
public class CompetenciaEspecificaProgramaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int FK_CodigoPrograma;

	private int FK_idCompetenciaEspecifica;

    public CompetenciaEspecificaProgramaPK() {
    }
	public int getFK_CodigoPrograma() {
		return this.FK_CodigoPrograma;
	}
	public void setFK_CodigoPrograma(int FK_CodigoPrograma) {
		this.FK_CodigoPrograma = FK_CodigoPrograma;
	}
	public int getFK_idCompetenciaEspecifica() {
		return this.FK_idCompetenciaEspecifica;
	}
	public void setFK_idCompetenciaEspecifica(int FK_idCompetenciaEspecifica) {
		this.FK_idCompetenciaEspecifica = FK_idCompetenciaEspecifica;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CompetenciaEspecificaProgramaPK)) {
			return false;
		}
		CompetenciaEspecificaProgramaPK castOther = (CompetenciaEspecificaProgramaPK)other;
		return 
			(this.FK_CodigoPrograma == castOther.FK_CodigoPrograma)
			&& (this.FK_idCompetenciaEspecifica == castOther.FK_idCompetenciaEspecifica);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.FK_CodigoPrograma;
		hash = hash * prime + this.FK_idCompetenciaEspecifica;
		
		return hash;
    }
}