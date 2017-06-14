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
	private String contenu;
	private String niveau;
	private String commentaireIndividuel;
	
	/*
	 * CONSTRUCTEUR(S)
	 */
	
	public SousPoint(String contenu){
		
		this.pointID = -1l;
		this.eleveID = -1l;
		
		this.niveau = "";
		this.commentaireIndividuel = "";
		
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

	public SousPoint deepCopy(){
		
		String contenu = new String(this.contenu);
		
		SousPoint sousPoint = new SousPoint(contenu);
		
		return sousPoint;
	}
	
	public String getNiveau() {
		return niveau;
	}
	
	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}

	public String getCommentaireIndividuel() {
		return commentaireIndividuel;
	}

	public void setCommentaireIndividuel(String commentaireIndividuel) {
		this.commentaireIndividuel = commentaireIndividuel;
	}
}