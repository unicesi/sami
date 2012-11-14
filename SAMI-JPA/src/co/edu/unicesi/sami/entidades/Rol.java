package co.edu.unicesi.sami.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the roles database table.
 * 
 */
@Entity
@Table(name="roles")
public class Rol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String rol;

	//bi-directional many-to-many association to Usuario
    @ManyToMany
	@JoinTable(
		name="usuariosroles"
		, joinColumns={
			@JoinColumn(name="FK_Rol")
			}
		, inverseJoinColumns={
			@JoinColumn(name="FK_NombreUsuario")
			}
		)
	private List<Usuario> usuarios;

    public Rol() {
    }

	public String getRol() {
		return this.rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
}