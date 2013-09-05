package blog.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import blog.data.Bok;
import blog.data.BokLån;
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
 * Den klass som sköter skrivningen av Boklåns-objekt till databasen
 *
 */

public class BokLånMapper {

	
	private static BokLånMapper BokLånMapper = new BokLånMapper();
	private static DatabasKoppling dbKoppling;
	private static Scanner scanner = new Scanner(System.in, "UTF-8"/*encoding*/);
	
	/**
	 * konstruktor som är private därför man bara ska kunna nå den via
	 * getInstance() så man bara kan skapa ett objekt av BokLånMapper.
	 * Singelton-klass.
	 */
	private BokLånMapper(){
		dbKoppling = DatabasKoppling.getInstance();
	}
	
	/**
	 * skapar en instans av BokLånMapper.
	 */
	public static BokLånMapper getInstance(){
		return BokLånMapper;
	}
	
	/**
	 * Metoden för att låna en bok som hämtar ID från
	 * de två övriga tabellerna och skriver till denna.
	 *
	 *
	 */
	public static void LånaBok() {
		
		// hämtar tabell för boklista		
		BokMapper.listaBok();
		System.out.print("ange Bok ID:");
		int bokLånID = Integer.parseInt(scanner.nextLine().trim());
		// hämtar tabell för låntagare
		LåntagareMapper.listaLåntagare();
		System.out.print("ange låntagare:");
		int låntagareID = Integer.parseInt(scanner.nextLine().trim());
		// skapar lånobjektet
		BokLån nyBokLån = new BokLån(bokLånID, låntagareID); 
		
		//påbörjar skrivning till databasen
		EntityManager em = dbKoppling.getEntityManager();
		em.getTransaction().begin();
		try {
		em.persist(nyBokLån);
		em.getTransaction().commit();
		} finally {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			}
	// stänger skrivning
		em.close();
		System.out.println("Du har lånat en bok!");
		
	}
	
	/**
	 * Metoden för att lista utlånade böcker.
	 * Vi valde att göra 3 databasförfrågningar.
	 */
	public static void ListaLån() {
		
		EntityManager em = dbKoppling.getEntityManager();
		
		List<BokLån> bokLånLista = new ArrayList<BokLån>();
		em.getTransaction().begin();
		// första förfrågning som hämtar från tabellen boklån
		TypedQuery<BokLån> qBokLån = em.createQuery("SELECT boklån FROM BokLån boklån", BokLån.class);
		bokLånLista = qBokLån.getResultList();
		
		System.out.format("%nUtlånade böcker i biblioteket:%n");
		for(BokLån boklån : bokLånLista){
			
			int bokID = boklån.getBokID();
			// andra förfrågningen för att hämta titel från boktabellen
			TypedQuery<Bok> qBok = em.createQuery("SELECT p FROM Bok p WHERE id =" + bokID, Bok.class);
			Bok B1 = (Bok) qBok.getSingleResult();
			String titel = B1.getTitel();
			
			int LåntagarID = boklån.getLåntagareID();
			// tredje förfårgningen för att hämta namn från låntagartabellen.
			TypedQuery<Låntagare> qLåntagare = em.createQuery("SELECT p FROM Låntagare p WHERE id =" + LåntagarID, Låntagare.class);
			Låntagare L1 = (Låntagare) qLåntagare.getSingleResult();
			String namn = L1.getFörnamn();
			String efternamn = L1.getEfternamn();
			//utskriften för utlånade böcker.
			System.out.println(boklån.toString() + " Låntagare: " + namn + " " + efternamn + " har lånat boken: " + titel);		
		}
	}
	/**
	 * Metoden för att returnera böcker
	 */
	public static void ReturneraBok() {
		
		System.out.format("%n*** LÄMNA TILLBAKA BOK ***%n");
		ListaLån();
		System.out.format("%nINPUT>> Ange id på den bok du vill returnera: ");
		
		
		int BoklånID = scanner.nextInt();
		
		EntityManager em = dbKoppling.getEntityManager();
		em.getTransaction().begin();
		try {
		   Query q = em.createQuery("SELECT p FROM BokLån p WHERE id ="+BoklånID);
		
			    BokLån user = (BokLån) q.getSingleResult();
			    em.remove(user);
			    
				em.getTransaction().commit();
				} finally {
					if (em.getTransaction().isActive())
						em.getTransaction().rollback();
					}
				em.close();
	}
	
}



