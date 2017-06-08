package gestiondesgrillesapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Commentaire {
	
	/*
	 * ATTRIBUTS
	 */
	
	@Id @GeneratedValue
	private long id;
	
	private long hyperID; // Soit l'ID d'une sous-compétence, soit celle d'un sous-point
	private String contenu;
	private String niveau;
	
	/*
	 * CONSTRUCTEUR(S)
	 */
//
//	public Commentaire(String contenu){
//		this.contenu = contenu;
//	}
	
	public Commentaire(String contenu, long hyperID, String niveau){
		
		this.contenu = contenu;
		this.hyperID = hyperID;
		this.niveau = niveau;
	}
	
	/*
	 * GETTERS and SETTERS
	 */
	
	public long getID(){
		return this.id;
	}
	
	public long getHyperID() {
		return hyperID;
	}
	
	public void setHyperID(long hyperID) {
		this.hyperID = hyperID;
	}
	
	public String getContenu() {
		return this.contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public String getNiveau() {
		return niveau;
	}

	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}
}