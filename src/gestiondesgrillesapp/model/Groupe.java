package gestiondesgrillesapp.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Groupe {

	/*
	 * ATTRIBUTS
	 */
	
	@Id @GeneratedValue
	private long id;
	
	private long promotionID;
	private ArrayList<Long> sousGroupesIDs;
	private ArrayList<Long> tuteursIDs;
	private String nom;
	
	/*
	 * CONSTRUCTEUR(S)
	 */
	
	public Groupe(long promotionID, ArrayList<Long> sousGroupesIDs, ArrayList<Long> tuteursIDs, String nom){
		this.promotionID = promotionID;
		this.sousGroupesIDs = sousGroupesIDs;
		this.tuteursIDs = tuteursIDs;
		this.nom = nom;
	}

	/*
	 * GETTERS and SETTERS
	 */
	
	public long getID(){
		return this.id;
	}
	
	public void addSousGroupeID(long sousGroupeID){
		this.sousGroupesIDs.add(sousGroupeID);
	}
	
	public void removeSousGroupeID(long sousGroupeID){
		this.sousGroupesIDs.remove(sousGroupeID);
	}
	
	public void addTuteurID(long tuteurID){
		this.tuteursIDs.add(tuteurID);
	}
	
	public void removeTuteurID(long tuteurID){
		this.tuteursIDs.remove(tuteurID);
	}

	public long getPromotionID() {
		return promotionID;
	}

	public void setPromotionID(long promotionID) {
		this.promotionID = promotionID;
	}

	public ArrayList<Long> getSousGroupesIDs() {
		return sousGroupesIDs;
	}

	public void setSousGroupesIDs(ArrayList<Long> sousGroupesIDs) {
		this.sousGroupesIDs = sousGroupesIDs;
	}

	public ArrayList<Long> getTuteursIDs() {
		return tuteursIDs;
	}

	public void setTuteursIDs(ArrayList<Long> tuteursIDs) {
		this.tuteursIDs = tuteursIDs;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}
