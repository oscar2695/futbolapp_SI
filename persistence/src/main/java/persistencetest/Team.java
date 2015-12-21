package persistencetest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Team {
	@Id @GeneratedValue 
	private int id;
	private String name;
	
	@OneToOne
	private Stadium stadium;
/**
 * 
 * @return
 */
	public String getName() 
	{
		return name;
	}
/**
 * 
 * @param name
 */
	public void setName(String name) 
	{
		this.name = name;
	}
/**
 * 
 * @return
 */
	public int getId() 
	{
		return id;
	}
	/**
	 * 
	 * @param id
	 */
	public void setId(int id) 
	{
		this.id = id;
	}
/**
 * 
 * @return
 */
	public Stadium getStadium() {
		return stadium;
	}
/**
 * 
 * @param stadium
 */
	public void setStadium(Stadium stadium) {
		this.stadium = stadium;
	}
	
}
