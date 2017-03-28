package gestiondesgrillesapp.model;

import java.util.ArrayList;

public class SousGroupe {
	
	/*
	 * ATTRIBUTS
	 */
	private Groupe groupe;
	private ArrayList<Eleve> eleves;
	private String nom;
	
	/*
	 * GETTERS and SETTERS
	 */
	public Groupe getGroupe() {
		return groupe;
	}
	
	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}

	public ArrayList<Eleve> getEleves() {
		return eleves;
	}

	public void setEleves(ArrayList<Eleve> eleves) {
		this.eleves = eleves;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}
