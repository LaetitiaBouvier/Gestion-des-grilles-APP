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
	 * CONSTRUCTEURS
	 */
	public SousGroupe (long groupeID, ArrayList<Long> elevesIDs, String nom){
		this.groupeID = groupeID;
		this.elevesIDs = elevesIDs;
		this.nom = nom;
	}
	
	
	/*
	 * GETTERS and SETTERS
	 */
	
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
