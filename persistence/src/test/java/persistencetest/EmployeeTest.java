package persistencetest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import persistencetest.util.Transaction;
import persistencetest.util.TransactionUtil;

public class EmployeeTest {
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
	public void testCreateEmployee() {

		EntityManager em = emf.createEntityManager();

		Employee empleado = new Employee();
		empleado.setName("pepe");
		empleado.setSurname("perez");
		TransactionUtil.doTransaction(new Transaction() {
			@Override
			public void run(EntityManager em) {
				em.persist(empleado);
			}
		}, em);

		Employee empleadoRecuperado = em.createQuery("SELECT e FROM Employee e WHERE e.name = 'pepe'", Employee.class)
				.getSingleResult();
		assertEquals("perez", empleadoRecuperado.getSurname());
	}

	@Test
	public void testUpdateEmployee() {
		EntityManager em = emf.createEntityManager();
		Employee empleado = new Employee();

		empleado.setName("Ruben");
		empleado.setSurname("Anido");

		TransactionUtil.doTransaction(new Transaction() {
			@Override
			public void run(EntityManager em) {
				em.persist(empleado);
			}
		}, em);

		TransactionUtil.doTransaction(new Transaction() {
			@Override
			public void run(EntityManager em) {
				Employee empleadoRecuperado = em
						.createQuery("SELECT e FROM Employee e WHERE e.name = 'Ruben'", Employee.class)
						.getSingleResult();
				assertEquals("Anido", empleadoRecuperado.getSurname());

				empleadoRecuperado.setSurname("Gonzalez");
			}
		}, em);

		Employee empleadoRecuperadoModificado = em
				.createQuery("SELECT e FROM Employee e WHERE e.name='Ruben'AND e.surname='Gonzalez'", Employee.class)
				.getSingleResult();

		assertFalse(empleadoRecuperadoModificado == null);
	}
}
