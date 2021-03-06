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
	private ArrayList<Long> elevesIDs;
	private String nom;
	
	/*
	 * CONSTRUCTEUR(S)
	 */
	
	public Groupe(String nom){
		
		this.promotionID = -1l;
		
		this.sousGroupesIDs = new ArrayList<Long>();
		this.tuteursIDs = new ArrayList<Long>();
		this.elevesIDs = new ArrayList<Long>();
		
		if(nom == null || nom.isEmpty()){
			throw new IllegalArgumentException("Il faut absoluement qu'une groupe possède un nom !");
		}else{
			this.nom = nom;
		}
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
	
	public void addEleveID(long eleveID){
		this.elevesIDs.add(eleveID);
	}
	
	public void removeEleveID(long eleveID){
		this.elevesIDs.remove(eleveID);
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
	
	public ArrayList<Long> getElevesIDs() {
		return elevesIDs;
	}

	public void setElevesIDs(ArrayList<Long> elevesIDs) {
		this.elevesIDs = elevesIDs;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}
