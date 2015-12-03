package persistencetest;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AppToDeleteNowDontForget {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("si-database");

	}

}
