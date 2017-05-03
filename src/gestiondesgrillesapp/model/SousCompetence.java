package gestiondesgrillesapp.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SousCompetence {
	
	/*
	 * ATTRIBUTS
	 */
	
	@Id @GeneratedValue
	private long id;
	
	private long competenceID;
	private ArrayList<Long> pointsIDs;
	
	private long eleveID;
	private long commentaireEleveID;
	private long commentaireSousGroupeID;
	private String contenu;
	private String evaluation;
	private double coefficient;
	
	/*
	 * CONSTRUCTEUR(S)
	 */
	
	public SousCompetence(String contenu, long competenceID, ArrayList<Long> pointsIDs, long eleveID){
		this.contenu = contenu;
		this.competenceID = competenceID;
		this.pointsIDs = pointsIDs;
		this.eleveID = eleveID;
		
		this.commentaireEleveID = -1;
		this.commentaireSousGroupeID = -1;
		this.coefficient = 0.;
		this.evaluation = null;
	}
	
	/*
	 * GETTERS and SETTERS
	 */
	
	public long getID(){
		return this.id;
	}
	
	public void addPointID(long pointID){
		this.pointsIDs.add(pointID);
	}
	
	public void removePointID(long pointID){
		this.pointsIDs.remove(pointID);
	}
	
	public long getCompetenceID() {
		return competenceID;
	}
	
	public void setCompetenceID(long competenceID) {
		this.competenceID = competenceID;
	}

	public ArrayList<Long> getPointsIDs() {
		return pointsIDs;
	}

	public void setPointsIDs(ArrayList<Long> pointID) {
		this.pointsIDs = pointID;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public long getEleveID() {
		return eleveID;
	}

	public void setEleveID(long eleveID) {
		this.eleveID = eleveID;
	}

	public long getCommentaireEleveID() {
		return commentaireEleveID;
	}

	public void setCommentaireEleveID(long commentaireEleveID) {
		this.commentaireEleveID = commentaireEleveID;
	}
	
	public long getCommentaireSousGroupeID() {
		return commentaireSousGroupeID;
	}

	public void setCommentaireSousGroupeID(long commentaireSousGroupeID) {
		this.commentaireSousGroupeID = commentaireSousGroupeID;
	}

	public String getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}

	public double getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}
}