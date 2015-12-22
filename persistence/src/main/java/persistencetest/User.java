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
	private String password; // contraseña de usuario

	/**
	 * método observador del id
	 * 
	 * @return
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * método observador de username
	 * 
	 * @return
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * método modificador de username
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * método observador de password
	 * 
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * método modificador de password
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}