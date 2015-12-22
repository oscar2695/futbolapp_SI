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
	private int spectators;// n�mero de espactadores que asistieron al partido

	/**
	 * m�todo observador del id
	 * 
	 * @return int
	 */
	public int getId() {
		return id;
	}

	/**
	 * m�todo observador de teamLocal
	 * 
	 * @return Team
	 */
	public Team getTeamLocal() {
		return teamLocal;
	}

	/**
	 * m�todo modificador de teamLocal
	 * 
	 * @param nameTeamLocal
	 */
	public void setTeamLocal(Team nameTeamLocal) {
		this.teamLocal = nameTeamLocal;
	}

	/**
	 * m�todo observador de teamVisitor
	 * 
	 * @return
	 */
	public Team getTeamVisitor() {
		return teamVisitor;
	}

	/**
	 * m�todo modificador de teamVisitor
	 * 
	 * @param teamVisitor
	 */
	public void setTeamVisitor(Team teamVisitor) {
		this.teamVisitor = teamVisitor;
	}

	/**
	 * m�todo observador de goalsLocal
	 * 
	 * @return
	 */
	public int getGoalsLocal() {
		return goalsLocal;
	}

	/**
	 * m�todo modificador de goalsLocal
	 * 
	 * @param goalsLocal
	 */
	public void setGoalsLocal(int goalsLocal) {
		this.goalsLocal = goalsLocal;
	}

	/**
	 * m�todo observador de goalsVisitor
	 * 
	 * @return
	 */
	public int getGoalsVisitor() {
		return goalsVisitor;
	}

	/**
	 * m�todo modificador de goalsVisitor
	 * 
	 * @param goalsVisitor
	 */
	public void setGoalsVisitor(int goalsVisitor) {
		this.goalsVisitor = goalsVisitor;
	}

	/**
	 * m�etodo observador de dateMatch
	 * 
	 * @return
	 */
	public Date getDateMatch() {
		return dateMatch;
	}

	/**
	 * m�todo modificador de dateMatch
	 * 
	 * @param dateMatch
	 */
	public void setDateMatch(Date dateMatch) {
		this.dateMatch = dateMatch;
	}

	/**
	 * m�todo observador de spectators
	 * 
	 * @return
	 */
	public int getSpectators() {
		return spectators;
	}

	/**
	 * m�todo modificador de spectators
	 * 
	 * @param spectators
	 */
	public void setSpectators(int spectators) {
		this.spectators = spectators;
	}

}
