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
	private long id;
	
	private long groupeID;
	private ArrayList<Long> elevesIDs;
	private String nom;

	/*
	 * CONSTRUCTEUR(S)
	 */
	
	public SousGroupe(String nom){
		
		this.groupeID = -1l;
		this.elevesIDs = new ArrayList<Long>();
		
		if(nom == null || nom.isEmpty()){
			throw new IllegalArgumentException("Il faut absoluement qu'un sous-groupe possède un nom !");
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
	
	public void addEleveID(long eleveID){
		this.elevesIDs.add(eleveID);
	}
	
	public void removeEleveID(long eleveID){
		this.elevesIDs.remove(eleveID);
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public long getGroupeID() {
		return groupeID;
	}

	public void setGroupeID(long groupeID) {
		this.groupeID = groupeID;
	}

	public ArrayList<Long> getElevesIDs() {
		return elevesIDs;
	}

	public void setElevesIDs(ArrayList<Long> elevesIDs) {
		this.elevesIDs = elevesIDs;
	}
}
