package gestiondesgrillesapp.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Point {
	
	/*
	 * ATTRIBUTS
	 */
	
	@Id @GeneratedValue
	private long id;
	
	private long sousCompetenceID;
	private ArrayList<Long> sousPointsIDs;
	private long eleveID;
	private String titre;
	
	/*
	 * CONSTRUCTEUR(S)
	 */
	
	public Point(String titre){
		
		this.sousCompetenceID = -1l;
		this.eleveID = -1l;
		this.sousPointsIDs = new ArrayList<Long>();
		
		if(titre == null || titre.isEmpty()){
			throw new IllegalArgumentException("Il faut absoluement qu'un point possède un titre !");
		}else{
			this.titre = titre;
		}
	}
	
	/*
	 * GETTERS and SETTERS
	 */

	public long getID(){
		return this.id;
	}
	
	public void addSousPointID(long sousPointID){
		this.sousPointsIDs.add(sousPointID);
	}
	
	public void removeSousPointID(long sousPointID){
		this.sousPointsIDs.remove(sousPointID);
	}
	
	public long getEleveID() {
		return eleveID;
	}

	public void setEleveID(long eleveID) {
		this.eleveID = eleveID;
	}
	
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public long getSousCompetenceID() {
		return sousCompetenceID;
	}

	public void setSousCompetenceID(long sousCompetenceID) {
		this.sousCompetenceID = sousCompetenceID;
	}

	public ArrayList<Long> getSousPointsIDs() {
		return sousPointsIDs;
	}

	public void setSousPointsIDs(ArrayList<Long> sousPointsIDs) {
		this.sousPointsIDs = sousPointsIDs;
	}
	
	public Point deepCopy(){
		
		String titre = new String(this.titre);
		
		Point point = new Point(titre);
		
		return point;
	}
}