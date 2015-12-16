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
	
	
	public int getId() {
		return id;
	}

	public Team getTeamLocal() {
		return teamLocal;
	}

	public void setTeamLocal(Team nameTeamLocal) {
		this.teamLocal = nameTeamLocal;
	}

	public Team getTeamVisitor() {
		return teamVisitor;
	}

	public void setTeamVisitor(Team teamVisitor) {
		this.teamVisitor = teamVisitor;
	}

	public int getGoalsLocal() {
		return goalsLocal;
	}

	public void setGoalsLocal(int goalsLocal) {
		this.goalsLocal = goalsLocal;
	}

	public int getGoalsVisitor() {
		return goalsVisitor;
	}

	public void setGoalsVisitor(int goalsVisitor) {
		this.goalsVisitor = goalsVisitor;
	}

	public Date getDateMatch() {
		return dateMatch;
	}

	public void setDateMatch(Date dateMatch) {
		this.dateMatch = dateMatch;
	}

	public int getSpectators() {
		return spectators;
	}

	public void setSpectators(int spectators) {
		this.spectators = spectators;
	}

}
