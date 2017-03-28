package gestiondesgrillesapp.model;

import java.util.ArrayList;

public class SousCompetence {
	
	/*
	 * ATTRIBUTS
	 */
	private Competence competence;
	private String titre;
	private CommentaireSousCompetenceSousGroupe commentaireSousGroupe; // On dit que c'est possible ! qu'il n'y a pas de problèmes d'instances etc
	private String commentaireEleve;

	private Evaluation evaluation;
	private Double coefficient;
	
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

	public CommentaireSousCompetenceSousGroupe getCommentaireSousGroupe() {
		return commentaireSousGroupe;
	}

	public void setCommentaireSousGroupe(CommentaireSousCompetenceSousGroupe commentaireSousGroupe) {
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
