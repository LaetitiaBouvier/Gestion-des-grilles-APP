package gestiondesgrillesapp.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Grille {
	
	/*
	 * ATTRIBUTS
	 */
	@Id @GeneratedValue
	private Long eleve;
	private ArrayList<Competence> competences;
	// est-ce qu'on créé un objet supp pour pouvoir éditer un titre etc ?
	
	/*
	 * GETTERS and SETTERS
	 */
	public ArrayList<Competence> getCompetences() {
		return competences;
	}

	public void setCompetences(ArrayList<Competence> competences) {
		this.competences = competences;
	}

	public Long getEleve() {
		return eleve;
	}

	public void setEleve(Long eleve) {
		this.eleve = eleve;
	}
}
