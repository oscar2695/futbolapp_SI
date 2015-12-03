package persistencetest;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Department {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	
	@OneToMany(mappedBy="department")
	private List<Employee> employees = new ArrayList<Employee>();
	
	public List<Employee> getEmployees() {
		return employees;
	}
	
	public void addEmployee(Employee e) {
		e.setDepartment(this);
	}
	
	public int getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	void internalAddEmployee(Employee employee) {
		employees.add(employee);
	}
	
	void internalRemoveEmployee(Employee employee) {
		employees.remove(employee);
	}

}
