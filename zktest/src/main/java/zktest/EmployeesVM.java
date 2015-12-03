package zktest;

import java.util.List;

import javax.persistence.EntityManager;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.DependsOn;
import org.zkoss.bind.annotation.NotifyChange;

import persistencetest.Department;
import persistencetest.Employee;
import persistencetest.util.Transaction;
import persistencetest.util.TransactionUtil;
import zktest.jpa.DesktopEntityManagerManager;

public class EmployeesVM {
	
	private Employee currentEmployee = null;
	private boolean edit = false;

	public List<Employee> getEmployees() {
		EntityManager em = DesktopEntityManagerManager.getDesktopEntityManager();
		return em.createQuery("select e from Employee e", Employee.class).getResultList();
		
		
	}
	
	public List<Department> getDepartments() {
		EntityManager em = DesktopEntityManagerManager.getDesktopEntityManager();
		return em.createQuery("select d from Department d",Department.class).getResultList();
	}
	
	@Command
	@NotifyChange("employees")
	public void delete(@BindingParam("employee") Employee e){
		EntityManager em = DesktopEntityManagerManager.getDesktopEntityManager();
		TransactionUtil.doTransaction(new Transaction() {
			@Override
			public void run(EntityManager em) {
				em.remove(e);
			}
		}, em);
		
	}
	
	@DependsOn("employees")
	public int getCount(){
		
		return this.getEmployees().size();
	}
	
	public Employee getCurrentEmployee(){
		return currentEmployee;
	}
	
	@Command
	@NotifyChange("currentEmployee")
	public void newEmployee(){
		this.currentEmployee = new Employee();
		this.edit = false;
	}
	
	@Command
	@NotifyChange("currentEmployee")
	public void cancel(){
		this.currentEmployee = null;
	}
	
	@Command
	@NotifyChange({"currentEmployee","employees"})
	public void save(){
		EntityManager em = DesktopEntityManagerManager.getDesktopEntityManager();
		TransactionUtil.doTransaction(new Transaction() {
			@Override
			public void run(EntityManager em) {
				if(!edit)
				{
					em.persist(currentEmployee);
				}
			}
		}, em);
		this.currentEmployee = null;
	}
	
	@Command
	@NotifyChange("currentEmployee")
	public void edit(@BindingParam("employee") Employee e){
		this.currentEmployee = e;
		this.edit = true;
			
	}
	
}
