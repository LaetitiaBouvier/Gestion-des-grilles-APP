package gestiondesgrillesapp.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tuteur {

	/*
	 * ATTRIBUTS
	 */
	
	@Id @GeneratedValue
	private long id;
	
	ArrayList<Long> groupesIDs;
	private String nom;
	private String prenom;
	private String email;
	
	/*
	 * CONSTRUCTEUR(S)
	 */
	
	public Tuteur(ArrayList<Long> groupesIDs, String nom, String prenom, String email){
		
		if(groupesIDs != null){
			this.groupesIDs = groupesIDs;
		}else{
			this.groupesIDs = new ArrayList<Long>();
		}
		
		if((nom == null || nom.isEmpty()) || (prenom == null || prenom.isEmpty()) || (email == null || email.isEmpty())){
			throw new IllegalArgumentException("Il faut absoluement qu'un tuteur possède un nom, un prenom ET un email !");
		}else{
			this.nom = nom;
			this.prenom = prenom;
			this.email = email;
		}
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
	
	public ArrayList<Long> getGroupesIDs(){
		return groupesIDs;
	}
	
	public void setGroupesIDs(ArrayList<Long> groupesIDs){
		this.groupesIDs = groupesIDs;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}