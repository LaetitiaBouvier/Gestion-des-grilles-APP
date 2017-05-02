package gestiondesgrillesapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SousPoint {

	/*
	 * ATTRIBUTS
	 */
	
	@Id @GeneratedValue
	long id;
	private String titre;
	private String description;
	
	/*
	 * CONSTRUCTEURS
	 */
	
	public SousPoint(String titre, String description){
		this.titre = titre;
		this.description = description;
	}
	
	/*
	 * GETTERS and SETTERS
	 */
	
	public long getID(){
		return this.id;
	}
	
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
