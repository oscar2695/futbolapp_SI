package persistencetest;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "match_table")
public class Match {
	@Id
	@GeneratedValue
	private int id; // identificador de un partido
	@ManyToOne
	private Team teamLocal;// Equipo local
	@ManyToOne
	private Team teamVisitor;// Equipo visitante
	private int goalsLocal; // goles del equipo local
	private int goalsVisitor;// goles del equipo visitante
	private Date dateMatch;// fecha del partido
	private int spectators;// número de espactadores que asistieron al partido

	/**
	 * método observador del id
	 * 
	 * @return int
	 */
	public int getId() {
		return id;
	}

	/**
	 * método observador de teamLocal
	 * 
	 * @return Team
	 */
	public Team getTeamLocal() {
		return teamLocal;
	}

	/**
	 * método modificador de teamLocal
	 * 
	 * @param nameTeamLocal
	 */
	public void setTeamLocal(Team nameTeamLocal) {
		this.teamLocal = nameTeamLocal;
	}

	/**
	 * método observador de teamVisitor
	 * 
	 * @return
	 */
	public Team getTeamVisitor() {
		return teamVisitor;
	}

	/**
	 * método modificador de teamVisitor
	 * 
	 * @param teamVisitor
	 */
	public void setTeamVisitor(Team teamVisitor) {
		this.teamVisitor = teamVisitor;
	}

	/**
	 * método observador de goalsLocal
	 * 
	 * @return
	 */
	public int getGoalsLocal() {
		return goalsLocal;
	}

	/**
	 * método modificador de goalsLocal
	 * 
	 * @param goalsLocal
	 */
	public void setGoalsLocal(int goalsLocal) {
		this.goalsLocal = goalsLocal;
	}

	/**
	 * método observador de goalsVisitor
	 * 
	 * @return
	 */
	public int getGoalsVisitor() {
		return goalsVisitor;
	}

	/**
	 * método modificador de goalsVisitor
	 * 
	 * @param goalsVisitor
	 */
	public void setGoalsVisitor(int goalsVisitor) {
		this.goalsVisitor = goalsVisitor;
	}

	/**
	 * mñetodo observador de dateMatch
	 * 
	 * @return
	 */
	public Date getDateMatch() {
		return dateMatch;
	}

	/**
	 * método modificador de dateMatch
	 * 
	 * @param dateMatch
	 */
	public void setDateMatch(Date dateMatch) {
		this.dateMatch = dateMatch;
	}

	/**
	 * método observador de spectators
	 * 
	 * @return
	 */
	public int getSpectators() {
		return spectators;
	}

	/**
	 * método modificador de spectators
	 * 
	 * @param spectators
	 */
	public void setSpectators(int spectators) {
		this.spectators = spectators;
	}

}
