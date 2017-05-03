package gestiondesgrillesapp.model;

import java.util.ArrayList;

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
	private ArrayList<Long> sousPointsIDs;
	private String titre;
	
	/*
	 * CONSTRUCTEUR(S)
	 */
	
	public Point(long sousCompetenceID, ArrayList<Long> sousPointsIDs, String titre){
		this.sousCompetenceID = sousCompetenceID;
		this.sousPointsIDs = sousPointsIDs;
		this.titre = titre;
	}
	
	/*
	 * GETTERS and SETTERS
	 */

	public long getID(){
		return this.id;
	}
	
	public void addSousPointID(long sousPointID){
		this.sousPointsIDs.add(sousPointID);
	}
	
	public void removeSousPointID(long sousPointID){
		this.sousPointsIDs.remove(sousPointID);
	}
	
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

	public ArrayList<Long> getSousPointsIDs() {
		return sousPointsIDs;
	}

	public void setSousPointsIDs(ArrayList<Long> sousPointsIDs) {
		this.sousPointsIDs = sousPointsIDs;
	}
}