package gestiondesgrillesapp.model;

import java.util.ArrayList;

public class Competence {
	
	/*
	 * ATTRIBUTS
	 */
	private long grilleID;
	private long sousCompetenceID;
	private String titre;
	private String description;
	private ArrayList<SousCompetence> sousCompetences;

	/*
	 * GETTERS and SETTERS
	 */
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public ArrayList<SousCompetence> getSousCompetences() {
		return sousCompetences;
	}

	public void setSousCompetences(ArrayList<SousCompetence> sousCompetences) {
		this.sousCompetences = sousCompetences;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Grille getGrille() {
		return grille;
	}

	public void setGrille(Grille grille) {
		this.grille = grille;
	}
}
