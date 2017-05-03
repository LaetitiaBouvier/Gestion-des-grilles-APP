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
	
	private long eleveID;
	private long sousGroupeID;
	private long sousCompetenceID;
	private long sousPointID;
	private String contenu;
	
	/*
	 * CONSTRUCTEUR(S)
	 */
//
//	public Commentaire(String contenu){
//		this.contenu = contenu;
//	}
	
	public Commentaire(String contenu, long destinataireID, long objectifID, String code){
		
		if(code.equals("eleveSousPoint")){
			this.contenu = contenu;
			this.eleveID = destinataireID;
			this.sousGroupeID = -1;
			this.sousPointID = objectifID;
			this.sousCompetenceID = -1;
		}
		else if (code.equals("eleveSousCompetence")){
			this.contenu = contenu;
			this.eleveID = destinataireID;
			this.sousGroupeID = -1;
			this.sousPointID = -1;
			this.sousCompetenceID = objectifID;
		}
		else if (code.equals("sousGroupeSousPoint")){
			this.contenu = contenu;
			this.eleveID = -1;
			this.sousGroupeID = destinataireID;
			this.sousPointID = objectifID;
			this.sousCompetenceID = -1;
		}
		else if (code.equals("sousGroupeSousCompetence")){
			this.contenu = contenu;
			this.eleveID = -1;
			this.sousGroupeID = destinataireID;
			this.sousPointID = -1;
			this.sousCompetenceID = objectifID;
		}
		else {
			 throw new IllegalArgumentException("Le code n'est pas bon! Ask Steph ou Laeti");
		}
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