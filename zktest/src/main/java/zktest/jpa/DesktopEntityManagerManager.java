package zktest.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.DesktopCleanup;

public class DesktopEntityManagerManager {
	private static final String ENTITY_MANAGER_NAME = "__ENTITY_MANAGER__"; //constante
	private static EntityManagerFactory emf = null;//entity manager

	/**
	 * método que devuelve un objeto entity manager la cual permite
	 * interaccionar con el contexto de persistencia
	 * 
	 * @return EntityManager
	 */
	public static EntityManager getDesktopEntityManager() {
		Desktop currentDesktop = Executions.getCurrent().getDesktop();
		if (currentDesktop != null) {
			if (currentDesktop.hasAttribute(ENTITY_MANAGER_NAME)) {
				return (EntityManager) currentDesktop.getAttribute(ENTITY_MANAGER_NAME);
			} else {
				EntityManager newEm = createNewEntityMamanger();
				currentDesktop.setAttribute(ENTITY_MANAGER_NAME, newEm);
				currentDesktop.addListener(new DesktopCleanup() {
					@Override
					public void cleanup(Desktop arg0) throws Exception {
						newEm.close();
					}
				});
				return newEm;
			}
		} else {
			throw new IllegalArgumentException("Desktop not found in this execution");
		}
	}

	/**
	 * método que permite crear un nuevo entityManager
	 * 
	 * @return
	 */
	private static EntityManager createNewEntityMamanger() {
		EntityManagerFactory emf = getOrCreateEntityManagerFactory();
		return emf.createEntityManager();
	}

	/**
	 * método que permite obtener o crear un entityManager, es decir, si no está
	 * creado, lo crea. Y si ya está creado lo inicia
	 * 
	 * @return
	 */
	private static EntityManagerFactory getOrCreateEntityManagerFactory() {
		if (emf != null) {
			return emf;
		}
		final String persistenceUnitName = Executions.getCurrent().getDesktop().getWebApp().getConfiguration()
				.getPreference("persistence-unit-name", "");
		if (persistenceUnitName.equals("")) {
			throw new IllegalArgumentException("no 'persistence-unit-name' preference found in zk.xml");
		} else {
			emf = Persistence.createEntityManagerFactory(persistenceUnitName);
		}
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				emf.close();
			}
		});
		return emf;
	}
}