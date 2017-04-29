package gestiondesgrillesapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CommentaireSousCompetenceSousGroupe {
	
	/*
	 * ATTRIBUTS
	 */
	@Id @GeneratedValue
	Long id;
	private String contenu;

	/*
	 * GETTERS and SETTERS
	 */
	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
}