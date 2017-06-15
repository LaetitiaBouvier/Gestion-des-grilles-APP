package gestiondesgrillesapp.controller;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestiondesgrillesapp.model.*;

/**
 * Servlet implementation class CreationGrilleServlet
 */
@WebServlet("/CreationGrilleServlet")
public class CreationGrilleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreationGrilleServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Obtain a database connection:
		EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();

		try {
			// Handle a new guest (if any):
			String nomGrille = request.getParameter("nom_grille");
			Grille g = new Grille(nomGrille,true);
			em.getTransaction().begin();	//
			em.persist(g);					//
			em.getTransaction().commit();	// Attention !!! les id's ne sont générées qu'après le commit de l'instance persistante associée !
			long gID = g.getID();
			String titreGrille = g.getTitre();
			System.out.println("Grille n°"+gID+" : "+titreGrille);
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			
//			List<Competence> compList = em.createQuery("SELECT c FROM Competence c WHERE grilleID="+gID, Competence.class).getResultList();
			
			request.setAttribute("grilleID", gID);
			request.getRequestDispatcher("/View/jsp/ImportGrille.jsp")
//			request.getRequestDispatcher("CreationSousCompetencesServlet")
			.forward(request, response);
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			return;
		}
		
		finally {
			// Close the database connection:
			em.close();
		}
	}
		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
		}
	}
