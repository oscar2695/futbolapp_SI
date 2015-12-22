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
 
 */
	@Test
	public void testCreateTeam() {

		EntityManager em = emf.createEntityManager();

		Team equipo = new Team();
		equipo.setName("Real Madrid");
		
		Stadium estadio = new Stadium();
		estadio.setName("Santiago Bernabeu");
		estadio.setCapacity(89000);
		
		equipo.setStadium(estadio);
		
		TransactionUtil.doTransaction(new Transaction() {
			@Override
			public void run(EntityManager em) {
				em.persist(estadio);
			}
		}, em);
		
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
