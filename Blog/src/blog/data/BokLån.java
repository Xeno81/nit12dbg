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
 * 
 * Denna klass skapar metoderna för utlån av böcker.
 *
 */

@Entity
public class BokLån implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int BokID;
	private int LåntagareID;

	@Id @GeneratedValue	
	private long id;
	
	public BokLån() {
		
	}

	public BokLån(int bokID, int låntagareID) {
		
		BokID = bokID;
		LåntagareID = låntagareID;
				
	}

	public int getBokID() {
		return BokID;
	}

	public void setBokID(int bokID) {
		BokID = bokID;
	}

	public int getLåntagareID() {
		return LåntagareID;
	}

	public void setLåntagareID(int låntagareID) {
		LåntagareID = låntagareID;
	}


	@Override
	public String toString() {
		return "BokLån ID: " + id;
	}
	
	
}
