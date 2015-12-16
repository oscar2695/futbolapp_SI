package zktest;

import java.util.List;  

import javax.persistence.EntityManager;



import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.DependsOn;
import org.zkoss.bind.annotation.NotifyChange;
import persistencetest.Team;
import persistencetest.util.Transaction;
import persistencetest.util.TransactionUtil;
import zktest.jpa.DesktopEntityManagerManager;

public class TeamsVM {
	
	private Team currentTeam = null;
	private boolean edit = false;

	public List<Team> getTeams() {
		EntityManager em = DesktopEntityManagerManager.getDesktopEntityManager();
		return em.createQuery("select t from Team t",Team.class).getResultList();
		
		
	}
	
	@DependsOn("teams")
	public int getCount(){
		return this.getTeams().size();
	}
	
	public Team getCurrentTeam(){
		return currentTeam;
	}
	
	@Command
	@NotifyChange("currentTeam")
	public void newTeam(){
		this.currentTeam = new Team();
		this.edit = false;
	}
	
	@Command
	@NotifyChange("currentTeam")
	public void cancelTeam(){
		this.currentTeam = null;
	}
	
	@Command
	@NotifyChange({"currentTeam","teams"})
	public void saveTeam(){
		EntityManager em = DesktopEntityManagerManager.getDesktopEntityManager();
		TransactionUtil.doTransaction(new Transaction() {
			@Override
			public void run(EntityManager em) {
				if(!edit)
				{
					em.persist(currentTeam);
				}
			}
		}, em);
		this.currentTeam = null;
	}
	
	@Command
	@NotifyChange("currentTeam")
	public void editTeam(@BindingParam("team")Team t){
		this.currentTeam = t;
		this.edit = true;	
	}
}
