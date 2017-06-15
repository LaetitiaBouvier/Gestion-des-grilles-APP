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

import gestiondesgrillesapp.model.Groupe;
import gestiondesgrillesapp.model.Promotion;
import gestiondesgrillesapp.model.SousGroupe;
import gestiondesgrillesapp.model.User;

/**
 * Servlet implementation class CreationTuteurServlet
 */
@WebServlet("/CreationTuteurServlet")
public class CreationTuteurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreationTuteurServlet() {
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

    		// nombres de lignes et de colonnes
    		String lignes = request.getParameter("ligne");
    		long nbLignes = Long.parseLong(lignes);

    		for (int i = 1; i < nbLignes+1; i++)
    		{
    			String numeroTuteur = request.getParameter("champ["+i+"][0]");
    			
    			if (numeroTuteur != null)
    			{
    				
    				// Récupération des valeurs des inputs
    				String nomTuteur = request.getParameter("champ["+i+"][1]");
    				String prenomTuteur = request.getParameter("champ["+i+"][2]");
    				String mailTuteur = request.getParameter("champ["+i+"][3]");
    				String bureau = request.getParameter("champ["+i+"][4]");
    				boolean isRespoModule = Boolean.parseBoolean(request.getParameter("champ["+i+"][5]"));
    				
    				List<User> usersList = em.createQuery("SELECT u FROM User u WHERE numero=\""+numeroTuteur+"\"", User.class).getResultList();
    				User tuteur = null;
    				
    				if(usersList.size() != 0)
    				{
    					// Extraction de l'élève
    					tuteur = (User) ObjectDBUtilServlet.extractOnlyOneObjectManagingExceptions(usersList);
    				}
    				else
    				{
    					// Création et enregistrement dans la BDD de l'eleve
    					tuteur = new User(nomTuteur, prenomTuteur, numeroTuteur, mailTuteur, false);
    					em.getTransaction().begin();	
    					em.persist(tuteur);					
    					em.getTransaction().commit();	// Attention !!! les id's ne sont générées qu'après le commit de l'instance persistante associée !
    				}
    				
    				tuteur.setBureauTuteur(bureau);
    				tuteur.setRespoModule(isRespoModule);
    				
    				em.getTransaction().begin();
					em.persist(tuteur);
					em.getTransaction().commit();
    				
    				int j = 6;
    				boolean isNull = false;
    				while(!isNull)
    				{
    					String groupeEtPromo = request.getParameter("champ["+i+"]["+j+"]");
    					j++;
    					
    					String groupeStr = groupeEtPromo.split(" : ")[0];
    					String promotionStr = groupeEtPromo.split(" : ")[1];
    					
    					List<Promotion> promotionList = em.createQuery("SELECT p FROM Promotion p WHERE anneeObtensionDiplome="+Long.parseLong(promotionStr), Promotion.class).getResultList();
        				Promotion promotion = null;
        				
        				if (promotionList.size() != 0)
        				{
        					promotion = (Promotion) ObjectDBUtilServlet.extractOnlyOneObjectManagingExceptions(promotionList);
        					
        					List<Groupe> groupesList = em.createQuery("SELECT g FROM Groupe g WHERE nom=\""+groupeStr+"\" AND promotionID="+promotion.getID(), Groupe.class).getResultList();
        					Groupe groupe = null;
        					
        					if(groupesList.size() != 0)
        					{
        						groupe = (Groupe) ObjectDBUtilServlet.extractOnlyOneObjectManagingExceptions(groupesList);
        						groupe.addTuteurID(tuteur.getID());
        						
        						em.getTransaction().begin();
        						em.persist(groupe);
        						em.getTransaction().commit();
        					}
        				}
    					
    					if(groupeEtPromo.equals(".")){
    						isNull = true;
    						break;
    					}
    				}
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
    	
    	request.getRequestDispatcher("/View/jsp/ImportTuteur.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
