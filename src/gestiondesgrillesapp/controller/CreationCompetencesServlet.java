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
 * Servlet implementation class CreationCompetencesServlet
 */
@WebServlet("/CreationCompetencesServlet")
public class CreationCompetencesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreationCompetencesServlet() {
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

			//numero de la grille !! A ENVOYER !!!!!!
			long gID = Long.parseLong(request.getParameter("gID"));
			// nombres de lignes et de colonnes
			String lignes = request.getParameter("ligne");
			long nbLignes = Long.parseLong(lignes);
			String colonnes = request.getParameter("colonne");
			long nbColonnes = Long.parseLong(colonnes);
			long i ;
			

			for (i=1; i<nbLignes+1; i++)
			{
				String titreCompetence = request.getParameter("champ["+i+"][0]");
//				System.out.println("titrecompétence champ["+i+"][0] : "+titreCompetence);
				if (!titreCompetence.equals("."))
				{
					// Récupération des valeurs des 7 premiers inputs intéressants
					String descCompetence = request.getParameter("champ["+i+"][1]");
					String nomCoefCompetence = request.getParameter("champ["+i+"][2]");
					String nomSousCompetence = request.getParameter("champ["+i+"][3]");
					String nomCoefSousCompetence = request.getParameter("champ["+i+"][4]");
					String nomPoint = request.getParameter("champ["+i+"][5]");
					String nomSousPoint = request.getParameter("champ["+i+"][6]");
					
					
					//gestion de la compétence
					List<Competence> competenceList = em.createQuery("SELECT c FROM Competence c WHERE titre=\""+titreCompetence+"\" AND grilleID="+gID, Competence.class).getResultList();
    				Competence competence = null;
    				
    				if(competenceList.size() != 0)
    				{
    					// Extraction de la compétence
    					competence = (Competence) ObjectDBUtilServlet.extractOnlyOneObjectManagingExceptions(competenceList);
    					}
    				else
    				{
    					// Création et enregistrement dans la BDD de la compétence
    					competence = new Competence(titreCompetence, descCompetence);
        				em.getTransaction().begin();	
        				em.persist(competence);					
        				em.getTransaction().commit();	// Attention !!! les id's ne sont générées qu'après le commit de l'instance persistante associée !
        				competence.setGrilleID(gID);
        				competence.setCoefficient(Double.valueOf(nomCoefCompetence));
    				}
    				
    				// ____________________________________
    				// Recherche de la sous competence dans la BDD
    				List<SousCompetence> sousCompetenceList = em.createQuery("SELECT sc FROM SousCompetence sc WHERE sc.contenu=\""+nomSousCompetence+"\" AND sc.competenceID="+competence.getID(), SousCompetence.class).getResultList();
    				SousCompetence sousCompetence = null;
    				
    				if (sousCompetenceList.size() != 0)
    				{
    					sousCompetence = (SousCompetence) ObjectDBUtilServlet.extractOnlyOneObjectManagingExceptions(sousCompetenceList);
       				}
    				else
    				{
    					//Création et enregistrement dans la BDD de la sous compétence
    					sousCompetence = new SousCompetence(nomSousCompetence);
    					
    					em.getTransaction().begin();	
    					em.persist(sousCompetence);					
    					em.getTransaction().commit(); // Attention !!! les id's ne sont générées qu'après le commit de l'instance persistante associée !
        				sousCompetence.setCompetenceID(competence.getID());
        				sousCompetence.setCoefficient(Double.valueOf(nomCoefSousCompetence));
        				competence.addSousCompetenceID(sousCompetence.getID());
    				}
    				
					
    				// ____________________________________
    				// Recherche du point dans la BDD
    				List<Point> pointList = em.createQuery("SELECT p FROM Point p WHERE p.titre=\""+nomPoint+"\" AND p.sousCompetenceID="+sousCompetence.getID(), Point.class).getResultList();
    				Point point = null;
    				
    				if (pointList.size() != 0)
    				{
    					point = (Point) ObjectDBUtilServlet.extractOnlyOneObjectManagingExceptions(pointList);
       				}
    				else
    				{	
    					//Création et enregistrement dans la BDD du point
    					point = new Point(nomPoint);
        				em.getTransaction().begin();	
        				em.persist(point);					
        				em.getTransaction().commit();
        				point.setSousCompetenceID(sousCompetence.getID());
        				sousCompetence.addPointID(point.getID());
    				}
    					
    				
    				// ____________________________________
    				// Recherche du sous-point dans la BDD
    				List<SousPoint> sousPointList = em.createQuery("SELECT sp FROM SousPoint sp WHERE sp.contenu=\""+nomSousPoint+"\" AND sp.pointID="+point.getID(), SousPoint.class).getResultList();
    				SousPoint sousPoint = null;
    				
    				if (sousPointList.size() != 0)
    				{
    					sousPoint = (SousPoint) ObjectDBUtilServlet.extractOnlyOneObjectManagingExceptions(sousPointList);
       				}
    				else
    				{	
    					//Création et enregistrement dans la BDD du point
    					sousPoint = new SousPoint(nomSousPoint);
        				em.getTransaction().begin();	
        				em.persist(sousPoint);					
        				em.getTransaction().commit();
        				sousPoint.setPointID(point.getID());
        				point.addSousPointID(sousPoint.getID());
    				}
    				
    				//on fait persister les informations ajoutées
    				em.getTransaction().begin();	
    				em.persist(competence);
    				em.persist(sousCompetence);
    				em.persist(point);
    				em.persist(sousPoint);
    				em.getTransaction().commit();
    				
//    				//test en console
//    				System.out.println("CompetenceID "+competence.getID()+" : description "+competence.getDescription()+", coefficient "+competence.getCoefficient()+", id de la grille "+competence.getGrilleID());
//    				System.out.println("SousCompetenceID "+sousCompetence.getID()+", liée à la compétence :"+sousCompetence.getCompetenceID()+" : nom "+sousCompetence.getContenu()+", coefficient "+sousCompetence.getCoefficient());
//    				System.out.println("PointID "+point.getID()+", lié à la sous compétence :"+point.getSousCompetenceID()+", nom :"+point.getTitre());
//    				System.out.println("SousPointID "+sousPoint.getID()+", lié au point : "+sousPoint.getPointID()+" : nom : "+sousPoint.getContenu());
    				
    				
					}					
				}
			}
		
				finally {
					// Close the database connection:
					em.close();
					
				}
		request.getRequestDispatcher("/View/jsp/ImportGrille.jsp").forward(request, response);
			}

			/**
			 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
			 */
			protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				// TODO Auto-generated method stub
				doGet(request, response);
			}
		}
