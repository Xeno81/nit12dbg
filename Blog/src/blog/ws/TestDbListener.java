package blog.ws;

import java.util.logging.Logger;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class TestDbListener
 *
 */
public class TestDbListener implements ServletContextListener {

    public TestDbListener() {
    }

	public void contextInitialized(ServletContextEvent event) {
		Logger l = Logger.getLogger(TestDbListener.class.getCanonicalName());
		l.severe("Context Initialized started");
		com.objectdb.Enhancer.enhance("se.jxn.data.*");
		EntityManagerFactory emf =
			Persistence.createEntityManagerFactory("$objectdb/db/blog.odb");
//			Persistence.createEntityManagerFactory("blog.odb");
		l.info("emf skapade och sparas i attribute emf: "+emf.toString());
		event.getServletContext().setAttribute("emf", emf);
		l.severe("Context Initialized finnished");
	}

	public void contextDestroyed(ServletContextEvent event) {
		Logger l = Logger.getLogger(TestDbListener.class.getCanonicalName());
		l.severe("Context Destroyed started");
		EntityManagerFactory emf =
			(EntityManagerFactory)event.getServletContext().getAttribute("emf");
		if (emf != null)
			emf.close();
		l.severe("Context Destroyde finnished");
	}
}
