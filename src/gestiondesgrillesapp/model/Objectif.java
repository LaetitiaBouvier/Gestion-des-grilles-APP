package gestiondesgrillesapp.model;

public class Objectif {
	
	/*
	 * ATTRIBUTS
	 */
	private SousCompetence sousCompetence;
	private String titre;
	private String commentaireEleve;
	private Validation validation;
	
	/*
	 * GETTERS and SETTERS
	 */
	public SousCompetence getSousCompetence() {
		return sousCompetence;
	}
	
	public void setSousCompetence(SousCompetence sousCompetence) {
		this.sousCompetence = sousCompetence;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getCommentaireEleve() {
		return commentaireEleve;
	}

	public void setCommentaireEleve(String commentaireEleve) {
		this.commentaireEleve = commentaireEleve;
	}

	public Validation getValidation() {
		return validation;
	}

	public void setValidation(Validation validation) {
		this.validation = validation;
	}
}
