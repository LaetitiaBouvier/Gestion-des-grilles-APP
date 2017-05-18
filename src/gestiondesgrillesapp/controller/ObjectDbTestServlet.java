package gestiondesgrillesapp.controller;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestiondesgrillesapp.model.Commentaire;
import gestiondesgrillesapp.model.Competence;
import gestiondesgrillesapp.model.Grille;
import gestiondesgrillesapp.model.Groupe;
import gestiondesgrillesapp.model.Point;
import gestiondesgrillesapp.model.SousCompetence;
import gestiondesgrillesapp.model.SousGroupe;
import gestiondesgrillesapp.model.SousPoint;
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
////			em.getTransaction().begin();
////			em.createQuery("DELETE FROM Tuteur").executeUpdate();
////			em.createQuery("DELETE FROM Eleve").executeUpdate();	// Gros problème : le type Eleve est introuvable alors qu'il trouve Tuteur (alors que Tuteur est supprimé) !!!
////			em.getTransaction().commit();
//			
//			
//			Commentaire com1 = new Commentaire("com1", -1, -1, "eleveSousPoint");
//			Commentaire com2 = new Commentaire("com2", -1, -1, "eleveSousPoint");
//			SousPoint sp1 = new SousPoint("sp1");
//			SousPoint sp2 = new SousPoint("sp2");
//			Point p1 = new Point("p1");
//			Point p2 = new Point("p2");
//			SousCompetence cs1 = new SousCompetence("sc1");
//			SousCompetence cs2 = new SousCompetence("sc2");
//			Competence c1 =new Competence("c1", "c1");
//			Competence c2 =new Competence("c2", "c2");
//			Grille grid1 = new Grille("g1", false);
//			Grille grid2 = new Grille("g2", false);
//			SousGroupe sg1 = new SousGroupe("sg1");
//			SousGroupe sg2 = new SousGroupe("sg2");
//			Groupe group1 = new Groupe("g1");
//			Groupe group2 = new Groupe("g2");
////			Promotion prom1 = new Promotion(2018, null, null);
////			Promotion prom2 = new Promotion(2019, null, null);
//			User usr1 = new User("a", "a", "a", "a", false);
//			User usr2 = new User("b", "b", "b", "b", true);
//			
//			em.getTransaction().begin();
//			em.persist(com1);
//			em.persist(com2);
//			em.persist(sp1);
//			em.persist(sp2);
//			em.persist(p1);
//			em.persist(p2);
//			em.persist(cs1);
//			em.persist(cs2);
//			em.persist(c1);
//			em.persist(c2);
//			em.persist(grid1);
//			em.persist(grid2);
//			em.persist(sg1);
//			em.persist(sg2);
//			em.persist(group1);
//			em.persist(group2);
////			em.persist(prom1);
////			em.persist(prom2);
//			em.persist(usr1);
//			em.persist(usr2);
//			em.getTransaction().commit();
//			
//			em.getTransaction().begin();	//
//			em.persist(sp);					//
//			em.getTransaction().commit();	// Attention !!! les id's ne sont générées qu'après le commit de l'instance persistante associée !
//											//
//			long spID = sp.getID();			// Attention !!! les id's ne sont générées qu'après le commit de l'instance persistante associée !
//			System.out.println(spID+" : "+content);
//			
//			sp.setContenu("content : "+content);
//			
//			em.getTransaction().begin();
//			em.persist(sp);
//			em.getTransaction().commit();
//			
//			System.out.println(sp.getID()+" : "+sp.getContenu());

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
//			String query = "SELECT c FROM SousPoint c WHERE c.id == "+spID;
//			String query = "SELECT c FROM SousPoint c WHERE id="+spID;
//			String query = "SELECT c FROM SousPoint c";
//			List<SousPoint> spList = em.createQuery("SELECT c FROM SousPoint c WHERE id="+spID, SousPoint.class).getResultList();
			
//			request.setAttribute("spList", spList);
//			request.getRequestDispatcher("/View/jsp/TestObjectDb.jsp").forward(request, response);

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
