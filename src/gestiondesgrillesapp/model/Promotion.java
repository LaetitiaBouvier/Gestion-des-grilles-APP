package gestiondesgrillesapp.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Promotion {
	
	/*
	 * ATTRIBUTS
	 */
	
	@Id @GeneratedValue
	private long id;
	
	private long anneeObtensionDiplome;
	private ArrayList<Long> groupesIDs;
	private ArrayList<Long> elevesIDs;
	
	/*
	 * CONSTRUCTEUR(S)
	 */
	
	public Promotion(long anneeObtensionDiplome){
		
		this.anneeObtensionDiplome = anneeObtensionDiplome;
		
		this.groupesIDs = new ArrayList<Long>();
		this.elevesIDs = new ArrayList<Long>();
	}

	/*
	 * GETTERS and SETTERS
	 */
	
	public long getID(){
		return this.id;
	}
	
	public void addGroupeID(long groupeID){
		this.groupesIDs.add(groupeID);
	}
	
	public void removeGroupeID(long groupeID){
		this.groupesIDs.remove(groupeID);
	}
	
	public void addEleveID(long eleveID){
		this.elevesIDs.add(eleveID);
	}
	
	public void removeEleveID(long eleveID){
		this.elevesIDs.remove(eleveID);
	}

	public long getAnneeObtensionDiplome() {
		return anneeObtensionDiplome;
	}

	public void setAnneeObtensionDiplome(long anneeObtensionDiplome) {
		this.anneeObtensionDiplome = anneeObtensionDiplome;
	}

	public ArrayList<Long> getGroupesIDs() {
		return groupesIDs;
	}

	public void setGroupesIDs(ArrayList<Long> groupesIDs) {
		this.groupesIDs = groupesIDs;
	}

	public ArrayList<Long> getElevesIDs() {
		return elevesIDs;
	}

	public void setElevesIDs(ArrayList<Long> elevesIDs) {
		this.elevesIDs = elevesIDs;
	}
}