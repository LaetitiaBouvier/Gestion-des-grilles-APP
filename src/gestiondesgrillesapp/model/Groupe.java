package gestiondesgrillesapp.model;

import java.util.ArrayList;

public class Groupe {

	/*
	 * ATTRIBUTS
	 */
	private Promotion promotion;
	private ArrayList<SousGroupe> sousGroupes;
	private String nom;
	// + tuteur associé
	/*
	 * GETTERS and SETTERS
	 */
	public Promotion getPromotion() {
		return promotion;
	}
	
	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}
	
	public ArrayList<SousGroupe> getSousGroupes() {
		return sousGroupes;
	}
	
	public void setSousGroupes(ArrayList<SousGroupe> sousGroupes) {
		this.sousGroupes = sousGroupes;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}
