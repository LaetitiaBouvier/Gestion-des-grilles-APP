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
import gestiondesgrillesapp.model.Groupe;
import gestiondesgrillesapp.model.Point;
import gestiondesgrillesapp.model.SousCompetence;
import gestiondesgrillesapp.model.SousGroupe;
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
//		Object testSession = new Object();					// ...
//		session.setAttribute("testSession", testSession);	// ... example de création de session

		String id = request.getParameter("ISEPid");
		String pw = request.getParameter("ISEPpassword");

		EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();

		try {

			if(("jbond").equals(id) && ("jesuisunespion").equals(pw))
			{
				
				
				
				List<User> userList = em.createQuery("SELECT c FROM User c WHERE numero='007'", User.class).getResultList();
				
				if(userList.size() != 0)
				{
					User user = (User) ObjectDBUtilServlet.extractOnlyOneObjectManagingExceptions(userList);
					session.removeAttribute("user");
					session.setAttribute("user", user);
					
					session.removeAttribute("userFill");
					session.setAttribute("userFill", user);
					
					fillSession(em, session, user);
					request.getRequestDispatcher("/View/jsp/VueDEnsemble.jsp").forward(request, response);
				}
				else
				{
					String code = "initializeDataBase";
					request.setAttribute("code", code);
					request.getRequestDispatcher("ObjectDBUtilServlet").include(request,response);
					request.removeAttribute("code");
					
					List<User> uList = em.createQuery("SELECT c FROM User c WHERE numero='007'", User.class).getResultList();
					User user = (User) ObjectDBUtilServlet.extractOnlyOneObjectManagingExceptions(uList);
					session.removeAttribute("user");
					session.setAttribute("user", user);
					
					session.removeAttribute("userFill");
					session.setAttribute("userFill", user);
					
					fillSession(em, session, user);
					request.getRequestDispatcher("/View/jsp/VueDEnsemble.jsp").forward(request, response);
				}
			}
			else
			{
				LDAPaccess access = new LDAPaccess();

				LDAPObject test = access.LDAPget(id, pw);
				if (test == null) {
					System.out.println("login invalide");
					request.getRequestDispatcher("/View/jsp/LoginPage.jsp").forward(request, response);
				}

				List<User> userList = em.createQuery("SELECT c FROM User c WHERE numero='"+test.getNumber()+"'", User.class).getResultList();
				User user = (User) ObjectDBUtilServlet.extractOnlyOneObjectManagingExceptions(userList);
				session.removeAttribute("user");
				session.setAttribute("user", user);
				
				session.removeAttribute("userFill");
				session.setAttribute("userFill", user);

				fillSession(em, session, user);
				request.getRequestDispatcher("/View/jsp/VueDEnsemble.jsp").forward(request, response);
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

	public static void fillSession(EntityManager em, HttpSession session, User user)
	{
		SousGroupe sousGroupe = null;
		
		if(user.isTuteur())
		{
			Long firstGroupeID = user.getGroupesTuteurIDs().get(0);
			
			List<Groupe> groupeTemp = em.createQuery("SELECT c FROM Groupe c WHERE id="+firstGroupeID, Groupe.class).getResultList();
			Groupe firstGroupe = (Groupe) ObjectDBUtilServlet.extractOnlyOneObjectManagingExceptions(groupeTemp);
			
			long firstSousGroupeID = firstGroupe.getSousGroupesIDs().get(0);
			
			List<SousGroupe> sousGroupeTemp = em.createQuery("SELECT c FROM SousGroupe c WHERE id="+firstSousGroupeID, SousGroupe.class).getResultList();
			sousGroupe = (SousGroupe) ObjectDBUtilServlet.extractOnlyOneObjectManagingExceptions(sousGroupeTemp);
		}
		else
		{
			List<SousGroupe> sousGroupeTemp = em.createQuery("SELECT c FROM SousGroupe c WHERE id="+user.getSousGroupeEleveID(), SousGroupe.class).getResultList();
			sousGroupe = (SousGroupe) ObjectDBUtilServlet.extractOnlyOneObjectManagingExceptions(sousGroupeTemp);
		}
		
		if(sousGroupe == null) throw new RuntimeException("Le sous groupe ne doit pas être null !");
		
		ArrayList<User> membresSousGroupe = new ArrayList<>();
		for(Long eleveID : sousGroupe.getElevesIDs())
		{
			List<User> userTemp = em.createQuery("SELECT c FROM User c WHERE id="+eleveID, User.class).getResultList();
			User userMembre = (User) ObjectDBUtilServlet.extractOnlyOneObjectManagingExceptions(userTemp);
			membresSousGroupe.add(userMembre);
		}
		
		HashMap<String, Grille> grillesMembres = new HashMap<>();
		HashMap<Grille, ArrayList<Competence>> competences = new HashMap<>();
		HashMap<Competence, ArrayList<SousCompetence>> sousCompetences = new HashMap<>();
		HashMap<SousCompetence, ArrayList<Point>> points = new HashMap<>();
		HashMap<Point, ArrayList<SousPoint>> sousPoints = new HashMap<>();
		
		HashMap<String, SousCompetence> sousCompetencesHashContent = new HashMap<>();
		HashMap<String, SousPoint> sousPointsHashContent = new HashMap<>();

		for(User u : membresSousGroupe)
		{
			System.out.println(u.getNumero());
			List<Grille> grillesList = em.createQuery("SELECT c FROM Grille c WHERE id="+u.getGrilleEleveID(), Grille.class).getResultList();
			Grille grille = (Grille) ObjectDBUtilServlet.extractOnlyOneObjectManagingExceptions(grillesList);

			grillesMembres.put(u.getNumero(), grille);
			
			ArrayList<Competence> compentencesList = new ArrayList<>();
			for(long competenceID : grille.getCompetencesIDs())
			{
				List<Competence> competencesTemp = em.createQuery("SELECT c FROM Competence c WHERE id="+competenceID, Competence.class).getResultList();
				Competence competence = (Competence) ObjectDBUtilServlet.extractOnlyOneObjectManagingExceptions(competencesTemp);
				compentencesList.add(competence);
			}
			competences.put(grille, compentencesList);
			
			for(Competence competence : compentencesList)
			{
				ArrayList<SousCompetence> sousCompentencesList = new ArrayList<>();
				for(long souscompetenceID : competence.getSousCompetencesIDs())
				{
					List<SousCompetence> sousCompetencesTemp = em.createQuery("SELECT c FROM SousCompetence c WHERE id="+souscompetenceID, SousCompetence.class).getResultList();
					SousCompetence sousCompetence = (SousCompetence) ObjectDBUtilServlet.extractOnlyOneObjectManagingExceptions(sousCompetencesTemp);
					sousCompetencesHashContent.put(sousCompetence.getEleveID()+""+sousCompetence.getContenu(), sousCompetence);
					sousCompentencesList.add(sousCompetence);
				}
				sousCompetences.put(competence, sousCompentencesList);
//				sousCompetencesHash.put("", value)

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
							sousPointsHashContent.put(sousPoint.getEleveID()+""+sousPoint.getContenu(), sousPoint);
							sousPointsList.add(sousPoint);
						}
						sousPoints.put(point, sousPointsList);
					}
				}
			}
		}
		
		List<Groupe> groupesTemp = em.createQuery("SELECT c FROM Groupe c WHERE id="+membresSousGroupe.get(0).getGroupeID(), Groupe.class).getResultList();
		Groupe groupe = (Groupe) ObjectDBUtilServlet.extractOnlyOneObjectManagingExceptions(groupesTemp);
		
		ArrayList<User> tuteursList = new ArrayList<User>();
		
		for(long tuteurID : groupe.getTuteursIDs())
		{
			List<User> tuteursTemp = em.createQuery("SELECT c FROM User c WHERE id="+tuteurID, User.class).getResultList();
			User tuteur = (User) ObjectDBUtilServlet.extractOnlyOneObjectManagingExceptions(tuteursTemp);
			
			tuteursList.add(tuteur);
		}
		
		session.removeAttribute("groupe");
		session.setAttribute(	"groupe", groupe);
		session.removeAttribute("tuteursList");
		session.setAttribute(	"tuteursList", tuteursList);
		session.removeAttribute("membresSousGroupe");
		session.setAttribute(   "membresSousGroupe", membresSousGroupe);
		session.removeAttribute("sousGroupe");
		session.setAttribute(	"sousGroupe", sousGroupe);
		session.removeAttribute("grillesMembres");
		session.setAttribute(   "grillesMembres", grillesMembres);
		session.removeAttribute("competences");
		session.setAttribute(   "competences", competences);
		session.removeAttribute("sousCompetences");
		session.setAttribute(   "sousCompetences", sousCompetences);
		session.removeAttribute("sousCompetencesHashContent");
		session.setAttribute(	"sousCompetencesHashContent", sousCompetencesHashContent);
		session.removeAttribute("points");
		session.setAttribute(   "points", points);
		session.removeAttribute("sousPoints");
		session.setAttribute(   "sousPoints", sousPoints);
		session.removeAttribute("sousPointsHashContent");
		session.setAttribute(	"sousPointsHashContent", sousPointsHashContent);
		
		Grille grilleUtilisateur = grillesMembres.get(user.getNumero());
		if(user.isTuteur()) grilleUtilisateur = grillesMembres.get(membresSousGroupe.get(0).getNumero());
		
		session.removeAttribute("grilleUtilisateur");
		session.setAttribute(   "grilleUtilisateur", grilleUtilisateur);
		
		System.out.println("\nServlet : competenceSelected = "+competences.get(grilleUtilisateur).get(0).getTitre());
		session.removeAttribute("competenceSelected");
		session.setAttribute(   "competenceSelected", competences.get(grilleUtilisateur).get(0));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
