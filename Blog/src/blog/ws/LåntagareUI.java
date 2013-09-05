package blog.ws;

import java.util.Scanner;
import blog.main.Main;

import blog.db.LåntagareMapper;

/**
 * 			Bokprojektet
 * 		 
 * @author 	Dennis Hedlund nit12dhd@student.hig.se 780408
 * 			Daniel Bergh nit12dbg@student.hig.se 810918
 * 			Patrik Eskilsson nit12pen@student.hig.se 920707
 */

/**
 * 
 * Den klass som styr menyfunktioner för Låntagare
 *
 */

public class LåntagareUI {

	private final static int GÅ_TILL_HUVUDMENY = 0;
	private final static int LISTA_LÅNTAGARE = 1;
	private final static int SKAPA_LÅNTAGARE = 2;
	private final static int ÄNDRA_DATA_OM_LÅNTAGARE = 3;
	private final static int RADERA_LÅNTAGARE = 4;
	


	public static void visaLåntagareMeny(){
		
		boolean runProgram = true;
		int val = 0;
		
		LåntagareMapper låntagareMapper = LåntagareMapper.getInstance();
		
		
		while(runProgram){
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in, "UTF-8"/*encoding*/);
			System.out.format("%n*** MENY - LÅNTAGARE ***%n");
			System.out.println("0 - Återgå till huvudmeny");
			System.out.println("1 - Lista låntagare");
			System.out.println("2 - Skapa ny låntagare");
			System.out.println("3 - Ändra data om låntagare");
			System.out.println("4 - Radera låntagare");
			System.out.print("Vad vill du göra: ");
			val = scanner.nextInt();
			
			//Den switch-sats som styr menyval.
			switch (val){
				case LISTA_LÅNTAGARE:
					LåntagareMapper.listaLåntagare();
					break;
				case SKAPA_LÅNTAGARE:
					System.out.println("skapa låntagare");
					låntagareMapper.skapaLåntagare();
					break;
				case ÄNDRA_DATA_OM_LÅNTAGARE:
					låntagareMapper.uppdateraLåntagare();
					break;
				case RADERA_LÅNTAGARE:
					System.out.println("*** RADERA PERSON UR DATABASEN ***");
					låntagareMapper.raderaLåntagare();
					break;
				case GÅ_TILL_HUVUDMENY:
					Main.huvudMeny();
					break;
				default:
					System.out.println("Felaktig inmatning!");
					visaLåntagareMeny();
					break;
			}
		}
		
	
	}
}
