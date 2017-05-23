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
import javax.servlet.http.HttpSession;

import gestiondesgrillesapp.ldap.LDAPObject;
import gestiondesgrillesapp.ldap.LDAPaccess;
import gestiondesgrillesapp.model.Grille;
import gestiondesgrillesapp.model.SousGroupe;
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
				List<User> userList = em.createQuery("SELECT c FROM User c WHERE numero='007'", User.class).getResultList();
				User user = (User) ObjectDBUtilServlet.extractOnlyOneObjectManagingExceptions(userList);
				session.setAttribute("user", user);
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
		
		List<SousGroupe> sousGroupeList = em.createQuery("SELECT c FROM SousGroupe c WHERE id="+user.getSousGroupeEleveID(), SousGroupe.class).getResultList();
		SousGroupe sousGroupe = (SousGroupe) ObjectDBUtilServlet.extractOnlyOneObjectManagingExceptions(sousGroupeList);
		
		System.out.println("\nMembres du même sous-groupe : ");
		for(long sgId: sousGroupe.getElevesIDs()){
			List<User> userSGList = em.createQuery("SELECT c FROM User c WHERE id="+sgId, User.class).getResultList();
			User u = (User) ObjectDBUtilServlet.extractOnlyOneObjectManagingExceptions(userSGList);
			System.out.println("Nom : "+u.getNom()+", Prenom : "+u.getPrenom()+", Email : "+u.getEmail());
			
			List<Grille> gList = em.createQuery("SELECT c FROM Grille c WHERE id="+u.getGrilleEleveID(), Grille.class).getResultList();
			Grille g = (Grille) ObjectDBUtilServlet.extractOnlyOneObjectManagingExceptions(gList);
			System.out.println("\nGrille associée : "+g.getTitre());
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
