package gestiondesgrillesapp.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Eleve {
	
	/*
	 * ATTRIBUTS
	 */
	
	@Id @GeneratedValue
	private long id;
	private String nom;
	private String prenom;
	private String numero;
	private String email;
	private long sousGroupeID;
	private ArrayList<Long> grillesIDs;
	// login pwd? => LDAP

	/*
	 * CONSTRUCTEUR(S)
	 */
	
	public Eleve (String nom, String prenom, String numero, String email, long sousGroupeID, ArrayList<Long> grillesIDs){

		this.sousGroupeID = sousGroupeID;
		
		if(grillesIDs == null){
			this.grillesIDs = new ArrayList<Long>();
		}else{
			this.grillesIDs = grillesIDs;
		}
		
		if((nom == null || nom.isEmpty()) || (prenom == null || prenom.isEmpty()) || (numero == null || numero.isEmpty()) || (email == null || email.isEmpty())){
			throw new IllegalArgumentException("Il faut absoluement qu'un élève possède un nom, un prenom, un numéro (étudiant) ET un email !");
		}else{
			this.nom = nom;
			this.prenom = prenom;
			this.numero = numero;
			this.email = email;
		}
	}
	
	/*
	 * GETTERS and SETTERS
	 */
	
	public long getID(){
		return this.id;
	}
	
	public void addGrilleID(long grilleID){
		this.grillesIDs.add(grilleID);
	}
	
	public void removeGrilleID(long grilleID){
		this.grillesIDs.remove(grilleID);
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
	
	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public long getSousGroupeID() {
		return sousGroupeID;
	}
	
	public void setSousGroupeID(long sousGroupeID) {
		this.sousGroupeID = sousGroupeID;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<Long> getGrillesIDs() {
		return grillesIDs;
	}

	public void setGrillesIDs(ArrayList<Long> grillesIDs) {
		this.grillesIDs = grillesIDs;
	}
}
