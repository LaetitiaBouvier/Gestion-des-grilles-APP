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
	
	public Competence(String titre, String description){
		
		this.grilleID = -1l;
		this.sousCompetencesIDs = new ArrayList<Long>();
		
		if((titre == null || titre.isEmpty()) || (description == null || description.isEmpty())){
			throw new IllegalArgumentException("Il faut absolument qu'une compétence possède un nom ET une description !");
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
	
	public Competence deepCopy(){
		String titre = new String(this.titre);
		String description = new String(this.description);
		double coefficient = this.coefficient;
		
		Competence competence = new Competence(titre, description);
		competence.setCoefficient(coefficient);
		
		return competence;
	}
}