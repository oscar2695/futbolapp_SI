package persistencetest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import java.util.Date;

@Entity
public class Employee {
	@Id @GeneratedValue 
	private int id;
	private String name;
	private String surname;
	private Date dateofbirth;
	private double salary;
	@ManyToOne
	private Department department;

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public int getId() 
	{
		return id;
	}
	
	public void setId(int id) 
	{
		this.id = id;
	}
	
	public String getSurname()
	{
		return surname;
	}
	
	public void setSurname(String surname)
	{
		this.surname = surname;
	}
	
	public void setDepartment(Department d)
	{
		if(this.department!=null){
			this.department.internalRemoveEmployee(this);
		}
		this.department = d;
		if(d!=null){
			d.internalAddEmployee(this);
		}
		
	}
	
	public Department getDepartment()
	{
		return this.department;
	}
	
}
