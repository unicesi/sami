package co.edu.unicesi.sami.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the competenciasterminales_programas database table.
 * 
 */
@Embeddable
public class CompetenciaTerminalProgramaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String FK_CodigoPrograma;

	private int FK_idCompetenciaTerminal;

    public CompetenciaTerminalProgramaPK() {
    }
	public String getFK_CodigoPrograma() {
		return this.FK_CodigoPrograma;
	}
	public void setFK_CodigoPrograma(String FK_CodigoPrograma) {
		this.FK_CodigoPrograma = FK_CodigoPrograma;
	}
	public int getFK_idCompetenciaTerminal() {
		return this.FK_idCompetenciaTerminal;
	}
	public void setFK_idCompetenciaTerminal(int FK_idCompetenciaTerminal) {
		this.FK_idCompetenciaTerminal = FK_idCompetenciaTerminal;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CompetenciaTerminalProgramaPK)) {
			return false;
		}
		CompetenciaTerminalProgramaPK castOther = (CompetenciaTerminalProgramaPK)other;
		return 
			this.FK_CodigoPrograma.equals(castOther.FK_CodigoPrograma)
			&& (this.FK_idCompetenciaTerminal == castOther.FK_idCompetenciaTerminal);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.FK_CodigoPrograma.hashCode();
		hash = hash * prime + this.FK_idCompetenciaTerminal;
		
		return hash;
    }
}