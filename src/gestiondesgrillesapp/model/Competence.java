package gestiondesgrillesapp.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Competence {
	
	/*
	 * ATTRIBUTS
	 */
	
	@Id @GeneratedValue
	private long id;
	
	private long grilleID;
	private ArrayList<Long> sousCompetencesIDs;
	
	private String titre;
	private String description;
	private double coefficient;
	
	/*
	 * CONSTRUCTEUR(S)
	 */
	
	public Competence(long grilleID, ArrayList<Long> sousCompetencesIDs, String titre, String description){
		
		this.grilleID = grilleID;
		
		if(sousCompetencesIDs == null){
			this.sousCompetencesIDs = new ArrayList<Long>();
		}else{
			this.sousCompetencesIDs = sousCompetencesIDs;
		}
		
		if((titre == null || titre.isEmpty()) || (description == null || description.isEmpty())){
			throw new IllegalArgumentException("Il faut absoluement qu'une compétence possède un titre ET une description !");
		}else{
			this.titre = titre;
			this.description = description;
		}
		
		this.coefficient = 0.;
	}
	
	
	/*
	 * GETTERS and SETTERS
	 */
	
	public long getID(){
		return this.id;
	}
	
	public void addSousCompetenceID(long sousCompetenceID){
		this.sousCompetencesIDs.add(sousCompetenceID);
	}
	
	public void removeSousCompetenceID(long sousCompetenceID){
		this.sousCompetencesIDs.remove(sousCompetenceID);
	}
	
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getGrilleID() {
		return grilleID;
	}

	public void setGrilleID(long grilleID) {
		this.grilleID = grilleID;
	}

	public ArrayList<Long> getSousCompetencesIDs() {
		return sousCompetencesIDs;
	}

	public void setSousCompetenceID(ArrayList<Long> sousCompetencesIDs) {
		this.sousCompetencesIDs = sousCompetencesIDs;
	}

	public double getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}
}