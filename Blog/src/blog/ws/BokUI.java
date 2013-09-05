package blog.ws;

import java.util.Scanner;

import blog.db.BokLånMapper;
import blog.db.BokMapper;
import blog.main.Main;

/**
 * 			Bokprojektet
 * 		 
 * @author 	Dennis Hedlund nit12dhd@student.hig.se 780408
 * 			Daniel Bergh nit12dbg@student.hig.se 810918
 * 			Patrik Eskilsson nit12pen@student.hig.se 920707
 */

/**
 * 
 * Klassen som styr menyn för bokval.
 *
 */

public class BokUI {

	private final static int GÅ_TILL_HUVUDMENY = 0;
	private final static int LISTA_BÖCKER = 1;
	private final static int REGISTRERA_BOK = 2;
	private final static int RADERA_BOK = 3;
	private final static int ÄNDRA_DATA_OM_BOK = 4;
	private final static int LISTA_UTLÅNAT = 5;
	private final static int LÅNA_BOK = 6;
	private final static int RETURNERA_BOK = 7;
	
	public static void visaBokMeny(){
		
		boolean runProgram = true;
		int val = 0;
		
		BokMapper bokMapper = BokMapper.getInstance();
		
		
		while(runProgram){
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in, "UTF-8"/*encoding*/);
			System.out.format("%n*** MENY - BÖCKER ***%n");
			System.out.println("0 - Återgå till huvudmeny");
			System.out.println("1 - Lista böcker");
			System.out.println("2 - Registrera bok");
			System.out.println("3 - Radera bok");
			System.out.println("4 - Ändra data om bok");
			System.out.println("5 - Lista utlånade böcker");
			System.out.println("6 - Låna bok");
			System.out.println("7 - Returnera bok");
			System.out.print("vad vill du göra:");
			val = scanner.nextInt();
			
			// Den switch-sats som styr vilket menyval man gör.
			switch (val){
				case LISTA_BÖCKER:
					BokMapper.listaBok();
					break;
				case REGISTRERA_BOK:
					System.out.println("registrera bok");
					bokMapper.skapaBok();
					break;
				case RADERA_BOK:
					System.out.println("*** RADERA BOK UR DATABASEN ***");
					//avbryt funktion
					bokMapper.raderaBok();
					break;
				case ÄNDRA_DATA_OM_BOK:
					bokMapper.uppdateraBok();
					break;
				case LÅNA_BOK:
					BokLånMapper.LånaBok();
					break;
				case LISTA_UTLÅNAT:
					BokLånMapper.ListaLån();
					break;
				case RETURNERA_BOK:
					BokLånMapper.ReturneraBok();
					break;
				case GÅ_TILL_HUVUDMENY:
					Main.huvudMeny();
					break;
				default:
					System.out.println("Felaktig inmatning!");
					visaBokMeny();
					break;
			}
		}
	}
}