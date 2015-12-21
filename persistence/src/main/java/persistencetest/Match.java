package persistencetest;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="match_table")
public class Match {
	@Id
	@GeneratedValue
	private int id;
	@ManyToOne
	private Team teamLocal;
	@ManyToOne
	private Team teamVisitor;
	private int goalsLocal; 
	private int goalsVisitor;
	private Date dateMatch;
	private int spectators;
	
	/**
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}
/**
 * 
 * @return
 */
	public Team getTeamLocal() {
		return teamLocal;
	}
/**
 * 
 * @param nameTeamLocal
 */
	public void setTeamLocal(Team nameTeamLocal) {
		this.teamLocal = nameTeamLocal;
	}
/**
 * 
 * @return
 */
	public Team getTeamVisitor() {
		return teamVisitor;
	}
/**
 * 
 * @param teamVisitor
 */
	public void setTeamVisitor(Team teamVisitor) {
		this.teamVisitor = teamVisitor;
	}
/**
 * 
 * @return
 */
	public int getGoalsLocal() {
		return goalsLocal;
	}
/**
 * 
 * @param goalsLocal
 */
	public void setGoalsLocal(int goalsLocal) {
		this.goalsLocal = goalsLocal;
	}
/**
 * 
 * @return
 */
	public int getGoalsVisitor() {
		return goalsVisitor;
	}
/**
 * 
 * @param goalsVisitor
 */
	public void setGoalsVisitor(int goalsVisitor) {
		this.goalsVisitor = goalsVisitor;
	}
/**
 * 
 * @return
 */
	public Date getDateMatch() {
		return dateMatch;
	}
/**
 * 
 * @param dateMatch
 */
	public void setDateMatch(Date dateMatch) {
		this.dateMatch = dateMatch;
	}
/**
 * 
 * @return
 */
	public int getSpectators() {
		return spectators;
	}
/**
 * 
 * @param spectators
 */
	public void setSpectators(int spectators) {
		this.spectators = spectators;
	}

}
