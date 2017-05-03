package gestiondesgrillesapp.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServlet;

import gestiondesgrillesapp.model.Eleve;
import gestiondesgrillesapp.model.Groupe;
import gestiondesgrillesapp.model.SousGroupe;
import gestiondesgrillesapp.model.Tuteur;

public class ObjectDBUtilServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ObjectDBUtilServlet() {
		super();
	}

	public void reInitializeDataBase(){

	}

	public void emptyDataBase(){

		// Obtain a database connection:
		EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();

		try{
			em.createQuery("DELETE FROM Object").executeUpdate(); // cf http://www.objectdb.com/java/jpa/query/jpql/delete 
			//=> this query can be used to delete all the objects in the database

		} finally {
			// Close the database connection:
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			em.close();
		}
	}

	public void initializeDataBase(){

		// Obtain a database connection:
		EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();

		try{
			Tuteur tuteurJB = new Tuteur("Bond", "James", "007", "james.bond@jesuisunespion.com", "MI6", true);
			
			Eleve eleveST = new Eleve("Stéphane", "Tzvetkov", "9482", "stephane.tzvetkov@isep.fr");
			Eleve elevePPC = new Eleve("Pierre-Philippe", "Cordier", "9551", "pierre-philippe.corder@isep.fr");
			Eleve eleveND = new Eleve("Nicolas", "Dubes", "9502", "nicolas.dubes@isep.fr");
			
			Eleve elebeLB = new Eleve("Laëtitia", "Bouvier", "9555", "laetitia.bouvier@isep.fr");
			Eleve elebeCB = new Eleve("Camille", "Duboue", "9648", "camille.duboue@isep.fr");
			
			SousGroupe sousGroupeGarçons = new SousGroupe("Garçons");
				sousGroupeGarçons.addEleveID(eleveST.getID());
				eleveST.setSousGroupeID(sousGroupeGarçons.getID());
				
				sousGroupeGarçons.addEleveID(elevePPC.getID());
				elevePPC.setSousGroupeID(sousGroupeGarçons.getID());
				
				sousGroupeGarçons.addEleveID(eleveND.getID());
				eleveND.setSousGroupeID(sousGroupeGarçons.getID());
			
			SousGroupe sousGroupeFilles = new SousGroupe("Filles");
				sousGroupeFilles.addEleveID(elebeLB.getID());
				elebeLB.setSousGroupeID(sousGroupeFilles.getID());
				
				sousGroupeFilles.addEleveID(elebeCB.getID());
				elebeCB.setSousGroupeID(sousGroupeFilles.getID());
			
			Groupe groupeLogiciel = new Groupe("Groupe Logiciel");
				groupeLogiciel.addSousGroupeID(sousGroupeGarçons.getID());
				sousGroupeGarçons.setGroupeID(groupeLogiciel.getID());
				
				groupeLogiciel.addTuteurID(tuteurJB.getID());
				tuteurJB.addGroupeID(groupeLogiciel.getID());
			
			Groupe groupeSI = new Groupe("GroupeSI");
				groupeSI.addSousGroupeID(sousGroupeFilles.getID());
				sousGroupeFilles.setGroupeID(groupeSI.getID());
				
				groupeSI.addTuteurID(tuteurJB.getID());
				tuteurJB.addGroupeID(groupeSI.getID());
			
			
				
		} finally {
			// Close the database connection:
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			em.close();
		}
	}
}