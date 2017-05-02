package gestiondesgrillesapp.model;

import java.util.ArrayList;

public class SousCompetence {
	
	/*
	 * ATTRIBUTS
	 */
	private long competenceID;
	private long pointID;
	private String contenu;
	private long eleveID;
	private long commentaireID;
	private String evaluation;
	private double coefficient;
	
	private ArrayList<Objectif> objectifs;

	/*
	 * GETTERS and SETTERS
	 */
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Commentaire getCommentaireSousGroupe() {
		return commentaireSousGroupe;
	}

	public void setCommentaireSousGroupe(Commentaire commentaireSousGroupe) {
		this.commentaireSousGroupe = commentaireSousGroupe;
	}

	public Evaluation getEval() {
		return evaluation;
	}

	public void setEval(Evaluation eval) {
		this.evaluation = eval;
	}

	public ArrayList<Objectif> getObjectifs() {
		return objectifs;
	}

	public void setObjectifs(ArrayList<Objectif> objectifs) {
		this.objectifs = objectifs;
	}

	public String getCommentaireEleve() {
		return commentaireEleve;
	}

	public void setCommentaireEleve(String commentaireEleve) {
		this.commentaireEleve = commentaireEleve;
	}

	public Competence getCompetence() {
		return competence;
	}

	public void setCompetence(Competence competence) {
		this.competence = competence;
	}

	public Double getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(Double coefficient) {
		this.coefficient = coefficient;
	}

}
