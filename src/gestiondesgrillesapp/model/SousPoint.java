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
	private long id;
	
	private long pointID;
	private long eleveID;
	private long commentaireEleveID;
	private long commentaireSousGroupeID;
	private String contenu;
	private String isValidate; //a confirmer
	
	/*
	 * CONSTRUCTEUR(S)
	 */
	
	public SousPoint(String contenu){
		
		this.pointID = -1l;
		this.eleveID = -1l;
		
		this.commentaireEleveID = -1l;
		this.commentaireSousGroupeID = -1l;
		this.isValidate = null;	// TODO : je préfèrerai l'initialiser à "" plutôt que null... Mettre des attributs null dans la BDD c'est plutôt mal ! Qu'en penses-tu ma Laeti ?
		
		if(contenu == null || contenu.isEmpty()){
			throw new IllegalArgumentException("Il faut absoluement qu'un sous-point possède un contenu !");
		}else{
			this.contenu = contenu;
		}
	}
	
	/*
	 * GETTERS and SETTERS
	 */
	
	public long getID(){
		return this.id;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public long getPointID() {
		return pointID;
	}

	public void setPointID(long pointID) {
		this.pointID = pointID;
	}

	public long getEleveID() {
		return eleveID;
	}

	public void setEleveID(long eleveID) {
		this.eleveID = eleveID;
	}

	public long getCommentaireEleveID() {
		return commentaireEleveID;
	}

	public void setCommentaireEleveID(long commentaireEleveID) {
		this.commentaireEleveID = commentaireEleveID;
	}
	
	public long getCommentaireSousGroupeID() {
		return commentaireSousGroupeID;
	}

	public void setCommentaireSousGroupeID(long commentaireSousGroupeID) {
		this.commentaireSousGroupeID = commentaireSousGroupeID;
	}

	public String getIsValidate() {
		return isValidate;
	}

	public void setIsValidate(String isValidate) {
		this.isValidate = isValidate;
	}
}
