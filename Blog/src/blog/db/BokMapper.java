/**
 * 
 */
package blog.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import blog.data.Bok;

/**
 * 			Bokprojektet
 * 		 
 * @author 	Dennis Hedlund nit12dhd@student.hig.se 780408
 * 			Daniel Bergh nit12dbg@student.hig.se 810918
 * 			Patrik Eskilsson nit12pen@student.hig.se 920707
 */

/**
 * 
 * Denna klass skapar alla metoder 
 * för att hantera böcker mot databasen
 *
 */

public class BokMapper {

	private static BokMapper BokMapper = new BokMapper();
	private static DatabasKoppling dbKoppling;
	Scanner scanner = new Scanner(System.in, "UTF-8"/*encoding*/);
	
	/**
	 * konstruktor som är private därför man bara ska kunna nå den via
	 * getInstance() så man bara kan skapa ett objekt av BokMapper.
	 * Singelton-klass.
	 */
	private BokMapper(){
		dbKoppling = DatabasKoppling.getInstance();
	}
	
	/**
	 * skapar en instans av BokMapper.
	 */
	public static BokMapper getInstance(){
		return BokMapper;
	}
	

/**
 * Metoden för att registrera en bok till databasen
 */
		public void skapaBok(){
	
			System.out.print("ange titel:");
			String titel = scanner.nextLine();
			System.out.print("ange författare:");
			String författare = scanner.nextLine(); 
			System.out.print("ange utgivningsår:");
			int utgivningsår = scanner.nextInt();
			Bok nyBok = new Bok(titel, utgivningsår, författare);
			
			
			EntityManager em = dbKoppling.getEntityManager();
			em.getTransaction().begin();
			try {
			em.persist(nyBok);
			em.getTransaction().commit();
			} finally {
				if (em.getTransaction().isActive())
					em.getTransaction().rollback();
			}
			em.close();
			System.out.println("vi har sparat en bok i db!");
		}
		
		
		/**
		 * Metoden för att lista böcker från databasen.
		 */
		public static void listaBok(){
			
			EntityManager em = dbKoppling.getEntityManager();
			
			List<Bok> bokLista = new ArrayList<Bok>();
			em.getTransaction().begin();
			try {
			TypedQuery<Bok> q = em.createQuery("SELECT bok FROM Bok bok", Bok.class);
			
			bokLista = q.getResultList();
			em.getTransaction().commit();
			} finally {
				if (em.getTransaction().isActive())
					em.getTransaction().rollback();
			}
			em.close();
			System.out.format("%nBöcker i biblioteket:%n");
			for(Bok bok : bokLista){
				System.out.println(bok.toString());
			}	
		}

		/**
		 * Vår metod för att kunna 
		 * redigera data för en befintlig bok
		 */
		
		public void uppdateraBok() {
			System.out.format("%n*** ÄNDRA DATA PÅ BOK ***%n");
			listaBok();
			System.out.format("%nINPUT>> Ange id på bok du vill ändra data på: ");
			
			//Integer.parseINT används för att undvika problem med blandning
			// av scanner av int och strings
			// för annars kan den hoppa över fält
			int bokID = Integer.parseInt(scanner.nextLine().trim());
			
			EntityManager em = dbKoppling.getEntityManager();
			em.getTransaction().begin();
			try {
			Query q = em.createQuery("SELECT p FROM Bok p WHERE id =" + bokID);

			Bok B1 = (Bok) q.getSingleResult();
			System.out.format("%n%nDu ändrar nu data på följande bok:%n");
			System.out.println(B1.toString() + "\n");
			
			System.out.print("ange ny titel: ");
			String titel = scanner.nextLine().trim();  
			System.out.print("ange ny författare: ");
			String författare = scanner.nextLine().trim(); 
			System.out.print("ange nytt utgivningsår: ");
			int utgivningsår = scanner.nextInt();
			B1.setTitel(titel);
			B1.setUtgivningsår(utgivningsår);
			B1.setFörfattare(författare);
			
			
			em.getTransaction().commit();
			} finally {
				if (em.getTransaction().isActive())
					em.getTransaction().rollback();
			}
			em.close();
			System.out.println("Information om bok uppdaterad.");
		}

		/**
		 * Metoden för att radera en bok ur databasen
		 */
		public void raderaBok() {
			
			System.out.format("%n*** RADERA BOK ***%n");
			listaBok();
			System.out.format("%nINPUT>> Ange id på boken du vill radera: ");
			
			
			int bokID = scanner.nextInt();
			
			EntityManager em = dbKoppling.getEntityManager();
			em.getTransaction().begin();
			try {
			
			   Query q = em.createQuery("SELECT p FROM Bok p WHERE id ="+bokID);
			
				    Bok user = (Bok) q.getSingleResult();
				    em.remove(user);
			
			em.getTransaction().commit();
			} finally {
				if (em.getTransaction().isActive())
					em.getTransaction().rollback();
			}
			em.close();
		}


	}