package co.edu.unicesi.sami.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the assessment database table.
 * 
 */
@Entity
public class Assessment implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AssessmentPK id;

	//bi-directional many-to-one association to UsuarioRol
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="FK_NombreUsuario", referencedColumnName="FK_NombreUsuario"),
		@JoinColumn(name="FK_Rol", referencedColumnName="FK_Rol")
		})
	private UsuarioRol usuariosrole;

    public Assessment() {
    }

	public AssessmentPK getId() {
		return this.id;
	}

	public void setId(AssessmentPK id) {
		this.id = id;
	}
	
	public UsuarioRol getUsuariosrole() {
		return this.usuariosrole;
	}

	public void setUsuariosrole(UsuarioRol usuariosrole) {
		this.usuariosrole = usuariosrole;
	}
	
}