package gestiondesgrillesapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RespoModule {

	/*
	 * ATTRIBUTS
	 */
	
	@Id @GeneratedValue
	private long id;
	
	private String nom;
	private String prenom;
	private String email;
	
	/*
	 * CONSTRUCTEUR(S)
	 */
	
	public RespoModule(String nom, String prenom, String email){
		
		if((nom == null || nom.isEmpty()) || (prenom == null || prenom.isEmpty()) || (email == null || email.isEmpty())){
			throw new IllegalArgumentException("Il faut absoluement qu'un responsable module possède un nom, un prenom ET un email !");
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
