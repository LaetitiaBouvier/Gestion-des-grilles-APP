package gestiondesgrillesapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Point {
	/*
	 * ATTRIBUTS
	 */
	@Id @GeneratedValue
	private long id;
	private long sousCompetenceID;
	private long sousPointID;
	private String titre;
	
	

	/*
	 * CONSTRUCTEURS
	 */
	
	public Point(long sousCompetenceID, long sousPointID, String titre){
		this.sousCompetenceID = sousCompetenceID;
		this.sousPointID = sousPointID;
		this.titre = titre;
	}
	/*
	 * GETTERS and SETTERS
	 */

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public long getSousCompetenceID() {
		return sousCompetenceID;
	}

	public void setSousCompetenceID(long sousCompetenceID) {
		this.sousCompetenceID = sousCompetenceID;
	}

	public long getSousPointID() {
		return sousPointID;
	}

	public void setSousPointID(long sousPointID) {
		this.sousPointID = sousPointID;
	}

}
