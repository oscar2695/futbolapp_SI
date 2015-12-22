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
 * Test que comprueba la creación de un nuevo equipo y su guardado enla base de datos.
 * Para ello se crea un nuevo usuario.
 * Se persiste y se comprueba que el equipo recuperado es el mismo que se crea en el método.
 */
	@Test
	public void testCreateUser() {

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
