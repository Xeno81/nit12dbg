package blog.ws;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import se.jxn.data.Person;

/**
 * Servlet implementation class PersonServlet
 */
public class PersonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Logger l = Logger.getLogger(PersonServlet.class.getCanonicalName());
    	l.info("entering doGet(): ");
		EntityManagerFactory emf =
			(EntityManagerFactory)getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();
		
		try {
			String reload = request.getParameter("reload");
			if (reload != null && ! reload.isEmpty())
				l.info("RELOAD: "+reload);
			String name = request.getParameter("namn");
			if (name != null && ! name.isEmpty()) {
				l.info("NAME: "+name);
				em.getTransaction().begin();
				em.persist(new Person(name));
				em.getTransaction().commit();
			}
			String remove = request.getParameter("remove");
			if (remove != null && ! remove.isEmpty()) {
				l.info("REMOVE: "+remove);
				long id = Long.parseLong(remove);
				em.getTransaction().begin();
				Person p = em.find(Person.class, id);
				if (p != null)
					em.remove(p);
				em.getTransaction().commit();
			}
			List<Person> personList = 
				em.createQuery("SELECT p FROM Person p", Person.class)
					.getResultList();
			request.setAttribute("personer", personList);
			request.getRequestDispatcher("/personer.jsp")
				.forward(request, response);
		} finally {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
				l.info("Error: rollback");
			}
			em.close();
		}
		l.info("Exit doGet()");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Logger l = Logger.getLogger(PersonServlet.class.getCanonicalName());
		l.info("Entering doPost()");
		doGet(request, response);
		l.info("Exiting doPost()");
	}
}
