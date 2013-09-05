package blog.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 			Bloggprojektet
 * 		 
 * @author 	 
 * 			Daniel Bergh nit12dbg@student.hig.se 810918
 * 			Joakim Hedman nit12jhn@student.hig.se 901217
 * 			Kristian Johansson ndi11@student.hig.se 910927
 * 			Stefan Wistbacka nit12swa@student.hig.se 850620
 */


/**
 * 
 * singeltonklass som skapar en databaskoppling
 *
 */
public class DatabasKoppling {


	private static EntityManagerFactory emf = null; // kopplingen till databasen
	private static DatabasKoppling dbKoppling = null;
	
	private DatabasKoppling(){ 
	}
	
	public static DatabasKoppling getInstance(){ 
		if(dbKoppling == null){
			dbKoppling = new DatabasKoppling();
		}
		return dbKoppling; 
	}
   /**
    * 
    * Kollar om det finns en entitymanager
    * finns ingen skapas en ny databas
    */
	public EntityManager getEntityManager(){
		if(emf == null){
			emf = Persistence.createEntityManagerFactory("databas.odb"); 
		}
		return emf.createEntityManager(); 
	}

}
