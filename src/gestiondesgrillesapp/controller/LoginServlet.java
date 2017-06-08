package gestiondesgrillesapp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gestiondesgrillesapp.ldap.LDAPObject;
import gestiondesgrillesapp.ldap.LDAPaccess;
import gestiondesgrillesapp.model.Competence;
import gestiondesgrillesapp.model.Grille;
import gestiondesgrillesapp.model.Point;
import gestiondesgrillesapp.model.SousCompetence;
import gestiondesgrillesapp.model.SousPoint;
import gestiondesgrillesapp.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);		// Création de la session
		Object testSession = new Object();					// ...
		session.setAttribute("testSession", testSession);	// ... example de création de session

		String id = request.getParameter("ISEPid");
		String pw = request.getParameter("ISEPpassword");

		EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();

		try {

			if(("jbond").equals(id) && ("jesuisunespion").equals(pw))
			{
				//				List<User> userList = em.createQuery("SELECT c FROM User c WHERE numero='007'", User.class).getResultList();
				//				User user = (User) ObjectDBUtilServlet.extractOnlyOneObjectManagingExceptions(userList);
				//				session.setAttribute("user", user);
			}
			else if (("test").equals(id) && ("test").equals(pw))
			{
				List<User> userList = em.createQuery("SELECT c FROM User c WHERE numero='0000'", User.class).getResultList();
				User user = (User) ObjectDBUtilServlet.extractOnlyOneObjectManagingExceptions(userList);
				session.setAttribute("user", user);

				fillSessionForEleve(em, session, user);

				request.getRequestDispatcher("/View/jsp/DetailCompetence.jsp").forward(request, response);
			}
			else
			{
				LDAPaccess access = new LDAPaccess();

				LDAPObject test = access.LDAPget(id, pw);
				if (test == null) {
					System.out.println("login invalide");
					System.exit(1);
				}
				System.out.println(test.toString());
				System.out.println(test.getNumber());

				List<User> userList = em.createQuery("SELECT c FROM User c WHERE numero='"+test.getNumber()+"'", User.class).getResultList();
				User user = (User) ObjectDBUtilServlet.extractOnlyOneObjectManagingExceptions(userList);
				session.setAttribute("user", user);

				System.out.println(user.getEmail());

				if(user.isRespoModule()){

				}
				else if(user.isTuteur()){

				}
				else{
					fillSessionForEleve(em, session, user);
				}
			}


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close the database connection:
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			em.close();
		}
	}

	public void fillSessionForEleve(EntityManager em, HttpSession session, User user){

		List<Grille> grillesList = em.createQuery("SELECT c FROM Grille c WHERE id="+user.getGrilleEleveID(), Grille.class).getResultList();
		Grille grille = (Grille) ObjectDBUtilServlet.extractOnlyOneObjectManagingExceptions(grillesList);
		System.out.println("\nGrille associée : "+grille.getTitre());

		session.setAttribute("grille", grille);

		ArrayList<Competence> competences = new ArrayList<>();
		for(long competenceID : grille.getCompetencesIDs())
		{
			List<Competence> competencesTemp = em.createQuery("SELECT c FROM Competence c WHERE id="+competenceID, Competence.class).getResultList();
			Competence competence = (Competence) ObjectDBUtilServlet.extractOnlyOneObjectManagingExceptions(competencesTemp);
			competences.add(competence);
		}
		session.setAttribute("competences", competences);

		HashMap<Competence, ArrayList<SousCompetence>> sousCompetences = new HashMap<>();
		HashMap<SousCompetence, ArrayList<Point>> points = new HashMap<>();
		HashMap<Point, ArrayList<SousPoint>> sousPoints = new HashMap<>();

		for( Competence competence : competences)
		{
			ArrayList<SousCompetence> sousCompentencesList = new ArrayList<>();
			for(long souscompetenceID : competence.getSousCompetencesIDs())
			{
				List<SousCompetence> sousCompetencesTemp = em.createQuery("SELECT c FROM SousCompetence c WHERE id="+souscompetenceID, SousCompetence.class).getResultList();
				SousCompetence sousCompetence = (SousCompetence) ObjectDBUtilServlet.extractOnlyOneObjectManagingExceptions(sousCompetencesTemp);
				sousCompentencesList.add(sousCompetence);
			}
			sousCompetences.put(competence, sousCompentencesList);
			
			for(SousCompetence sousCompetence : sousCompentencesList)
			{
				ArrayList<Point> pointsList = new ArrayList<>();
				for(long pointID : sousCompetence.getPointsIDs())
				{
					List<Point> pointsTemp = em.createQuery("SELECT c FROM Point c WHERE id="+pointID, Point.class).getResultList();
					Point point = (Point) ObjectDBUtilServlet.extractOnlyOneObjectManagingExceptions(pointsTemp);
					pointsList.add(point);
				}
				points.put(sousCompetence, pointsList);
				
				for(Point point : pointsList)
				{
					ArrayList<SousPoint> sousPointsList = new ArrayList<>();
					for(long sousPointID : point.getSousPointsIDs())
					{
						List<SousPoint> sousPointsTemp = em.createQuery("SELECT c FROM SousPoint c WHERE id="+sousPointID, SousPoint.class).getResultList();
						SousPoint sousPoint = (SousPoint) ObjectDBUtilServlet.extractOnlyOneObjectManagingExceptions(sousPointsTemp);
						sousPointsList.add(sousPoint);
					}
					sousPoints.put(point, sousPointsList);
				}
			}
		}

		session.setAttribute("souscompetences", sousCompetences);
		session.setAttribute("points", points);
		session.setAttribute("souspoints", sousPoints);
		session.setAttribute("competenceSelected", competences.get(0));
		System.out.println("\nServlet : competenceSelected = "+competences.get(0).getTitre());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
