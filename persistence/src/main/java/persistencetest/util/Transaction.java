package persistencetest.util;

import javax.persistence.EntityManager;

public interface Transaction {
/**
 * 
 * @param em
 */
	public void run(EntityManager em);
}
