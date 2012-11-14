package co.edu.unicesi.sami.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the materias_competenciasespecificas database table.
 * 
 */
@Entity
@Table(name="materias_competenciasespecificas")
public class MateriaCompetenciaEspecifica implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MateriaCompetenciaEspecificaPK id;

	private byte aplica;

	private byte enseña;

	private byte introduce;

	//bi-directional many-to-one association to CompetenciaEspecificaPrograma
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="FK_CodigoPrograma", referencedColumnName="FK_CodigoPrograma"),
		@JoinColumn(name="FK_IdCompetenciaEspecifica", referencedColumnName="FK_idCompetenciaEspecifica")
		})
	private CompetenciaEspecificaPrograma competenciasespecificasPrograma;

	//bi-directional many-to-one association to Materia
    @ManyToOne
	@JoinColumn(name="FK_CodigoMateria")
	private Materia materia;

    public MateriaCompetenciaEspecifica() {
    }

	public MateriaCompetenciaEspecificaPK getId() {
		return this.id;
	}

	public void setId(MateriaCompetenciaEspecificaPK id) {
		this.id = id;
	}
	
	public byte getAplica() {
		return this.aplica;
	}

	public void setAplica(byte aplica) {
		this.aplica = aplica;
	}

	public byte getEnseña() {
		return this.enseña;
	}

	public void setEnseña(byte enseña) {
		this.enseña = enseña;
	}

	public byte getIntroduce() {
		return this.introduce;
	}

	public void setIntroduce(byte introduce) {
		this.introduce = introduce;
	}

	public CompetenciaEspecificaPrograma getCompetenciasespecificasPrograma() {
		return this.competenciasespecificasPrograma;
	}

	public void setCompetenciasespecificasPrograma(CompetenciaEspecificaPrograma competenciasespecificasPrograma) {
		this.competenciasespecificasPrograma = competenciasespecificasPrograma;
	}
	
	public Materia getMateria() {
		return this.materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}
	
}