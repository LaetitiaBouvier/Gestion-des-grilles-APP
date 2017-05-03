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
	//private String dateCreation; 	TODO : est-ce qu'on ajoute cet attribut ? Ce serait effectivement potentiellement intéressant de connaitre la date de création...
	private boolean isModel;
	
	/*
	 * CONSTRUCTEURS
	 */
	
	public Grille(String titre, boolean isModel){
		
		this.eleveID = -1l;
		this.competencesIDs = new ArrayList<Long>();
		this.isModel = isModel;
		
		if(titre == null || titre.isEmpty()){
			throw new IllegalArgumentException("Il faut absoluement qu'une grille possède un titre !");
		}else{
			this.titre = titre;
		}
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

	public boolean isModel() {
		return isModel;
	}

	public void setModel(boolean isModel) {
		this.isModel = isModel;
	}

	//public String getDateCreation() {
	//	return dateCreation;
	//}

	//public void setDateCreation(String dateCreation) {
	//	this.dateCreation = dateCreation;
	//}
}