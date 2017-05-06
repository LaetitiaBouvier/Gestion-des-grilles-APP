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

import gestiondesgrillesapp.model.SousPoint;

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
			String content = request.getParameter("content");
			
			SousPoint sp = new SousPoint(content);
			
			em.getTransaction().begin();	//
			em.persist(sp);					//
			em.getTransaction().commit();	// Attention !!! les id's ne sont générées qu'après le commit de l'instance persistante associée !
											//
			long spID = sp.getID();			// Attention !!! les id's ne sont générées qu'après le commit de l'instance persistante associée !
			System.out.println(spID+" : "+content);
			
			sp.setContenu("content : "+content);
			em.getTransaction().begin();
			em.persist(sp);
			em.getTransaction().commit();
			
			System.out.println(sp.getID()+" : "+sp.getContenu());

			// _____________
			// Requete Objet
//			CriteriaBuilder cb = em.getCriteriaBuilder();
//			CriteriaQuery<Commentaire> q = cb.createQuery(Commentaire.class);
//			Root<Commentaire> c = q.from(Commentaire.class);
//			q.select(c);
//			
//			TypedQuery<Commentaire> query = em.createQuery(q);
//			List<Commentaire> cscsgList = query.getResultList();

			// ____________
			// Requete SQL
//				String query = "SELECT c FROM SousPoint c WHERE c.id == "+spID;
//				String query = "SELECT c FROM SousPoint c WHERE id="+spID;
//				String query = "SELECT c FROM SousPoint c";
				List<SousPoint> spList = em.createQuery("SELECT c FROM SousPoint c WHERE id="+spID, SousPoint.class).getResultList();
				
				request.setAttribute("spList", spList);
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
		doGet(request, response);
	}
}
