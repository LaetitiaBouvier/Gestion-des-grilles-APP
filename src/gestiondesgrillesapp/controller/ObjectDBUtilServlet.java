package gestiondesgrillesapp.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServlet;

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
			Tuteur tuteur = new Tuteur(null, "Hervé", "Feller", "herve.feller@isep.fr"); // ==> Ici "null" correspond à l' "ArrayList<Long> groupesIDs" dont on s'occupera plus tard

		} finally {
			// Close the database connection:
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			em.close();
		}
	}
}