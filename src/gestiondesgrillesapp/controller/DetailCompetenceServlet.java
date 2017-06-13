package gestiondesgrillesapp.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gestiondesgrillesapp.model.SousCompetence;
import gestiondesgrillesapp.model.User;

/**
 * Servlet implementation class DetailCompetenceServlet
 */
@WebServlet("/DetailCompetenceServlet")
public class DetailCompetenceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailCompetenceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// Obtain a database connection:
		EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();

		try
		{
			String req = "";
			String code = "";
			String id = "";
			Enumeration<String> enu = request.getParameterNames();
			while(enu.hasMoreElements())
			{
				String name = enu.nextElement();
				code = name.split("_")[0];
				id = name.split("_")[1];
				System.out.println("nom : "+name+" | id : "+id);
				req = request.getParameter(name);
			}
			if(req == null) throw new RuntimeException("La requête ne doit pas être null !");
			
			HttpSession sess = request.getSession(false);
//			Object[] obj = (Object[]) sess.getAttribute("obj");
			
//			String code = (String) obj[0];
			
//			long sousCompetenceID = 0;
			
			if(("CommentaireIndividuelSousCompetence").equals(code))
			{
//				SousCompetence sousCompetenceSelected = (SousCompetence) obj[1];
//				sousCompetenceID =  (long) obj[1];
				System.out.println("!!! "+req+" !!!");
				
				SousCompetence sousCompetenceSelected = em.find(SousCompetence.class, Long.parseLong(id));
				
				// MAJ BDD
				// cf. http://www.objectdb.com/java/jpa/persistence/update
				em.getTransaction().begin();
				sousCompetenceSelected.setCommentaireIndividuel(req);
				em.getTransaction().commit();
				
				// MAJ session
				LoginServlet.fillSession(em, sess, (User) sess.getAttribute("user"));
				
//				SousCompetence sc = (SousCompetence) obj[2];
//				Competence c = (Competence) obj[3];
//				HashMap<Competence, ArrayList<SousCompetence>> sousCompetences = (HashMap<Competence, ArrayList<SousCompetence>>) sess.getAttribute("sousCompetences");
//				for(SousCompetence sousCompetence : sousCompetences.get(c))
//				{
//					if(sousCompetence.getID() == sc.getID())
//					{
//						System.out.println(sousCompetence.getCommentaireIndividuel());
//						System.out.println(sc.getCommentaireIndividuel());
//					}
//				}
				
//				SousCompetence sousCompetence = (SousCompetence) obj[2];
//				sousCompetence.setCommentaireIndividuel(req);
			}
			
			List<SousCompetence> sousCompetencesTemp = em.createQuery("SELECT c FROM SousCompetence c WHERE id="+Long.parseLong(id), SousCompetence.class).getResultList();
			SousCompetence sousCompetence = (SousCompetence) ObjectDBUtilServlet.extractOnlyOneObjectManagingExceptions(sousCompetencesTemp);
			System.out.println("======>"+sousCompetence.getCommentaireIndividuel()+"<======");
		}
		finally
		{
			// Close the database connection:
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			em.close();
		}
		request.getRequestDispatcher("/View/jsp/DetailCompetence.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
