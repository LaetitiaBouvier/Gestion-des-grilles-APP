package gestiondesgrillesapp.model;

import java.util.ArrayList;

public class Promotion {
	
	/*
	 * ATTRIBUTS
	 */
	private int anneeObtensionDiplome;
	private ArrayList<Groupe> groupes;

	/*
	 * GETTERS and SETTERS
	 */
	public ArrayList<Groupe> getGroupes() {
		return groupes;
	}

	public void setGroupes(ArrayList<Groupe> groupes) {
		this.groupes = groupes;
	}

	public int getAnneeObtensionDiplome() {
		return anneeObtensionDiplome;
	}

	public void setAnneeObtensionDiplome(int anneeObtensionDiplome) {
		this.anneeObtensionDiplome = anneeObtensionDiplome;
	}
}
