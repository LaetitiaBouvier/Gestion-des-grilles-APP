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
 * Servlet implementation class CreationGrilleServlet2
 */
@WebServlet("/CreationGrilleServlet2")
public class CreationGrilleServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreationGrilleServlet2() {
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
			
			// nombres de lignes et de colonnes
			String lignes = request.getParameter("");
			long nbLignes = Long.parseLong(lignes);
			String colonnes = request.getParameter("");
			long nbColonnes = Long.parseLong(colonnes);
			long i ;
			long j ;
			
			for (i=1; i<nbLignes; i++){
				for (j=0; j<nbColonnes; j++){
					if(j==0){
						String nomCompetence = request.getParameter("champ["+i+"]["+j+"]");
					}
					if(j==1){
						String descriptionCompetence = request.getParameter("champ["+i+"]["+j+"]");
					}
					if(j==2){
						String coefficientCompetence = request.getParameter("champ["+i+"]["+j+"]");
					}
					if(j==3){
						String nomSousCompetence = request.getParameter("champ["+i+"]["+j+"]");
						if(nomSousCompetence != null){
							SousCompetence sc = new SousCompetence(nomSousCompetence);
						}
					}
					if(j==4){
						String coefficientSousCompetence = request.getParameter("champ["+i+"]["+j+"]");
					}
					if(j==5){
						String point = request.getParameter("champ["+i+"]["+j+"]");
						if(point != null){
							Point p = new Point(point);
						}
					}
					if(j==6){
						String sousPoint = request.getParameter("champ["+i+"]["+j+"]");
						if(sousPoint != null){
							SousPoint sp = new SousPoint(sousPoint);
						}
					}
				}
				
			}
			String nomGrille = request.getParameter("nom_grille");
			Grille g = new Grille(nomGrille,true);
			em.getTransaction().begin();	//
			em.persist(g);					//
			em.getTransaction().commit();	// Attention !!! les id's ne sont générées qu'après le commit de l'instance persistante associée !
			long gID = g.getID();
			String titreGrille = g.getTitre();
			System.out.println("Grille n°"+gID+" : "+titreGrille);
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			
			for(int i = 1; i <=100; i++){
					String nomCompetence = request.getParameter("nom_competence"+i);
					if(nomCompetence != null){
						String descCompetence = request.getParameter("desc_competence"+i);
						String coefCompetence = request.getParameter("coef_competence"+i);
						Competence comp = new Competence(nomCompetence,descCompetence);
						comp.setCoefficient(Double.valueOf(coefCompetence));
						comp.setGrilleID(gID);
						
						em.getTransaction().begin();
						em.persist(comp);					
						em.getTransaction().commit();  // Attention !!! les id's ne sont générées qu'après le commit de l'instance persistante associée !
						
						long compID=comp.getID();
						String titreComp=comp.getTitre();
						String descComp=comp.getDescription();
						double coefComp=comp.getCoefficient();
						
//						System.out.println("Competence n°:"+compID+"; nomCompetence : "+nomCompetence+"; descCompetence : "+descCompetence+"; coefCompetence : "+coefCompetence);
						System.out.println("Competence n°:"+compID+"; nomCompetence : "+titreComp+"; descCompetence : "+descComp+"; coefCompetence : "+coefComp);
						
					}
							}
			List<Competence> compList = em.createQuery("SELECT c FROM Competence c WHERE grilleID="+gID, Competence.class).getResultList();
			
			request.setAttribute("compList", compList);
			request.getRequestDispatcher("/View/jsp/CreationSousCompetences.jsp")
//			request.getRequestDispatcher("CreationSousCompetencesServlet")
			.forward(request, response);
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			return;
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

