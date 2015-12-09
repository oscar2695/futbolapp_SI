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
		partido.setNameTeamLocal("Real Madrid");
		partido.setNameTeamVisitor("Barcelona");
		partido.setGoalsLocal(5);
		partido.setGoalsLocal(1);
		partido.setDateMatch(new Date());
		partido.setStadium("Santiago Bernabeu");
		partido.setSpectators(83563);
		
		TransactionUtil.doTransaction(new Transaction() {
			@Override
			public void run(EntityManager em) {
				em.persist(partido);
			}
		}, em);

		Match partidoRecuperado = em.createQuery("SELECT p FROM Match p WHERE p.nameTeamLocal='Real Madrid'", Match.class)
				.getSingleResult();
		assertEquals("Real Madrid", partidoRecuperado.getNameTeamLocal());
	}


}
