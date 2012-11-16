package co.edu.unicesi.sami.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the bloques_competenciasespecificas database table.
 * 
 */
@Entity
@Table(name="bloques_competenciasespecificas")
public class BloqueCompetenciaEspecifica implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BloqueCompetenciaEspecificaPK id;

	private byte aplica;

	private byte enseña;

	private byte introduce;

	private int puntaje;

	//bi-directional many-to-one association to Bloque
    @ManyToOne
	@JoinColumn(name="FK_IdBloque")
	private Bloque bloque;

	//bi-directional many-to-one association to CompetenciaEspecificaPrograma
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="FK_CodigoPrograma", referencedColumnName="FK_CodigoPrograma"),
		@JoinColumn(name="FK_idCompetenciaEspecifica", referencedColumnName="FK_IdCompetenciaEspecifica")
		})
	private CompetenciaEspecificaPrograma competenciasespecificasPrograma;

    public BloqueCompetenciaEspecifica() {
    }

	public BloqueCompetenciaEspecificaPK getId() {
		return this.id;
	}

	public void setId(BloqueCompetenciaEspecificaPK id) {
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

	public int getPuntaje() {
		return this.puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public Bloque getBloque() {
		return this.bloque;
	}

	public void setBloque(Bloque bloque) {
		this.bloque = bloque;
	}
	
	public CompetenciaEspecificaPrograma getCompetenciasespecificasPrograma() {
		return this.competenciasespecificasPrograma;
	}

	public void setCompetenciasespecificasPrograma(CompetenciaEspecificaPrograma competenciasespecificasPrograma) {
		this.competenciasespecificasPrograma = competenciasespecificasPrograma;
	}
	
}