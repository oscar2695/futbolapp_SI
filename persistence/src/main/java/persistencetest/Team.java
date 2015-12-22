package persistencetest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Team {
	@Id
	@GeneratedValue
	private int id; // identificador de un equipo
	private String name; // nombre del equipo

	@OneToOne
	private Stadium stadium;// estadio que posee dicho equipo

	/**
	 * método observador de name
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * método modificador de name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * método observador de id
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * método modficador de id
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * método observador de statium
	 * 
	 * @return
	 */
	public Stadium getStadium() {
		return stadium;
	}

	/**
	 * método modificador de estadium
	 * 
	 * @param stadium
	 */
	public void setStadium(Stadium stadium) {
		this.stadium = stadium;
	}

}
