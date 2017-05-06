package gestiondesgrillesapp.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	
	/*
	 * ATTRIBUTS
	 */
	
	@Id @GeneratedValue
	private long id;
	private String nom;
	private String prenom;
	private String numero;
	private String email;
	
	private boolean isTuteur;
	private String bureauTuteur;
	private ArrayList<Long> groupesTuteurIDs;
	
	private boolean isRespoModule;
	
	private long sousGroupeEleveID;
	private long grilleEleveID;
	private ArrayList<Long> grillesHistoriqueEleveIDs;
	// login pwd? => LDAP

	/*
	 * CONSTRUCTEUR(S)
	 */
	
	public User (String nom, String prenom, String numero, String email, boolean isTuteur){

		this.sousGroupeEleveID = -1l;
		this.grilleEleveID = -1l;
		this.grillesHistoriqueEleveIDs = new ArrayList<Long>();
		this.groupesTuteurIDs = new ArrayList<Long>();
		
		if((nom == null || nom.isEmpty()) || (prenom == null || prenom.isEmpty()) || (numero == null || numero.isEmpty()) || (email == null || email.isEmpty())){
			throw new IllegalArgumentException("Il faut absoluement qu'un utilisateur possède un nom, un prenom, un numéro ET un email !");
		}else{
			this.nom = nom;
			this.prenom = prenom;
			this.numero = numero;
			this.email = email;
			this.bureauTuteur = "";
			this.isTuteur = isTuteur;
			this.isRespoModule = false;
		}
	}
	
	/*
	 * GETTERS and SETTERS
	 */
	
	public long getID(){
		return this.id;
	}
	
	public void addGroupeTuteurID(long groupeTuteurID){
		this.groupesTuteurIDs.add(groupeTuteurID);
	}
	
	public void removeGroupeID(long groupeTuteurID){
		this.groupesTuteurIDs.remove(groupeTuteurID);
	}
	
	public void addGrilleHistoriqueEleveID(long grilleHistoriqueID){
		this.grillesHistoriqueEleveIDs.add(grilleHistoriqueID);
	}
	
	public void removeGrilleHistoriqueEleveID(long grilleHistoriqueID){
		this.grillesHistoriqueEleveIDs.remove(grilleHistoriqueID);
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
	
	public long getSousGroupeEleveID() {
		return sousGroupeEleveID;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setSousGroupeEleveID(long sousGroupeID) {
		this.sousGroupeEleveID = sousGroupeID;
	}

	public ArrayList<Long> getGrillesHistoriqueEleveIDs() {
		return grillesHistoriqueEleveIDs;
	}

	public void setGrillesHistoriqueEleveIDs(ArrayList<Long> grillesIDs) {
		this.grillesHistoriqueEleveIDs = grillesIDs;
	}

	public long getGrilleEleveID() {
		return grilleEleveID;
	}

	public void setGrilleEleveID(long grilleID) {
		this.grilleEleveID = grilleID;
	}
	
	public String getBureauTuteur() {
		return bureauTuteur;
	}

	public void setBureauTuteur(String bureauTuteur) {
		this.bureauTuteur = bureauTuteur;
	}
	
	public boolean isRespoModule() {
		return isRespoModule;
	}

	public void setRespoModule(boolean isRespoModule) {
		this.isRespoModule = isRespoModule;
	}
	
	public boolean isTuteur() {
		return isTuteur;
	}

	public void setTuteur(boolean isTuteur) {
		this.isTuteur = isTuteur;
	}
}