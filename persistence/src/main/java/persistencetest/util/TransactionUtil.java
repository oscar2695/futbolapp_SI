package persistencetest.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class TransactionUtil {
	/**
	 * m�todo que permite realizar una transacci�n sobre un entityManager
	 * recibiendo como par�metros la transacci�n t que se va a realizar y el
	 * entityManager que se usar�
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
