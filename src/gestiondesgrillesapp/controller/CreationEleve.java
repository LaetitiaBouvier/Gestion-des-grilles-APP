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
 * Servlet implementation class CreationEleve
 */
@WebServlet("/CreationEleve")
public class CreationEleve extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreationEleve() {
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
			String lignes = request.getParameter("ligne");
			long nbLignes = Long.parseLong(lignes);
			String colonnes = request.getParameter("colonne");
			long nbColonnes = Long.parseLong(colonnes);
			long i ;
			long j ;
			
			//ATTENTION : l'ideal serait de preciser 
			for (i=1; i<nbLignes+1; i++){
				String numeroUser = request.getParameter("champ["+i+"][0]");
				if (numeroUser != null){
				System.out.println(numeroUser);
				String nomUser = request.getParameter("champ["+i+"][1]");
				String prenomUser = request.getParameter("champ["+i+"][2]");
				String mailUser = request.getParameter("champ["+i+"][3]");
				String promotionUser = request.getParameter("champ["+i+"][4]");
				String groupeUser = request.getParameter("champ["+i+"][5]");
				String sousGroupeUser = request.getParameter("champ["+i+"][6]");
				User eleve = new User(nomUser, prenomUser, numeroUser, mailUser, false);
				em.getTransaction().begin();	//
				em.persist(eleve);					//
				em.getTransaction().commit();	// Attention !!! les id's ne sont générées qu'après le commit de l'instance persistante associée !
				long eleveID = eleve.getID();
				String numero = eleve.getNumero();
				String nom = eleve.getNom();
				String prenom = eleve.getPrenom();
				String mail = eleve.getEmail();
				System.out.println("Eleve ID "+eleveID+" : numero "+numero+", nom "+nom+", prenom "+prenom+", mail "+mail);

				if (em.getTransaction().isActive())
					em.getTransaction().rollback();
				}
		
				}}
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

