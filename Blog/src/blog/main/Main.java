package blog.main;
import java.util.Scanner;

import blog.ws.BokUI;
import blog.ws.LåntagareUI;

/**
 * 			Bokprojektet
 * 		 
 * @author 	Dennis Hedlund nit12dhd@student.hig.se 780408
 * 			Daniel Bergh nit12dbg@student.hig.se 810918
 * 			Patrik Eskilsson nit12pen@student.hig.se 920707
 */

/**
 * 
 * Mainmetoden som initierar respektive menymetod
 *
 */

public class Main {
	
	private final static int AVSLUTA = 0;
	private final static int VISA_LÅNTAGARE = 1;
	private final static int VISA_BÖCKER = 2;


	public static void main(String[] args) {
		
		huvudMeny();
		
	}

	@SuppressWarnings("resource")
	public static void huvudMeny() {
		
		Scanner scanner = new Scanner(System.in, "Cp1252"/*encoding*/);
		System.out.format("%n*** HUVUDMENY ***%n");
		System.out.println("0 - Avsluta");
		System.out.println("1 - Visa låntagarmeny");
		System.out.println("2 - Visa bokmeny");
		int menyval = scanner.nextInt();

		//används för anrop av respektive menyklass
		switch (menyval) {
		case AVSLUTA:
			System.out.println("Programmet avslutat!");
			System.exit(0);
			break;
		
		case VISA_LÅNTAGARE:
			LåntagareUI.visaLåntagareMeny();
			break;
		case VISA_BÖCKER:
			BokUI.visaBokMeny();
			break;
			
		default:
			System.out.println("Felaktig inmatning!");
			huvudMeny();
			break;
		}
	}
}

