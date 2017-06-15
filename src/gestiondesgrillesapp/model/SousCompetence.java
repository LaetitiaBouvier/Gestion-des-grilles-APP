package gestiondesgrillesapp.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SousCompetence {
	
	/*
	 * ATTRIBUTS
	 */
	
	@Id @GeneratedValue
	private long id;
	
	private long competenceID;
	private ArrayList<Long> pointsIDs;
	
	private long eleveID;
	private String contenu;
	private String niveau;
	private double coefficient;
	
	private String commentaireEquipe;
	private String commentaireTuteurTuteur;
	private String commentaireIndividuel;
	
	/*
	 * CONSTRUCTEUR(S)
	 */
	
	public SousCompetence(String contenu){
		
		this.competenceID = -1l;
		this.pointsIDs = new ArrayList<Long>();
		
		this.eleveID = -1l;
		this.commentaireEquipe = "";
		this.commentaireTuteurTuteur = "";
		this.commentaireIndividuel = "";
		this.niveau = "Non assigné";
		this.coefficient = 0.;
		
		if(contenu == null || contenu.isEmpty()){
			throw new IllegalArgumentException("Il faut absoluement qu'une sous-compétence possède un contenu !");
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
	
	public void addPointID(long pointID){
		this.pointsIDs.add(pointID);
	}
	
	public void removePointID(long pointID){
		this.pointsIDs.remove(pointID);
	}
	
	public long getCompetenceID() {
		return competenceID;
	}
	
	public void setCompetenceID(long competenceID) {
		this.competenceID = competenceID;
	}

	public ArrayList<Long> getPointsIDs() {
		return pointsIDs;
	}

	public void setPointsIDs(ArrayList<Long> pointID) {
		this.pointsIDs = pointID;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public long getEleveID() {
		return eleveID;
	}

	public void setEleveID(long eleveID) {
		this.eleveID = eleveID;
	}

	public String getNiveau() {
		return niveau;
	}

	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}

	public double getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}
	
	public SousCompetence deepCopy(){
		
		String contenu = new String(this.contenu);
		double coefficient = this.coefficient;
		
		SousCompetence sousCompetence = new SousCompetence(contenu);
		sousCompetence.setCoefficient(coefficient);
		
		return sousCompetence;
	}

	public String getCommentaireEquipe() {
		return commentaireEquipe;
	}

	public void setCommentaireEquipe(String commentaireEquipe) {
		this.commentaireEquipe = commentaireEquipe;
	}

	public String getCommentaireTuteurTuteur() {
		return commentaireTuteurTuteur;
	}

	public void setCommentaireTuteurTuteur(String commentaireTuteurTuteur) {
		this.commentaireTuteurTuteur = commentaireTuteurTuteur;
	}

	public String getCommentaireIndividuel() {
		return commentaireIndividuel;
	}

	public void setCommentaireIndividuel(String commentaireIndividuel) {
		this.commentaireIndividuel = commentaireIndividuel;
	}
}