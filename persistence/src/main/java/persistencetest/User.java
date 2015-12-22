package persistencetest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue
	private int id; // identificador de usuario
	private String username; // nombre de usuario
	private String password; // contrase�a de usuario

	/**
	 * m�todo observador del id
	 * 
	 * @return
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * m�todo observador de username
	 * 
	 * @return
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * m�todo modificador de username
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * m�todo observador de password
	 * 
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * m�todo modificador de password
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}