package co.edu.unicesi.sami.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the usuariosroles database table.
 * 
 */
@Entity
@Table(name="usuariosroles")
public class UsuarioRol implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UsuarioRolPK id;

	//bi-directional many-to-one association to Assessment
	@OneToMany(mappedBy="usuariosrole")
	private List<Assessment> assessments;

    public UsuarioRol() {
    }

	public UsuarioRolPK getId() {
		return this.id;
	}

	public void setId(UsuarioRolPK id) {
		this.id = id;
	}
	
	public List<Assessment> getAssessments() {
		return this.assessments;
	}

	public void setAssessments(List<Assessment> assessments) {
		this.assessments = assessments;
	}
	
}