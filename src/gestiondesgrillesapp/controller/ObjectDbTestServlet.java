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
 * Servlet implementation class ObjectDbTestServlet
 */
@WebServlet("/ObjectDbTestServlet")
public class ObjectDbTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ObjectDbTestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// Obtain a database connection:
		EntityManagerFactory emf =
				(EntityManagerFactory)getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();

		try {
			// Handle a new guest (if any):
			String name = request.getParameter("test");
			if (name != null) {
				em.getTransaction().begin();
				em.persist(new CommentaireSousCompetenceSousGroupe());
				em.getTransaction().commit();
			}

			// Display the list of guests:
				List<CommentaireSousCompetenceSousGroupe> comsList = em.createQuery(
						"SELECT c FROM CommentaireSousCompetenceSousGroupe c", CommentaireSousCompetenceSousGroupe.class).getResultList();
				request.setAttribute("coms", comsList);
				request.getRequestDispatcher("/View/jsp/TestObjectDb.jsp")
				.forward(request, response);

		} finally {
			// Close the database connection:
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
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
