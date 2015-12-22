package persistencetest;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import persistencetest.util.Transaction;
import persistencetest.util.TransactionUtil;

public class MatchTest {
	private static EntityManagerFactory emf;
/**
 * Antes de realizar los Test se crea el Entity Manager Factory para tener acceso a la base de datos.
 */
	@BeforeClass
	public static void createEntityManagerFactory() {
		emf = Persistence.createEntityManagerFactory("si-database");
	}
/**
 * Método que cierra el Entity Manager cuando acaban los tests.
 */
	@AfterClass
	public static void closeEntityManagerFactory() {
		emf.close();
	}
/**
 * Test en el que se prueba el crear de un partido.
 * Para crear un partido se declara un nuevo partido, dos equipos
 * Comprueba que el partido que se recoge de la base de datos es el mismo que se acaba de crear.
 */
	@Test
	public void testCreateMatch() {

		EntityManager em = emf.createEntityManager();

		Match partido = new Match();
		Team equipoLocal = new Team();
		equipoLocal.setName("Real Madrid");
		Team equipoVisitante = new Team();
		equipoVisitante.setName("Barcelona");
		partido.setTeamLocal(equipoLocal);
		partido.setTeamVisitor(equipoVisitante);
		partido.setGoalsLocal(5);
		partido.setGoalsLocal(1);
		partido.setDateMatch(new Date());
		partido.setSpectators(83563);
		
		TransactionUtil.doTransaction(new Transaction() {
			@Override
			public void run(EntityManager em) {
				em.persist(equipoLocal);
			}
		}, em);
		
		TransactionUtil.doTransaction(new Transaction() {
			@Override
			public void run(EntityManager em) {
				em.persist(equipoVisitante);
			}
		}, em);
		
		TransactionUtil.doTransaction(new Transaction() {
			@Override
			public void run(EntityManager em) {
				em.persist(partido);
			}
		}, em);

		Match partidoRecuperado = em.createQuery("SELECT p FROM Match p WHERE p.teamLocal.name='Real Madrid'", Match.class)
				.getSingleResult();
		assertEquals("Barcelona", partidoRecuperado.getTeamVisitor().getName());
	}


}
