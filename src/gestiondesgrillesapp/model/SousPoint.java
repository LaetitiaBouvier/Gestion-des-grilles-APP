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
	private String contenu;
	private long pointID;
	private long eleveID;
	private long commentaireID;
	private String isValidate; //a confirmer
	
	/*
	 * CONSTRUCTEURS
	 */
	
	public SousPoint(String contenu, long pointID, long eleveID, long commentaireID){
		this.contenu = contenu;
		this.pointID = pointID;
		this.eleveID = eleveID;
		this.commentaireID = commentaireID;
		this.isValidate = null;
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

	public long getCommentaireID() {
		return commentaireID;
	}

	public void setCommentaireID(long commentaireID) {
		this.commentaireID = commentaireID;
	}

	public String getIsValidate() {
		return isValidate;
	}

	public void setIsValidate(String isValidate) {
		this.isValidate = isValidate;
	}
	
}
