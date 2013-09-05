/**
 * 
 */
package blog.db;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.*;

import blog.data.Låntagare;

/**
 * 			Bokprojektet
 * 		 
 * @author 	Dennis Hedlund nit12dhd@student.hig.se 780408
 * 			Daniel Bergh nit12dbg@student.hig.se 810918
 * 			Patrik Eskilsson nit12pen@student.hig.se 920707
 */
/**
 * 
 * Klassen där låntagare skrivs till databasen.
 *
 */
public class LåntagareMapper {
	private static LåntagareMapper låntagareMapper = new LåntagareMapper();
	private static DatabasKoppling dbKoppling;
	Scanner scanner = new Scanner(System.in, "UTF-8"/*encoding*/);
	final static Charset ENCODING = StandardCharsets.UTF_8;
	
	/**
	 * konstruktor som är private därför man bara ska kunna nå den via
	 * getInstance() så man bara kan skapa ett objekt av LåntagareMapper.
	 * Singelton-klass.
	 */
	
	private LåntagareMapper(){
		dbKoppling = DatabasKoppling.getInstance();
	}
	/**
	 * skapar en instans av LåntagareMapper.
	 */
	public static LåntagareMapper getInstance(){
		return låntagareMapper;
	}
	
	/**
	 * Metoden för att lagra en låntagare i databasen
	 */
	public void skapaLåntagare(){
		System.out.print("ange förnamn:");
		String förnamn = scanner.nextLine();
		System.out.print("ange efternamn:");
		String efternamn = scanner.nextLine();
		System.out.print("ange adress:");
		String adress = scanner.nextLine();
		System.out.print("ange telefon nr:");
		String telefonnummer = scanner.nextLine(); 
		System.out.print("ange email:");
		String email = scanner.nextLine();
		Låntagare nyLåntagare = new Låntagare(telefonnummer, adress, förnamn, efternamn, email);
		
		
		EntityManager em = dbKoppling.getEntityManager();
		em.getTransaction().begin();
		try {
		em.persist(nyLåntagare);
		em.getTransaction().commit();
		} finally {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
		}
		em.close();
		System.out.println("Låntagare lagrad i databasen!");
	}
	/**
	 * Metoden för att lista en låntagare från databasen.
	 */
	public static void listaLåntagare(){
		
		EntityManager em = dbKoppling.getEntityManager();
		
		List<Låntagare> låntagareLista = new ArrayList<Låntagare>();
		em.getTransaction().begin();
		try {
		//Frågeställningen för att hämta låntagar-tabellen
		TypedQuery<Låntagare> q = em.createQuery("SELECT låntagare FROM Låntagare låntagare", Låntagare.class);
		
		låntagareLista = q.getResultList();
		em.getTransaction().commit();
		} finally {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
		}
		em.close();
		System.out.format("%nRegistrerade Låntagare:%n");
		for(Låntagare person : låntagareLista){
			System.out.println(person.toString());
			
			
			
			  
			 
		}	
	}
	/**
	 * Metod för att redigera en låntagare
	 */
	public void uppdateraLåntagare() {
		System.out.format("%n*** ÄNDRA DATA PÅ LÅNTAGARE ***%n");
		listaLåntagare();
		System.out.format("%nINPUT>> Ange id på låntagare du vill ändra data på: ");
		
		//parseInt används för att undvika problem med blandning av int och stränginmatning
		int låntagareID = Integer.parseInt(scanner.nextLine().trim());
		
		EntityManager em = dbKoppling.getEntityManager();
		em.getTransaction().begin();
		try {
		Query q = em.createQuery("SELECT p FROM Låntagare p WHERE id =" + låntagareID);

		Låntagare L1 = (Låntagare) q.getSingleResult();
		System.out.format("%n%nDu ändrar nu data på följande person:%n");
		System.out.println(L1.toString() + "\n");
		
		System.out.print("ange nytt förnamn:");
		String förnamn = scanner.nextLine().trim();  
		System.out.print("ange nytt efternamn:");
		String efternamn = scanner.nextLine().trim(); 
		System.out.print("ange ny adress:");
		String adress = scanner.nextLine().trim();
		System.out.print("ange nytt telefon nr:");
		String telefonnummer = scanner.nextLine().trim(); 
		System.out.print("ange ny email:");
		String mail = scanner.nextLine().trim();
		L1.setFörnamn(förnamn);
		L1.setEfternamn(efternamn);
		L1.setAdress(adress);
		L1.setTelefonnummer(telefonnummer);
		L1.setEmail(mail);
		
		em.getTransaction().commit();
		} finally {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
		}
		em.close();
		System.out.format("%nLåntagare uppdaterad!%n");
	}

	/**
	 * Metod för att radera en låntagare från databasen.
	 */
	public void raderaLåntagare() {
		
		System.out.format("%n*** RADERA LÅNTAGARE ***%n");
		listaLåntagare();
		System.out.format("%nINPUT>> Ange id på låntagare du vill radera: ");
		
		
		int låntagareID = scanner.nextInt();
		
		EntityManager em = dbKoppling.getEntityManager();
		em.getTransaction().begin();
		try {
		
		   Query q = em.createQuery("SELECT p FROM Låntagare p WHERE id ="+låntagareID);
		
			    Låntagare user = (Låntagare) q.getSingleResult();
			    em.remove(user);
		
		em.getTransaction().commit();
		} finally {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
		}
		em.close();
	}
}
