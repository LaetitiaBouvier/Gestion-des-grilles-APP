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
	long id;
	private String contenu;
	
	private long eleveID;
	private long sousGroupeID;
	
	private long sousCompetenceID;
	private long sousPointID;
	
	/*
	 * CONSTRUCTEURS
	 */

	public Commentaire(String contenu){
		this.contenu = contenu;
	}
	
	public Commentaire(String contenu, long sousPointID){
		this.contenu = contenu;
		this.sousPointID = sousPointID;
	}
	
	/*
	 * GETTERS and SETTERS
	 */
	
	public long getID(){
		return this.id;
	}
	
	public String getContenu() {
		return this.contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public long getEleveID() {
		return this.eleveID;
	}

	public void setEleveID(long eleveID) {
		this.eleveID = eleveID;
	}

	public long getSousGroupeID() {
		return this.sousGroupeID;
	}

	public void setSousGroupeID(long sousGroupeID) {
		this.sousGroupeID = sousGroupeID;
	}

	public long getSousCompetenceID() {
		return this.sousCompetenceID;
	}

	public void setSousCompetenceID(long sousCompetenceID) {
		this.sousCompetenceID = sousCompetenceID;
	}

	public long getSousPointID() {
		return this.sousPointID;
	}

	public void setSousPoint(long sousPointID) {
		this.sousPointID = sousPointID;
	}
}