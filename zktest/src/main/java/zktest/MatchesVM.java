package zktest;

import java.util.List;

import javax.persistence.EntityManager;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.DependsOn;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;

import persistencetest.Match;
import persistencetest.Team;
import persistencetest.User;
import persistencetest.util.Transaction;
import persistencetest.util.TransactionUtil;
import zktest.jpa.DesktopEntityManagerManager;

public class MatchesVM {
	
	private Match currentMatch = null;
	private User currentUser = null;
	private boolean edit = false;

	
	/**
	 * 
	 * @return
	 */
	public List<Match> getMatches() {
		EntityManager em = DesktopEntityManagerManager.getDesktopEntityManager();
		 return em.createQuery("SELECT e FROM Match e",Match.class).getResultList();	
	}
	/**
	 * 
	 * @return
	 */
	public List<Team> getTeams() {
		EntityManager em = DesktopEntityManagerManager.getDesktopEntityManager();
		return em.createQuery("select t from Team t",Team.class).getResultList();	
	}
	/**
	 * 
	 * @param m
	 */
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
	/**
	 * 
	 * @return
	 */
	@DependsOn("matches")
	public int getCount(){
		
		return this.getMatches().size();
	}
	/**
	 * 
	 * @return
	 */
	public Match getCurrentMatch(){
		return currentMatch;
	}
	/**
	 * 
	 */
	@Command
	@NotifyChange("currentMatch")
	public void newMatch(){
		this.currentMatch = new Match();
		this.edit = false;
	}
	/**
	 * 
	 */
	@Command
	@NotifyChange("currentMatch")
	public void cancelMatch(){
		this.currentMatch = null;
	}
	
	/**
	 * 
	 */
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
	/**
	 * 
	 * @param m
	 */
	@Command
	@NotifyChange("currentMatch")
	public void editMatch(@BindingParam("match")Match m){
		this.currentMatch = m;
		this.edit = true;	
	}
	/**
	 * 
	 * @return
	 */
	public User getCurrentUser(){
		return currentUser;
	}
	/**
	 * 
	 */
	@Command
	@NotifyChange("currentUser")
	public void newUser(){
		this.currentUser = new User();
		this.edit = false;
	}
	/**
	 * 
	 */
	@Command
	@NotifyChange("currentUser")
	public void cancelUser(){
		this.currentUser= null;
	}
	/**
	 * 
	 */
	@Command
	public void login(){
		EntityManager em = DesktopEntityManagerManager.getDesktopEntityManager();
		
		User userbd  = em.createQuery("SELECT e FROM User e WHERE e.username = '" + currentUser.getUsername()+"'", User.class)
				.getSingleResult();
		
		if(userbd.getPassword().equals(currentUser.getPassword())){
			Executions.getCurrent().sendRedirect("admin_menu.zul");
		}

		
	}
}
