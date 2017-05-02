package gestiondesgrillesapp.model;

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
	private long pointID;
	private String contenu;
	private long eleveID;
	private long commentaireEleveID;
	private long commentaireSousGroupeID;
	private String evaluation;
	private double coefficient;
	
	/*
	 * CONSTRUCTEURS
	 */
	public SousCompetence(String contenu, long competenceID, long pointID, long eleveID){
		this.contenu = contenu;
		this.competenceID = competenceID;
		this.pointID = pointID;
		this.eleveID = eleveID;
		
		this.commentaireEleveID = -1;
		this.commentaireSousGroupeID = -1;
		this.coefficient = 0.;
		this.evaluation = null;
	}
	
	/*
	 * GETTERS and SETTERS
	 */
	public long getCompetenceID() {
		return competenceID;
	}
	
	public void setCompetenceID(long competenceID) {
		this.competenceID = competenceID;
	}

	public long getPointID() {
		return pointID;
	}

	public void setPointID(long pointID) {
		this.pointID = pointID;
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
