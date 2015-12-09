package persistencetest;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="match_table")
public class Match {
	@Id
	@GeneratedValue
	private int id;
	private String nameTeamLocal;
	private String nameTeamVisitor;
	private int goalsLocal; 
	private int goalsVisitor;
	private Date dateMatch;
	private String stadium;
	private int spectators;
	
	
	public int getId() {
		return id;
	}

	public String getNameTeamLocal() {
		return nameTeamLocal;
	}

	public void setNameTeamLocal(String nameTeamLocal) {
		this.nameTeamLocal = nameTeamLocal;
	}

	public String getNameTeamVisitor() {
		return nameTeamVisitor;
	}

	public void setNameTeamVisitor(String nameTeamVisitor) {
		this.nameTeamVisitor = nameTeamVisitor;
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

	public String getStadium() {
		return stadium;
	}

	public void setStadium(String stadium) {
		this.stadium = stadium;
	}

	public int getSpectators() {
		return spectators;
	}

	public void setSpectators(int spectators) {
		this.spectators = spectators;
	}

}
