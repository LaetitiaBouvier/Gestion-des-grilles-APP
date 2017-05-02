package gestiondesgrillesapp.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SousGroupe {
	
	/*
	 * ATTRIBUTS
	 */
	@Id @GeneratedValue
	Long id;
	private Groupe groupe;
	private ArrayList<Long> eleves;
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

	public ArrayList<Long> getEleves() {
		return eleves;
	}

	public void setEleves(ArrayList<Long> eleves) {
		this.eleves = eleves;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}
