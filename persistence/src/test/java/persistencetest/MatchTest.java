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

	@BeforeClass
	public static void createEntityManagerFactory() {
		emf = Persistence.createEntityManagerFactory("si-database");
	}

	@AfterClass
	public static void closeEntityManagerFactory() {
		emf.close();
	}

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
