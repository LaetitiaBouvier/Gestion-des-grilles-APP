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
	 * CONSTRUCTEURS
	 */
	
	public Competence(long grilleID, ArrayList<Long> sousCompetencesIDs, String titre, String description){
		this.grilleID = grilleID;
		this.sousCompetencesIDs = sousCompetencesIDs;
		this.titre = titre;
		this.description = description;
		
		this.coefficient = 0.;
	}
	
	
	/*
	 * GETTERS and SETTERS
	 */
	
	public long getID(){
		return this.id;
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