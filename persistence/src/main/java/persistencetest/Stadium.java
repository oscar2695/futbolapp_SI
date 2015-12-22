package persistencetest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Stadium {
	@Id
	@GeneratedValue
	private int id;// identificador del estadio

	private String name;// nombre del estadio
	private int capacity; // capacidad del estadio

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
	 * m�todo observador de capacity
	 * 
	 * @return
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * m�todo modificador de capacity
	 * 
	 * @param capacity
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
}