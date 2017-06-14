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
 * Servlet implementation class CreationEleveServlet
 */
@WebServlet("/CreationEleveServlet")
public class CreationEleveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreationEleveServlet() {
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
			 
			for (i=1; i<nbLignes+1; i++){
				String numeroUser = request.getParameter("champ["+i+"][0]");
				if (numeroUser != null){
				// Récupération des valeurs des inputs
				String nomUser = request.getParameter("champ["+i+"][1]");
				String prenomUser = request.getParameter("champ["+i+"][2]");
				String mailUser = request.getParameter("champ["+i+"][3]");
				String nomPromotionUser = request.getParameter("champ["+i+"][4]");
				String nomGroupeUser = request.getParameter("champ["+i+"][5]");
				String nomSousGroupeUser = request.getParameter("champ["+i+"][6]");
				// Enregistrement dans la BDD de l'eleve
				User eleve = new User(nomUser, prenomUser, numeroUser, mailUser, false);
				em.getTransaction().begin();	
				em.persist(eleve);					
				em.getTransaction().commit();	// Attention !!! les id's ne sont générées qu'après le commit de l'instance persistante associée !
				// Recherche de la promo dans la BDD
				Promotion promo = em.createQuery("SELECT p FROM Promotion p WHERE anneeObtensionDiplome="+nomPromotionUser, Promotion.class).getSingleResult();
				if (promo == null){
					//creation de la promotion
					Promotion promotionUser = new Promotion(Long.parseLong(nomPromotionUser));
					em.getTransaction().begin();	
					em.persist(promotionUser);					
					em.getTransaction().commit();	// Attention !!! les id's ne sont générées qu'après le commit de l'instance persistante associée !
					long idPromo = promotionUser.getID();
					eleve.setPromotionID(idPromo);
					}
				if (promo != null){
					long idPromo = promo.getID();
					eleve.setPromotionID(idPromo);
					}
				// Recherche du groupe dans la BDD
				Promotion promogroupe = em.createQuery("SELECT p FROM Promotion p WHERE anneeObtensionDiplome="+nomPromotionUser, Promotion.class).getSingleResult();
				Long idPromoGroupe = promogroupe.getID();
				Groupe grp = em.createQuery("SELECT g FROM Groupe g WHERE nom="+nomGroupeUser+" AND promotion="+(idPromoGroupe), Groupe.class).getSingleResult();
				if (grp == null){
					//creation du groupe
					Groupe groupeUser = new Groupe(nomGroupeUser);
					em.getTransaction().begin();	
					em.persist(groupeUser);					
					em.getTransaction().commit();	// Attention !!! les id's ne sont générées qu'après le commit de l'instance persistante associée !
					long idGroupe = groupeUser.getID();
					eleve.setGroupeID(idGroupe);
				}
				if (grp != null){
					long idGroupe = grp.getID();
					eleve.setGroupeID(idGroupe);
				}
				// Recherche du sous groupe dans la BDD
				Promotion promosousgroupe = em.createQuery("SELECT p FROM Promotion p WHERE anneeObtensionDiplome="+nomPromotionUser, Promotion.class).getSingleResult();
				Long idPromoSousGroupe = promosousgroupe.getID();
				Groupe groupesousgroupe = em.createQuery("SELECT g FROM Groupe g WHERE nom="+nomGroupeUser+" AND promotion="+(idPromoSousGroupe), Groupe.class).getSingleResult();
				long idGroupe = groupesousgroupe.getID();
				SousGroupe sousGrp = em.createQuery("SELECT sg FROM SousGroupe sg WHERE nom="+nomSousGroupeUser+" AND groupeID="+idGroupe, SousGroupe.class).getSingleResult();
				if (sousGrp == null){
					//creation du sous-groupe
					SousGroupe sousGroupeUser = new SousGroupe(nomSousGroupeUser);
					em.getTransaction().begin();	
					em.persist(sousGroupeUser);					
					em.getTransaction().commit();	// Attention !!! les id's ne sont générées qu'après le commit de l'instance persistante associée !
					long idSousGroupe = sousGroupeUser.getID();
					eleve.setSousGroupeEleveID(idSousGroupe);
				}
				if (sousGrp != null){
					long idSousGroupe = sousGrp.getID();
					eleve.setSousGroupeEleveID(idSousGroupe);
				}
				
				long eleveID = eleve.getID();
				String numero = eleve.getNumero();
				String nom = eleve.getNom();
				String prenom = eleve.getPrenom();
				String mail = eleve.getEmail();
				Long sousgroupe = eleve.getSousGroupeEleveID();
				Long groupe = eleve.getGroupeID();
				Long promotion = eleve.getPromotionID();
				em.getTransaction().begin();	//
				em.persist(eleve);					//
				em.getTransaction().commit();	// Attention !!! les id's ne sont générées qu'après le commit de l'instance persistante associée !
				System.out.println("Eleve ID "+eleveID+" : numero "+numero+", nom "+nom+", prenom "+prenom+", mail "+mail+", promo "+promotion+", groupe "+groupe+", sousgroupe "+sousgroupe);

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

