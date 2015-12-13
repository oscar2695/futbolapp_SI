package zktest;

import java.util.List; 

import javax.persistence.EntityManager;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.DependsOn;
import org.zkoss.bind.annotation.NotifyChange;
import persistencetest.Match;
import persistencetest.util.Transaction;
import persistencetest.util.TransactionUtil;
import zktest.jpa.DesktopEntityManagerManager;

public class MatchesVM {
	
	private Match currentMatch = null;
	private boolean edit = false;

	public List<Match> getMatches() {
		EntityManager em = DesktopEntityManagerManager.getDesktopEntityManager();
		 return em.createQuery("SELECT e FROM Match e",Match.class).getResultList();
		
		
	}
	
	
	@Command
	@NotifyChange("matches")
	public void delete(@BindingParam("match")Match m){
		EntityManager em = DesktopEntityManagerManager.getDesktopEntityManager();
		TransactionUtil.doTransaction(new Transaction() {
			@Override
			public void run(EntityManager em) {
				em.remove(m);
			}
		}, em);
		
	}
	
	@DependsOn("matches")
	public int getCount(){
		
		return this.getMatches().size();
	}
	
	public Match getCurrentMatch(){
		return currentMatch;
	}
	
	@Command
	@NotifyChange("currentMatch")
	public void newMatch(){
		this.currentMatch = new Match();
		this.edit = false;
	}
	
	@Command
	@NotifyChange("currentMatch")
	public void cancel(){
		this.currentMatch = null;
	}
	
	@Command
	@NotifyChange({"currentMatch","matches"})
	public void save(){
		EntityManager em = DesktopEntityManagerManager.getDesktopEntityManager();
		TransactionUtil.doTransaction(new Transaction() {
			@Override
			public void run(EntityManager em) {
				if(!edit)
				{
					em.persist(currentMatch);
				}
			}
		}, em);
		this.currentMatch = null;
	}
	
	@Command
	@NotifyChange("currentMatch")
	public void edit(@BindingParam("match")Match m){
		this.currentMatch = m;
		this.edit = true;	
	}
}
