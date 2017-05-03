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
	//private String dateCreation;
	
	/*
	 * CONSTRUCTEURS
	 */
	
	public Grille(long eleveID, ArrayList<Long> competencesIDs, String titre){
		this.eleveID = eleveID;
		this.competencesIDs = competencesIDs;
		this.titre = titre;
		//this.dateCreation = null;
	}
	
	/*
	 * GETTERS and SETTERS
	 */
	
	public void addCompetenceID(long competenceID){
		this.competencesIDs.add(competenceID);
	}
	
	public void removeCompetenceID(long competenceID){
		this.competencesIDs.remove(competenceID);
	}
	
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

	//public String getDateCreation() {
	//	return dateCreation;
	//}

	//public void setDateCreation(String dateCreation) {
	//	this.dateCreation = dateCreation;
	//}
}
