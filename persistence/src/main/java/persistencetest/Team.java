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
	 * m�todo observador de name
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * m�todo modificador de name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * m�todo observador de id
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * m�todo modficador de id
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * m�todo observador de statium
	 * 
	 * @return
	 */
	public Stadium getStadium() {
		return stadium;
	}

	/**
	 * m�todo modificador de estadium
	 * 
	 * @param stadium
	 */
	public void setStadium(Stadium stadium) {
		this.stadium = stadium;
	}

}
