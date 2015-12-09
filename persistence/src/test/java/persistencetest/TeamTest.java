package persistencetest;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import persistencetest.util.Transaction;
import persistencetest.util.TransactionUtil;

public class TeamTest {
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
	public void testCreateTeam() {

		EntityManager em = emf.createEntityManager();

		Team equipo = new Team();
		equipo.setName("Real Madrid");
		TransactionUtil.doTransaction(new Transaction() {
			@Override
			public void run(EntityManager em) {
				em.persist(equipo);
			}
		}, em);

		Team departamentoRecuperado = em.createQuery("SELECT e FROM Team e WHERE e.name = 'Real Madrid'", Team.class)
				.getSingleResult();
		assertEquals("Real Madrid", departamentoRecuperado.getName());
	}


}
