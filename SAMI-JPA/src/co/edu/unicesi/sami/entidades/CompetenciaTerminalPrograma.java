package co.edu.unicesi.sami.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the competenciasterminales_programas database table.
 * 
 */
@Entity
@Table(name="competenciasterminales_programas")
public class CompetenciaTerminalPrograma implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CompetenciaTerminalProgramaPK id;

	private int puntaje;

	//bi-directional many-to-one association to CompetenciaTerminal
    @ManyToOne
	@JoinColumn(name="FK_idCompetenciaTerminal")
	private CompetenciaTerminal competenciasterminale;

	//bi-directional many-to-one association to Programa
    @ManyToOne
	@JoinColumn(name="FK_CodigoPrograma")
	private Programa programa;

    public CompetenciaTerminalPrograma() {
    }

	public CompetenciaTerminalProgramaPK getId() {
		return this.id;
	}

	public void setId(CompetenciaTerminalProgramaPK id) {
		this.id = id;
	}
	
	public int getPuntaje() {
		return this.puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public CompetenciaTerminal getCompetenciasterminale() {
		return this.competenciasterminale;
	}

	public void setCompetenciasterminale(CompetenciaTerminal competenciasterminale) {
		this.competenciasterminale = competenciasterminale;
	}
	
	public Programa getPrograma() {
		return this.programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}
	
}