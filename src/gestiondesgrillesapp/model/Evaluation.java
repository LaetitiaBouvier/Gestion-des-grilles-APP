package gestiondesgrillesapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Evaluation {
	/*
	 * ATTRIBUTS
	 */
	@Id @GeneratedValue
	private long id;
	private String appreciation;
	private long nbPoints;
	
	/*
	 * CONSTRUCTEURS
	 */
	
	public Evaluation(String appreciation, long nbPoints){
		this.appreciation = appreciation;
		this.setNbPoints(nbPoints);
	}
	
	/*
	 * GETTERS and SETTERS
	 */
	public long getID(){
		return this.id;
	}
	public String getAppreciation() {
		return appreciation;
	}
	public void setAppreciation(String appreciation) {
		this.appreciation = appreciation;
	}

	public long getNbPoints() {
		return nbPoints;
	}

	public void setNbPoints(long nbPoints) {
		this.nbPoints = nbPoints;
	}
}
