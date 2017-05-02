package gestiondesgrillesapp.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Grille {
	
	/*
	 * ATTRIBUTS
	 */
	@Id @GeneratedValue
	private long id;
	private long eleveID;
	private ArrayList<Long> competencesIDs;
	private String titre;
	
	/*
	 * CONSTRUCTEURS
	 */
	
	public Grille(long eleveID, ArrayList<Long> competencesIDs, String titre){
		this.eleveID = eleveID;
		this.competencesIDs = competencesIDs;
		this.titre = titre;
	}
	
	/*
	 * GETTERS and SETTERS
	 */
	
	public long getID(){
		return this.id;
	}
	
	public long getEleveID() {
		return eleveID;
	}
	
	public void setEleveID(long eleveID) {
		this.eleveID = eleveID;
	}

	public ArrayList<Long> getCompetencesIDs() {
		return competencesIDs;
	}

	public void setCompetenceID(ArrayList<Long> competencesIDs) {
		this.competencesIDs = competencesIDs;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}
}
