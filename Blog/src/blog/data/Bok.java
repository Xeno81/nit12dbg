package blog.data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * 			Bokprojektet
 * 		 
 * @author 	Dennis Hedlund nit12dhd@student.hig.se 780408
 * 			Daniel Bergh nit12dbg@student.hig.se 810918
 * 			Patrik Eskilsson nit12pen@student.hig.se 920707
 */


/**
 * Denna entitetsklass beskriver en bok med egenskaperna 
 * titel, utgivningsår och författare
 */
@Entity
public class Bok implements Serializable{


	private static final long serialVersionUID = 1L;  //Klassens serienummer
	protected String titel;
	protected int utgivningsår;
	protected String författare;
	
	
	@Id @GeneratedValue	
	private long id;
	
	/**
	 * Tom konstruktor som behövs för entitetsklass
	 */
	public Bok() {}
	
	/**
	 *  konstruktormetoden för klassen bok.
	 * @param titel på boken
	 * @param utgivningsår för boken
	 * @param författare för boken
	 */
	public Bok(String titel, int utgivningsår, String författare) {
		
		this.titel = titel;
		this.utgivningsår = utgivningsår;
		this.författare = författare;
		
		
	}
	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	public int getUtgivningsår() {
		return utgivningsår;
	}
	public void setUtgivningsår(int utgivningsår) {
		this.utgivningsår = utgivningsår;
	}
	public String getFörfattare() {
		return författare;
	}
	public void setFörfattare(String författare) {
		this.författare = författare;
	}


	/**
	 * toString skapar utskriften för böckerna.
	 */
	@Override
	public String toString() {
		return "Bok ID = " + id + " " +  titel + " - " + utgivningsår + " - " + författare;
	}

	


	
	

}
