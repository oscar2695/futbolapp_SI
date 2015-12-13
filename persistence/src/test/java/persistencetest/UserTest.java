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

public class UserTest {
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

		User usuario = new User();
		usuario.setUsername("admin");
		usuario.setPassword("pass");
		
		TransactionUtil.doTransaction(new Transaction() {
			@Override
			public void run(EntityManager em) {
				em.persist(usuario);
			}
		}, em);

		User usuarioRecuperado = em.createQuery("SELECT e FROM User e WHERE e.username = 'admin'", User.class)
				.getSingleResult();
		assertEquals("admin", usuarioRecuperado.getUsername());
	}


}
