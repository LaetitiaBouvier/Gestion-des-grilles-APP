package gestiondesgrillesapp.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tuteur {

	/*
	 * ATTRIBUTS
	 */
	
	@Id @GeneratedValue
	private long id;
	private String nom;
	private String prenom;
	private String numero;
	private String email;
	private String bureau;
	private boolean isRespoModule;
//	private ArrayList<Long> elevesIDs;		//
//	private ArrayList<Long> sousGroupesIDs;	// TODO : Est-ce que "elevesIDs" et "sousGroupesIDs" sont pertinents ici ? Dans la mesure où on peut déjà y accéder via "groupesIDs"
											//        Au-delà du boublon (qui peut être suffisamment pratique pour se justifier), ça ajoute pas mal de difficulter pour répercuter
											//		  une modification. Par exemple se je veux transférer un élève d'un groupe à un autre, il faut potentiellement modifier le groupe d'origine
											//        puis le groupe d'arrivé, mais aussi le tuteur d'origine, le tuteur d'arrivé etc... Je sais pas trop si ça vaut le coup ...
	private ArrayList<Long> groupesIDs;
	// login pwd? => LDAP
	
	/*
	 * CONSTRUCTEUR(S)
	 */

	public Tuteur(String nom, String prenom, String numero, String email, String bureau, boolean isRespoModule){
//							, ArrayList<Long> sousGroupesIDs,ArrayList<Long> elevesIDs){
		
		this.groupesIDs = new ArrayList<Long>();
		
		if((nom == null || nom.isEmpty()) || (prenom == null || prenom.isEmpty()) || (email == null || email.isEmpty())){
			throw new IllegalArgumentException("Il faut absoluement qu'un tuteur possède un nom, un prenom ET un email !");
		}else{
			this.nom = nom;
			this.prenom = prenom;
			this.numero = numero;
			this.email = email;
			this.bureau = "";
			this.isRespoModule = isRespoModule;
//			this.elevesIDs = elevesIDs;
//			this.sousGroupesIDs = sousGroupesIDs;
		}
	}
	
	/*
	 * GETTERS and SETTERS
	 */
	
	public long getID(){
		return this.id;
	}
	
	public void addGroupeID(long groupeID){
		this.groupesIDs.add(groupeID);
	}
	
	public void removeGroupeID(long groupeID){
		this.groupesIDs.remove(groupeID);
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
	
//	public ArrayList<Long> getElevesIDs() {
//		return elevesIDs;
//	}
//	
//	public void setElevesIDs(ArrayList<Long> elevesIDs) {
//		this.elevesIDs = elevesIDs;
//	}
	
	public ArrayList<Long> getGroupesIDs() {
		return groupesIDs;
	}
	
	public void setGroupesIDs(ArrayList<Long> groupesIDs) {
		this.groupesIDs = groupesIDs;
	}
	
//	public ArrayList<Long> getSousGroupesIDs() {
//		return sousGroupesIDs;
//	}
//	
//	public void setSousGroupesIDs(ArrayList<Long> sousGroupesIDs) {
//		this.sousGroupesIDs = sousGroupesIDs;
//	}
	
	public String getBureau() {
		return bureau;
	}

	public void setBureau(String bureau) {
		this.bureau = bureau;
	}

	public boolean isRespoModule() {
		return isRespoModule;
	}

	public void setRespoModule(boolean isRespoModule) {
		this.isRespoModule = isRespoModule;
	}
}