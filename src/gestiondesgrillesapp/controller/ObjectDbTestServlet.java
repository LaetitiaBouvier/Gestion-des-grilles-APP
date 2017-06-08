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

import gestiondesgrillesapp.model.User;

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
		
		String code = "initializeDataBase";
		request.setAttribute("code", code);
		request.getRequestDispatcher("ObjectDBUtilServlet").include(request,response);
		request.removeAttribute("code");

//		// Obtain a database connection:
//		EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
//		EntityManager em = emf.createEntityManager();
//
//		try {
////			String content = request.getParameter("content");
//
//			// _____________
//			// Requete Objet
////			CriteriaBuilder cb = em.getCriteriaBuilder();
////			CriteriaQuery<Commentaire> q = cb.createQuery(Commentaire.class);
////			Root<Commentaire> c = q.from(Commentaire.class);
////			q.select(c);
////			
////			TypedQuery<Commentaire> query = em.createQuery(q);
////			List<Commentaire> cscsgList = query.getResultList();
//
//			// ____________
//			// Requete SQL
//			List<User> userList = em.createQuery("SELECT c FROM User c", User.class).getResultList();
//			
//			request.setAttribute("userList", userList);
//			request.getRequestDispatcher("/View/jsp/TestObjectDb.jsp").forward(request, response);
//			
//		} finally {
//			// Close the database connection:
//			if (em.getTransaction().isActive())
//				em.getTransaction().rollback();
//			em.close();
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
