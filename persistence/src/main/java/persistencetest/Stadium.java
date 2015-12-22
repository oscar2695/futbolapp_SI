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
	 * método observador de capacity
	 * 
	 * @return
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * método modificador de capacity
	 * 
	 * @param capacity
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
}