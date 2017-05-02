package gestiondesgrillesapp.controller;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestiondesgrillesapp.model.CommentaireSousCompetenceSousGroupe;

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
			String content = request.getParameter("content");
			
			if (content != null) {
				CommentaireSousCompetenceSousGroupe cscsg = new CommentaireSousCompetenceSousGroupe();
				cscsg.setContenu(content);
				
				em.getTransaction().begin();
				em.persist(cscsg);
				em.getTransaction().commit();
			}
			
			// _____________
			// Requete Objet
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<CommentaireSousCompetenceSousGroupe> q = cb.createQuery(CommentaireSousCompetenceSousGroupe.class);
			Root<CommentaireSousCompetenceSousGroupe> c = q.from(CommentaireSousCompetenceSousGroupe.class);
			q.select(c);
			
			TypedQuery<CommentaireSousCompetenceSousGroupe> query = em.createQuery(q);
			List<CommentaireSousCompetenceSousGroupe> cscsgList = query.getResultList();

			// ____________
			// Requete SQL
//				List<CommentaireSousCompetenceSousGroupe> cscsgList = em.createQuery(
//						"SELECT c FROM CommentaireSousCompetenceSousGroupe c", CommentaireSousCompetenceSousGroupe.class).getResultList();
				
				request.setAttribute("cscsgList", cscsgList);
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
