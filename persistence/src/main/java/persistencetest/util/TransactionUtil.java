package persistencetest.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class TransactionUtil {
	/**
	 * método que permite realizar una transacción sobre un entityManager
	 * recibiendo como parámetros la transacción t que se va a realizar y el
	 * entityManager que se usará
	 * 
	 * @param t
	 * @param em
	 */
	public static void doTransaction(Transaction t, EntityManager em) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			t.run(em);
			tx.commit();
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
	}

}
