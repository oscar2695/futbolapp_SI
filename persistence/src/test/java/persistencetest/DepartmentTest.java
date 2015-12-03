package persistencetest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import persistencetest.util.Transaction;
import persistencetest.util.TransactionUtil;

public class DepartmentTest {
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
	public void testCreateDepartment() {

		EntityManager em = emf.createEntityManager();

		Department departamento = new Department();
		departamento.setName("contabilidad");
		TransactionUtil.doTransaction(new Transaction() {
			@Override
			public void run(EntityManager em) {
				em.persist(departamento);
			}
		}, em);

		Department departamentoRecuperado = em.createQuery("SELECT e FROM Department e WHERE e.name = 'contabilidad'", Department.class)
				.getSingleResult();
		assertEquals("contabilidad", departamentoRecuperado.getName());
	}
	
	@Test
	public void createDepartmentWithEmployeeTest()
	{
		EntityManager em = emf.createEntityManager();
		
		Department departamento = new Department();
		departamento.setName("finanzas");
		TransactionUtil.doTransaction(new Transaction() {
			@Override
			public void run(EntityManager em) {
				em.persist(departamento);
			}
		}, em);

		Department departamentoRecuperado = em.createQuery("SELECT e FROM Department e WHERE e.name = 'finanzas'", Department.class)
				.getSingleResult();
		assertEquals("finanzas", departamentoRecuperado.getName());
		
		Employee emp = new Employee();
		emp.setName("Manolo");
		emp.setDepartment(departamentoRecuperado);
		
		TransactionUtil.doTransaction(new Transaction() {
			@Override
			public void run(EntityManager em) {
				em.persist(emp);
			}
		}, em);
		
		Department departamentoManolo = em.createQuery("SELECT e.department FROM Employee e WHERE e.name = 'Manolo'", Department.class)
				.getSingleResult();
		assertEquals("finanzas", departamentoManolo.getName());
	}
	
	@Test
	public void createAndDeleteDepartmentTest(){
EntityManager em = emf.createEntityManager();
		
		Department departamento = new Department();
		departamento.setName("RRHH");
		TransactionUtil.doTransaction(new Transaction() {
			@Override
			public void run(EntityManager em) {
				em.persist(departamento);
			}
		}, em);

		Department departamentoRecuperado = em.createQuery("SELECT e FROM Department e WHERE e.name = 'RRHH'", Department.class)
				.getSingleResult();
		assertEquals("RRHH", departamentoRecuperado.getName());
		
		Employee emp = new Employee();
		emp.setName("Juancho");
		emp.setDepartment(departamentoRecuperado);
		
		TransactionUtil.doTransaction(new Transaction() {
			@Override
			public void run(EntityManager em) {
				em.persist(emp);
			}
		}, em);
		
		
		Department departamentoManolo = em.createQuery("SELECT e.department FROM Employee e WHERE e.name = 'Juancho'", Department.class)
				.getSingleResult();
		assertEquals("RRHH", departamentoManolo.getName());
		
		TransactionUtil.doTransaction(new Transaction() {
			@Override
			public void run(EntityManager em) {
				List<Employee> empleadosDepartamentoManolo = em.createQuery("SELECT e FROM Employee e WHERE e.department.name='"+departamentoManolo.getName()+"'", Employee.class).getResultList();
				for (Employee employee : empleadosDepartamentoManolo) {
					employee.setDepartment(null);
				}
				em.remove(departamentoManolo);
			}
		}, em);
		
		int departamentosRRHH = em.createQuery("SELECT d FROM Department d WHERE d.name='RRHH'", Department.class).getResultList().size();
		
		assertEquals(0, departamentosRRHH);
	}
	
	@Test
	public void getEmployeesTest(){
		EntityManager em = emf.createEntityManager();
		
		Department departamento = new Department();
		departamento.setName("soporte");
		TransactionUtil.doTransaction(new Transaction() {
			@Override
			public void run(EntityManager em) {
				em.persist(departamento);
			}
		}, em);

		Department departamentoRecuperado = em.createQuery("SELECT e FROM Department e WHERE e.name = 'soporte'", Department.class)
				.getSingleResult();
		assertEquals("soporte", departamentoRecuperado.getName());
		
		Employee emp = new Employee();
		emp.setName("David");
		emp.setDepartment(departamentoRecuperado);
		
		TransactionUtil.doTransaction(new Transaction() {
			@Override
			public void run(EntityManager em) {
				em.persist(emp);
			}
		}, em);
		
		Department departamentoManolo = em.createQuery("SELECT e.department FROM Employee e WHERE e.name = 'David'", Department.class)
				.getSingleResult();
		assertEquals("soporte", departamentoManolo.getName());
		
		System.out.println(departamentoManolo + " " + departamentoRecuperado);
		
		assertEquals(1,departamentoManolo.getEmployees().size());
	}
}
