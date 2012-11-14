package co.edu.unicesi.sami.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the assessment database table.
 * 
 */
@Embeddable
public class AssessmentPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String FK_Rol;

	private String FK_NombreUsuario;

    public AssessmentPK() {
    }
	public String getFK_Rol() {
		return this.FK_Rol;
	}
	public void setFK_Rol(String FK_Rol) {
		this.FK_Rol = FK_Rol;
	}
	public String getFK_NombreUsuario() {
		return this.FK_NombreUsuario;
	}
	public void setFK_NombreUsuario(String FK_NombreUsuario) {
		this.FK_NombreUsuario = FK_NombreUsuario;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AssessmentPK)) {
			return false;
		}
		AssessmentPK castOther = (AssessmentPK)other;
		return 
			this.FK_Rol.equals(castOther.FK_Rol)
			&& this.FK_NombreUsuario.equals(castOther.FK_NombreUsuario);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.FK_Rol.hashCode();
		hash = hash * prime + this.FK_NombreUsuario.hashCode();
		
		return hash;
    }
}