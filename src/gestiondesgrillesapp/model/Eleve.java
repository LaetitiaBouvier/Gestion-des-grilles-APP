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
	private String nom;
	private String prénom;
	private String numero;
	private String email;
	private long sousGroupeID;
	private long grilleID;
	// login pwd? => LDAP
	
	
}
