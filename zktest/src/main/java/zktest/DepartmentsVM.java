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

public class DepartmentsVM {
	
	private Department currentDepartment = null;
	private boolean edit = false;

	public List<Department> getDepartments() {
		EntityManager em = DesktopEntityManagerManager.getDesktopEntityManager();
		return em.createQuery("select d from Department d",Department.class).getResultList();
		
		
	}
	
	@Command
	@NotifyChange("departments")
	public void delete(@BindingParam("department")Department d){
		EntityManager em = DesktopEntityManagerManager.getDesktopEntityManager();
		TransactionUtil.doTransaction(new Transaction() {
			@Override
			public void run(EntityManager em) {
				List<Employee> employees = em.createQuery("SELECT e FROM Employee e WHERE e.department.name='"+d.getName()+"'", Employee.class).getResultList();
				for (Employee employee : employees) {
					employee.setDepartment(null);
				}
				em.remove(d);
			}
		}, em);
		
	}
	
	@DependsOn("departments")
	public int getCount(){
		
		return this.getDepartments().size();
	}
	
	public Department getCurrentDepartment(){
		return currentDepartment;
	}
	
	@Command
	@NotifyChange("currentDepartment")
	public void newDepartment(){
		this.currentDepartment = new Department();
		this.edit = false;
	}
	
	@Command
	@NotifyChange("currentDepartment")
	public void cancel(){
		this.currentDepartment = null;
	}
	
	@Command
	@NotifyChange({"currentDepartment","departments"})
	public void save(){
		EntityManager em = DesktopEntityManagerManager.getDesktopEntityManager();
		TransactionUtil.doTransaction(new Transaction() {
			@Override
			public void run(EntityManager em) {
				if(!edit)
				{
					em.persist(currentDepartment);
				}
			}
		}, em);
		this.currentDepartment = null;
	}
	
	@Command
	@NotifyChange("currentDepartment")
	public void edit(@BindingParam("department")Department d){
		this.currentDepartment = d;
		this.edit = true;
			
	}
	
}
