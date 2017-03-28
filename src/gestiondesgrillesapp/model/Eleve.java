package gestiondesgrillesapp.model;

public class Eleve {
	
	/*
	 * ATTRIBUTS
	 */
	private SousGroupe sousGroupe;
	
	private String nom;
	private String prenom;
	private int id;
	// ajouter une photo ?
	
	private Grille grille;
	
	/*
	 * GETTERS and SETTERS
	 */
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Grille getGrille() {
		return grille;
	}

	public void setGrille(Grille grille) {
		this.grille = grille;
	}

	public SousGroupe getSousGroupe() {
		return sousGroupe;
	}

	public void setSousGroupe(SousGroupe sousGroupe) {
		this.sousGroupe = sousGroupe;
	}
}
