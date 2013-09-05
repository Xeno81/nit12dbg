
package blog.data;

import java.io.Serializable;

import javax.persistence.*;

/**
 * 			Bokprojektet
 * 		 
 * @author 	Dennis Hedlund nit12dhd@student.hig.se 780408
 * 			Daniel Bergh nit12dbg@student.hig.se 810918
 * 			Patrik Eskilsson nit12pen@student.hig.se 920707
 */

/**
 * 
 * Denna klass beskriver egenskaperna hos en låntagare
 *
 */

@Entity
public class Låntagare implements Serializable{
	
	
	
	
	private static final long serialVersionUID = 1L; //Klassens serienummer.
	private String adress;
	private String förnamn;
	private String efternamn;
	private String telefonnummer;
	private String email;
	
	
	@Id @GeneratedValue	
	private long id;
	
	/**
	 * Tom konstruktor som behövs för entitetsklasser.
	 */
	public Låntagare() {}
	/**
	 * konstruktormetoden för låntagare.
	 * @param telefonnummer där vi valde som string för att 
	 * själv välja i vilket format man vill skriva in nummer.
	 * @param adress
	 * @param förnamn
	 * @param efternamn
	 * @param email
	 */
	public Låntagare(String telefonnummer, String adress, String förnamn, String efternamn, String email) {
		
		this.telefonnummer = telefonnummer;
		this.adress = adress;
		this.förnamn = förnamn;
		this.efternamn = efternamn;
		this.email = email;
		
	}
	
	public long getId() {
		return id;
	}
	
	public String getTelefonnummer() {
		return telefonnummer;
	}
	
	public void setTelefonnummer(String telefonnummer) {
		this.telefonnummer = telefonnummer;
	}
	
	public String getAdress() {
		return adress;
	}
	
	public void setAdress(String adress) {
		this.adress = adress;
	}
	
	public String getFörnamn() {
		return förnamn;
	}
	
	public void setFörnamn(String förnamn) {
		this.förnamn = förnamn;
	}
	
	public String getEfternamn() {
		return efternamn;
	}

	public void setEfternamn(String efternamn) {
		this.efternamn = efternamn;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
	/**
	 * Utskriftsmetoden för låntagare.
	 */
	@Override
	public String toString() {
		 
		return "ID: " + id 
				+ " Namn: " + förnamn +" " + efternamn
				+ "\t\tAdress: " + adress
				+ "\tTele: " + telefonnummer 
				+ "\tEmail: " + email;
	}
	
}
