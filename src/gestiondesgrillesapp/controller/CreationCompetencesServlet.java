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
			long j ;

			for (i=1; i<nbLignes+1; i++)
			{
				String titreCompetence = request.getParameter("champ["+i+"][0]");
				System.out.println("titrecompétence champ["+i+"][0] : "+titreCompetence);
				if (titreCompetence != null)
				{
					// Récupération des valeurs des 7 premiers inputs intéressants
					String descCompetence = request.getParameter("champ["+i+"][1]");
					String nomCoefCompetence = request.getParameter("champ["+i+"][2]");
					String nomSousCompetence = request.getParameter("champ["+i+1+"][3]");
					String nomCoefSousCompetence = request.getParameter("champ["+i+1+"][4]");
					String nomPoint = request.getParameter("champ["+i+2+"][5]");
					String nomSousPoint = request.getParameter("champ["+i+2+"][6]");
					
					// Gestion de la compétence en question
					Competence competence = null;

    				// Création et enregistrement dans la BDD de la compétence, paramétrage de ses attributs
    				competence = new Competence(titreCompetence, descCompetence);
    				em.getTransaction().begin();	
    				em.persist(competence);					
    				em.getTransaction().commit();	// Attention !!! les id's ne sont générées qu'après le commit de l'instance persistante associée !
    				long IDcomp = competence.getID();
    				competence.setGrilleID(gID);
    				competence.setCoefficient(Double.valueOf(nomCoefCompetence));
    				// Création et enregistrement dans la BDD de la souscompétence, paramétrage de ses attributs
    				SousCompetence sousCompetence = null;
    				sousCompetence = new SousCompetence(nomSousCompetence);
    				em.getTransaction().begin();	
    				em.persist(sousCompetence);					
    				em.getTransaction().commit();	// Attention !!! les id's ne sont générées qu'après le commit de l'instance persistante associée !
    				long IDsousComp = sousCompetence.getID();
    				sousCompetence.setCompetenceID(IDcomp);
    				sousCompetence.setCoefficient(Double.valueOf(nomCoefSousCompetence));
    				competence.addSousCompetenceID(IDsousComp);
    				// Création et enregistrement dans la BDD du point, paramétrage de ses attributs
    				Point point = null;
    				point = new Point(nomPoint);
    				em.getTransaction().begin();	
    				em.persist(point);					
    				em.getTransaction().commit();
    				point.setSousCompetenceID(IDsousComp);
    				long IDpoint = point.getID();
    				sousCompetence.addPointID(IDpoint);
    				// Création et enregistrement dans la BDD du sous-point, paramétrage de ses attributs*
    				SousPoint sousPoint = null;
    				sousPoint = new SousPoint(nomSousPoint);
    				em.getTransaction().begin();	
    				em.persist(sousPoint);					
    				em.getTransaction().commit();
    				sousPoint.setPointID(IDpoint);
    				long IDsousPoint = sousPoint.getID();
    				point.addSousPointID(IDsousPoint);
    				
    				//on fait persister les informations ajoutées
    				em.getTransaction().begin();	
    				em.persist(competence);
    				em.persist(sousCompetence);
    				em.persist(point);
    				em.persist(sousPoint);
    				em.getTransaction().commit();
    				
    				//on ADD les trucs dans les groupes
    				System.out.println("CompetenceID "+competence.getID()+" : description "+competence.getDescription()+", coefficient "+competence.getCoefficient()+", id de la grille "+competence.getGrilleID());
    				System.out.println("SousCompetenceID "+sousCompetence.getID()+" : coefficient "+sousCompetence.getCoefficient());
    				
    				
    				
					}					
				}
			}
		
				finally {
					// Close the database connection:
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
