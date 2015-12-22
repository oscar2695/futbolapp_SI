package zktest;

import java.util.List;

import javax.persistence.EntityManager;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.DependsOn;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Window;

import persistencetest.Match;
import persistencetest.Team;
import persistencetest.User;
import persistencetest.util.Transaction;
import persistencetest.util.TransactionUtil;
import zktest.jpa.DesktopEntityManagerManager;

public class MatchesVM {

	private Match currentMatch = null;//partido actual
	private User currentUser = null;//usuario actual
	private boolean edit = false;//editable o no

	/**
	 * método que permite devolver el listado de partidos de la base de datos
	 * 
	 * @return lista de partidos
	 */
	public List<Match> getMatches() {
		EntityManager em = DesktopEntityManagerManager.getDesktopEntityManager();
		return em.createQuery("SELECT e FROM Match e", Match.class).getResultList();
	}

	/**
	 * método que permite devolver el listado de equipos de la base de datos
	 * 
	 * @return lista de equipos
	 */
	public List<Team> getTeams() {
		EntityManager em = DesktopEntityManagerManager.getDesktopEntityManager();
		return em.createQuery("select t from Team t", Team.class).getResultList();
	}

	/**
	 * método que permite borrar un partido m de la base de datos
	 * 
	 * @param m
	 */
	@Command
	@NotifyChange("matches")
	public void delete(@BindingParam("match") Match m) {
		EntityManager em = DesktopEntityManagerManager.getDesktopEntityManager();
		TransactionUtil.doTransaction(new Transaction() {
			@Override
			public void run(EntityManager em) {
				em.remove(m);
			}
		}, em);

	}

	/**
	 * método que permite contabilizar el número de partidos
	 * 
	 * @return
	 */
	@DependsOn("matches")
	public int getCount() {

		return this.getMatches().size();
	}

	/**
	 * constructor de la clase
	 */
	@Command
	@NotifyChange("currentMatch")
	public void newMatch() {
		this.currentMatch = new Match();
		this.edit = false;
	}

	/**
	 * método observador de la variable currentMatch de tipo Match
	 * 
	 * @return
	 */
	public Match getCurrentMatch() {
		return currentMatch;
	}

	/**
	 * método que iguala a null la variable currentMatch
	 */
	@Command
	@NotifyChange("currentMatch")
	public void cancelMatch() {
		this.currentMatch = null;
	}

	/**
	 * método que permite insertar un partido
	 */
	@Command
	@NotifyChange({ "currentMatch", "matches" })
	public void save() {
		EntityManager em = DesktopEntityManagerManager.getDesktopEntityManager();
		TransactionUtil.doTransaction(new Transaction() {
			@Override
			public void run(EntityManager em) {
				if (!edit) {
					em.persist(currentMatch);
				}
			}
		}, em);
		this.currentMatch = null;
	}

	/**
	 * método que permite editar un partido que se le pasa como parámetro m
	 * 
	 * @param m
	 */
	@Command
	@NotifyChange("currentMatch")
	public void editMatch(@BindingParam("match") Match m) {
		this.currentMatch = m;
		this.edit = true;
	}

	/**
	 * método observador de currentUser
	 * 
	 * @return
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * método que permite crear un nuevo usuario
	 */
	@Command
	@NotifyChange("currentUser")
	public void newUser() {
		this.currentUser = new User();
		this.edit = false;
	}

	/**
	 * método que elimina el valor al que está iniciada la variable currentUser
	 */
	@Command
	@NotifyChange("currentUser")
	public void cancelUser() {
		this.currentUser = null;
	}

	/**
	 * método que permite loguearse en el sistema realizando na comprobación de
	 * si ese usuario con dicha contraseña existe en la base de datos
	 */
	@Command
	public void login() {
		EntityManager em = DesktopEntityManagerManager.getDesktopEntityManager();

		User userbd = em
				.createQuery("SELECT e FROM User e WHERE e.username = '" + currentUser.getUsername() + "'", User.class)
				.getSingleResult();

		if (userbd.getPassword().equals(currentUser.getPassword())) {
			Executions.getCurrent().sendRedirect("admin_menu.zul");
		}

	}
	/**
	 * metodo que permite cerrar una ventana x pasada como parámetro
	 * @param x
	 */
	@Command
	public void cancel(@BindingParam("cmp")  Window x) {
	    x.detach();
	    Executions.getCurrent().sendRedirect("index.zul");
	}
	
	@Command
	public void logout()
	{
		Executions.getCurrent().sendRedirect("index.zul");
	}
	
	
}
