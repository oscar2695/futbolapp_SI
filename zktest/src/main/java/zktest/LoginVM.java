package zktest;

import javax.persistence.EntityManager;

import org.zkoss.bind.annotation.Command;

import persistencetest.User;
import zktest.jpa.DesktopEntityManagerManager;

public class LoginVM {
	
	private User currentUser = null;
	private String usuario;
	private String password;
	
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public String getUsuario() {
        return usuario;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPassword() {
        return password;
    }

	@Command
	public void login(){
		EntityManager em = DesktopEntityManagerManager.getDesktopEntityManager();
		
		currentUser  = em.createQuery("SELECT e FROM User e WHERE e.username = '"+ this.getUsuario() + "'", User.class)
				.getSingleResult();
		
		if(currentUser.getPassword()=="admin"){
			System.out.println("La contraseña es correcta");
		}
		
	}
	
}