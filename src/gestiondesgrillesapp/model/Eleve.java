package gestiondesgrillesapp.model;

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
	private long sousGroupeID;
	private long grilleID;
	
	private String nom;
	private String prenom;
	private String numero;
	private String email;
	// login pwd? => LDAP
	
	/*
	 * CONSTRUCTEUR(S)
	 */
	
	public Eleve(long sousGroupeID, long grilleID, String nom, String prenom, String numero, String email){
		
		this.sousGroupeID = sousGroupeID;
		this.grilleID = grilleID;
		
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getSousGroupeID() {
		return sousGroupeID;
	}

	public void setSousGroupeID(long sousGroupeID) {
		this.sousGroupeID = sousGroupeID;
	}

	public long getGrilleID() {
		return grilleID;
	}

	public void setGrilleID(long grilleID) {
		this.grilleID = grilleID;
	}
}