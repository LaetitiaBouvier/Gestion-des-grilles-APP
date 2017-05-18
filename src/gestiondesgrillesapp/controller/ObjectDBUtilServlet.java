package gestiondesgrillesapp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestiondesgrillesapp.model.Competence;
import gestiondesgrillesapp.model.Grille;
import gestiondesgrillesapp.model.Groupe;
import gestiondesgrillesapp.model.Point;
import gestiondesgrillesapp.model.SousCompetence;
import gestiondesgrillesapp.model.SousGroupe;
import gestiondesgrillesapp.model.SousPoint;
import gestiondesgrillesapp.model.User;

@WebServlet("/ObjectDBUtilServlet")
public class ObjectDBUtilServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ObjectDBUtilServlet() {
		super();
	}
	
	/*
	 * TODO : Très important pour la suite : voilà comment gérer les informations des sessions, ce qui sera très pratique :
	 * - https://javaweb.developpez.com/faq/javaee?page=Session
	 * - http://stackoverflow.com/questions/20540191/how-to-call-a-method-in-a-servlet-from-another-class#20540506
	 * - côté jsp ? : http://java.mesexemples.com/java-server-page/jsp-exemple-des-sessions-en-jsp/
	 * 
	 * TODO : Et pour appeler une servlet depuis une autre servlet (en l'occurence cette servlet depuis une autre), si besoin :
	 * - http://stackoverflow.com/questions/20947806/how-can-i-call-from-one-servlet-file-to-another-servlet-file#20947869
	 * - http://tutorials.jenkov.com/java-servlets/requestdispatcher.html
	 * - https://www.javatpoint.com/requestdispatcher-in-servlet
	 */
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String code = (String) request.getAttribute("code");
		System.out.println("ok : code : "+code);
		
		if(("emptyDataBase").equals(code)){
			emptyDataBase();
		}
		else if(("initializeDataBase").equals(code)){
			System.out.println("start");
			initializeDataBase();
			System.out.println("end");
		}
		else if(("associateStudentToGrid").equals(code)){
			
			if(request.getAttribute("eleveID") != null && request.getAttribute("grilleModelID") != null){
				
				long eleveID = (long) request.getAttribute("eleveID");
				long grilleModelID = (long) request.getAttribute("grilleModelID");
				
				associate(eleveID, grilleModelID);
			}else{
				throw new IllegalArgumentException("Impossible d'associer un élève à une grille sans leur ID respectifs !");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void associate(long eleveID, long grilleModelID){
		
		// Obtain a database connection:
				EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
				EntityManager em = emf.createEntityManager();

				try{
					
					List<Grille> grilleModelList = em.createQuery("SELECT c FROM Grille c WHERE id="+grilleModelID, Grille.class).getResultList();

					if(grilleModelList == null || grilleModelList.size() == 0){
						throw new IllegalArgumentException("La grille "+grilleModelID+" n'existe pas !");
					}
					else if(grilleModelList.size() > 1){
						throw new IllegalArgumentException("Plusieurs grilles semblent partager le même ID : "+grilleModelID+", c'est la merde !");
					} 
					
					List<User> eleveList = em.createQuery("SELECT c FROM User c WHERE id="+eleveID, User.class).getResultList();
					
					if(eleveList == null || eleveList.size() == 0){
						throw new IllegalArgumentException("L'élève "+eleveID+" n'existe pas !");
					}
					else if(eleveList.size() > 1){
						throw new IllegalArgumentException("Plusieurs élèves semblent partager le même ID : "+eleveID+", c'est la merde !");
					} 

					Grille grilleModel = grilleModelList.get(0);
					User eleve = eleveList.get(0);
					
					associateStudentToGrid(em, eleve, grilleModel);

				} finally {
					// Close the database connection:
					if (em.getTransaction().isActive())
						em.getTransaction().rollback();
					em.close();
				}
	}
	
	public void associateStudentToGrid(EntityManager em, User eleve, Grille grilleModel){

		Grille grilleCopy = grilleModelDeepCopy(grilleModel);

		if(grilleCopy == null){
			throw new RuntimeException("Une erreur est survenue au moment de copier la grille \"model\" pour associer la copie à l'élève !");
		}

		eleve.setGrilleEleveID(grilleCopy.getID());

		em.getTransaction().begin();
		em.persist(eleve);
		em.getTransaction().commit();	// Ici on MAJ seulement "eleve"

	}
	
	public Grille grilleModelDeepCopy(Grille grilleModel){

		// Obtain a database connection:
		EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();
		
		Grille grilleModelCopy = null;

		try{
			grilleModelCopy = grilleModel.deepCopy();
			
			em.getTransaction().begin();
			em.persist(grilleModelCopy);
			em.getTransaction().commit();	// Attention !!! les id's ne sont générées qu'après le commit de l'instance persistante associée !
			
			ArrayList<Competence> competencesListCopy = new ArrayList<>();
			for(long competenceID : grilleModel.getCompetencesIDs()){
				List<Competence> competenceTempList = em.createQuery("SELECT c FROM Competence c WHERE id="+competenceID, Competence.class).getResultList();
				
				if(competenceTempList == null || competenceTempList.size() == 0){
					throw new IllegalArgumentException("L'ID : "+competenceID+" de cette compétence n'existe pas !");
				}
				else if(competenceTempList.size() > 1){
					throw new IllegalArgumentException("Plusieurs compétences semblent partager le même ID : "+competenceID+", c'est la merde !");
				}
				
				Competence competence = competenceTempList.get(0);
				Competence competenceCopy = competenceTempList.get(0).deepCopy();
				competenceCopy.setGrilleID(grilleModelCopy.getID());
				
				em.getTransaction().begin();
				em.persist(competenceCopy);
				em.getTransaction().commit();	// Attention !!! les id's ne sont générées qu'après le commit de l'instance persistante associée !
				
				competencesListCopy.add(competenceCopy);
				
				ArrayList<SousCompetence> sousCompetencesListCopy = new ArrayList<>();
				for(long sousCompetenceID : competence.getSousCompetencesIDs()){
					List<SousCompetence> sousCompetenceTempList = em.createQuery("SELECT c FROM SousCompetence c WHERE id="+sousCompetenceID, SousCompetence.class).getResultList();
					
					if(sousCompetenceTempList == null || sousCompetenceTempList.size() == 0){
						throw new IllegalArgumentException("L'ID : "+sousCompetenceID+" de cette sous-compétence n'existe pas !");
					}
					else if(sousCompetenceTempList.size() > 1){
						throw new IllegalArgumentException("Plusieurs sous-compétences semblent partager le même ID : "+sousCompetenceID+", c'est la merde !");
					}
					
					SousCompetence sousCompetence = sousCompetenceTempList.get(0);
					SousCompetence sousCompetenceCopy = sousCompetenceTempList.get(0).deepCopy();
					sousCompetenceCopy.setCompetenceID(competenceCopy.getID());
					
					em.getTransaction().begin();
					em.persist(sousCompetenceCopy);
					em.getTransaction().commit();	// Attention !!! les id's ne sont générées qu'après le commit de l'instance persistante associée !
					
					sousCompetencesListCopy.add(sousCompetenceCopy);
					
					ArrayList<Point> pointsListCopy = new ArrayList<>();
					for(long pointID : sousCompetence.getPointsIDs()){
						List<Point> pointsTempList = em.createQuery("SELECT c FROM Point c WHERE id="+pointID, Point.class).getResultList();
						
						if(pointsTempList == null || pointsTempList.size() == 0){
							throw new IllegalArgumentException("L'ID : "+pointID+" de ce point n'existe pas !");
						}
						else if(pointsTempList.size() > 1){
							throw new IllegalArgumentException("Plusieurs points semblent partager le même ID : "+pointID+", c'est la merde !");
						}
						
						Point point = pointsTempList.get(0);
						Point pointCopy = pointsTempList.get(0).deepCopy();
						pointCopy.setSousCompetenceID(sousCompetenceCopy.getID());
						
						em.getTransaction().begin();
						em.persist(pointCopy);
						em.getTransaction().commit();	// Attention !!! les id's ne sont générées qu'après le commit de l'instance persistante associée !
						
						pointsListCopy.add(pointCopy);
						
						ArrayList<SousPoint> sousPointsListCopy = new ArrayList<>();
						for(long sousPointID : point.getSousPointsIDs()){
							List<SousPoint> sousPointsTempList = em.createQuery("SELECT c FROM SousPoint c WHERE id="+sousPointID, SousPoint.class).getResultList();
							
							if(sousPointsTempList == null || sousPointsTempList.size() == 0){
								throw new IllegalArgumentException("L'ID : "+sousPointID+" de ce sous-point n'existe pas !");
							}
							else if(sousPointsTempList.size() > 1){
								throw new IllegalArgumentException("Plusieurs sous-points semblent partager le même ID : "+sousPointID+", c'est la merde !");
							}
							
							SousPoint sousPointCopy = sousPointsTempList.get(0).deepCopy();
							sousPointCopy.setPointID(pointCopy.getID());
							
							em.getTransaction().begin();
							em.persist(sousPointCopy);
							em.getTransaction().commit();	// Attention !!! les id's ne sont générées qu'après le commit de l'instance persistante associée !
							
							sousPointsListCopy.add(sousPointCopy);
						}
						for(SousPoint sousPointCopy : sousPointsListCopy){
							pointCopy.addSousPointID(sousPointCopy.getID());
						}
						em.getTransaction().begin();
						em.persist(pointCopy);
						em.getTransaction().commit();	// Ici on MAJ seulement "pointCopy"
					}
					for(Point pointCopy : pointsListCopy){
						sousCompetenceCopy.addPointID(pointCopy.getID());
					}
					em.getTransaction().begin();
					em.persist(sousCompetenceCopy);
					em.getTransaction().commit();	// Ici on MAJ seulement "sousCompetenceCopy"
				}
				for(SousCompetence sousCompetenceCopy : sousCompetencesListCopy){
					competenceCopy.addSousCompetenceID(sousCompetenceCopy.getID());
				}
				em.getTransaction().begin();
				em.persist(competenceCopy);
				em.getTransaction().commit();	// Ici on MAJ seulement "competenceCopy"
			}
			for(Competence competenceCopy : competencesListCopy){
				grilleModelCopy.addCompetenceID(competenceCopy.getID());
			}
			em.getTransaction().begin();
			em.persist(grilleModelCopy);
			em.getTransaction().commit();	// Ici on MAJ seulement "grilleModelCopy"
			
		} finally {
			// Close the database connection:
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			em.close();
		}
		
		return grilleModelCopy;
	}
	
	public void initializeDataBase(){
		
		emptyDataBase();
		
		createGrilleModel();

		fillUsers();
	}
	
	public void emptyDataBase(){

		// Obtain a database connection:
		EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();

		try{
			em.getTransaction().begin();
			em.createQuery("DELETE FROM Object").executeUpdate();
//			em.createQuery("DELETE FROM Commentaire AS c").executeUpdate(); // cf http://www.objectdb.com/java/jpa/query/jpql/delete
//			em.createQuery("DELETE FROM SousPoint AS c").executeUpdate();
//			em.createQuery("DELETE FROM Point AS c").executeUpdate();
//			em.createQuery("DELETE FROM SousCompetence AS c").executeUpdate();
//			em.createQuery("DELETE FROM Competence AS c").executeUpdate();
//			em.createQuery("DELETE FROM Grille AS c").executeUpdate();
//			em.createQuery("DELETE FROM Eleve AS c").executeUpdate();
//			em.createQuery("DELETE FROM SousGroupe AS c").executeUpdate();
//			em.createQuery("DELETE FROM Groupe AS c").executeUpdate();
//			em.createQuery("DELETE FROM Promotion AS c").executeUpdate();
//			em.createQuery("DELETE FROM Tuteur AS c").executeUpdate();
			em.getTransaction().commit();
			
		} finally {
			// Close the database connection:
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			em.close();
		}
	}
	
	public void fillUsers(){

		// Obtain a database connection:
		EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();

		try{

			User tuteurJB = new User("Bond", "James", "007", "james.bond@jesuisunespion.com", true);
			tuteurJB.setBureauTuteur("MI6");
			tuteurJB.setRespoModule(true);

			User eleveST = new User("Stéphane", "Tzvetkov", "9482", "stephane.tzvetkov@isep.fr", false);
			User elevePPC = new User("Pierre-Philippe", "Cordier", "9551", "pierre-philippe.corder@isep.fr", false);
			User eleveND = new User("Nicolas", "Dubes", "9502", "nicolas.dubes@isep.fr", false);

			User elebeLB = new User("Laëtitia", "Bouvier", "9555", "laetitia.bouvier@isep.fr", false);
			User elebeCB = new User("Camille", "Duboue", "9648", "camille.duboue@isep.fr", false);

			SousGroupe sousGroupeGarçons = new SousGroupe("Garçons");
			SousGroupe sousGroupeFilles = new SousGroupe("Filles");
			Groupe groupeLogiciel = new Groupe("Groupe Logiciel");
			Groupe groupeSI = new Groupe("GroupeSI");

			//  ____________________________________
			//  Enregistrement des objets dans la BDD :
			em.getTransaction().begin();
			em.persist(tuteurJB);
			em.persist(eleveST);
			em.persist(elevePPC);
			em.persist(eleveND);
			em.persist(elebeLB);
			em.persist(elebeCB);

			em.persist(sousGroupeGarçons);
			em.persist(sousGroupeFilles);
			em.persist(groupeLogiciel);
			em.persist(groupeSI);
			em.getTransaction().commit();	// Attention !!! les id's ne sont générées qu'après le commit de l'instance persistante associée !
			//  ____________________________________
			//  Associations entres les élèves, tuteurs et (sous-)groupes

			sousGroupeGarçons.addEleveID(eleveST.getID());
			eleveST.setSousGroupeEleveID(sousGroupeGarçons.getID());

			sousGroupeGarçons.addEleveID(elevePPC.getID());
			elevePPC.setSousGroupeEleveID(sousGroupeGarçons.getID());

			sousGroupeGarçons.addEleveID(eleveND.getID());
			eleveND.setSousGroupeEleveID(sousGroupeGarçons.getID());

			sousGroupeFilles.addEleveID(elebeLB.getID());
			elebeLB.setSousGroupeEleveID(sousGroupeFilles.getID());

			sousGroupeFilles.addEleveID(elebeCB.getID());
			elebeCB.setSousGroupeEleveID(sousGroupeFilles.getID());

			groupeLogiciel.addSousGroupeID(sousGroupeGarçons.getID());
			sousGroupeGarçons.setGroupeID(groupeLogiciel.getID());

			groupeLogiciel.addTuteurID(tuteurJB.getID());
			tuteurJB.addGroupeTuteurID(groupeLogiciel.getID());

			groupeSI.addSousGroupeID(sousGroupeFilles.getID());
			sousGroupeFilles.setGroupeID(groupeSI.getID());

			groupeSI.addTuteurID(tuteurJB.getID());
			tuteurJB.addGroupeTuteurID(groupeSI.getID());

			//  ____________________________________
			//  Enregistrement de la MAJ des objets dans la BDD :
			em.getTransaction().begin();
			em.persist(tuteurJB);
			em.persist(eleveST);
			em.persist(elevePPC);
			em.persist(eleveND);
			em.persist(elebeLB);
			em.persist(elebeCB);

			em.persist(sousGroupeGarçons);
			em.persist(sousGroupeFilles);
			em.persist(groupeLogiciel);
			em.persist(groupeSI);
			em.getTransaction().commit();	// Attention !!! les id's ne sont générées qu'après le commit de l'instance persistante associée !
			//  ____________________________________
			//  Associer les élèves à une grille "model" :

			List<Grille> grilleModelList = em.createQuery("SELECT c FROM Grille c WHERE isModel=TRUE AND titre='GrilleModelTest'", Grille.class).getResultList();

			if(grilleModelList == null || grilleModelList.size() == 0){
				throw new IllegalArgumentException("La grille \"GrilleModelTest\" n'existe pas !");
			}
			else if(grilleModelList.size() > 1){
				throw new IllegalArgumentException("Plusieurs grilles semblent partager l'ID de \"GrilleModelTest\" : "+grilleModelList.get(0).getID()+", c'est la merde !");
			} 

			Grille grilleModel = grilleModelList.get(0);

			associateStudentToGrid(em, eleveST, grilleModel);
			associateStudentToGrid(em, elevePPC, grilleModel);
			associateStudentToGrid(em, eleveND, grilleModel);
			associateStudentToGrid(em, elebeLB, grilleModel);
			associateStudentToGrid(em, elebeCB, grilleModel);

		} finally {
			// Close the database connection:
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			em.close();
		}
	}
	
	public void createGrilleModel(){

		// Obtain a database connection:
		EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();

		try{
			
			Grille grilleModel = new Grille("GrilleModelTest", true); // "true" car c'est une grille "model"
			
			//  ____________________________________
			//  ____________________________________
			//  ____________________________________
			//  Création de la compétence communication :
				Competence competenceCommunication = new Competence("Communication", "1-Agir en bon communicant dans un environnement scientifique et technique");
			
					SousCompetence sousCompetenceEchanger = new SousCompetence("Echanger");
					
						Point pointEcouter = new Point("Ecouter et se faire écouter");
							SousPoint sousPtEcouter1 = new SousPoint("Etre disposé à l'écoute et être capable d'exposer son point de vue");
							SousPoint sousPtEcouter2 = new SousPoint("Admettre  que l'autre peut avoir raison et maintenir l'intérêt de son auditoire");
							
						Point pointDialoguer = new Point("Dialoguer, argumenter et convaincre");
							SousPoint sousPtDialoguer1 = new SousPoint("Savoir réunir les conditions d'un dialogue et l'engager");
							SousPoint sousPtDialoguer2 = new SousPoint("Avancer des arguments convaincants qui font évoluer les positions des interlocuteurs");
							
					SousCompetence sousCompetenceCommuniquerOral = new SousCompetence("Communiquer à l'oral");
					
						Point pointCommuniquerOral = new Point("Savoir communiquer à l'oral");
							SousPoint sousPtCommuniquerOral1 = new SousPoint("Savoir communiquer à l'oral, de façon claire et structurée, en français ou en anglais, sur un sujet technique");
						
						Point pointAnalyserOral = new Point("Analyser et synthétiser à l'oral");
							SousPoint sousPtAnalyserOral1 = new SousPoint("Analyser et synthétiser, à l'oral, ses idées scientifiques de façon pertinente tout en s'adaptant à son public");
							
					SousCompetence sousCompetenceCommuniquerEcrit = new SousCompetence("Communiquer à l'écrit");
					
						Point pointCommuniquerEcrit = new Point("Savoir communiquer à l'écrit");
							SousPoint sousPtCommuniquer1 = new SousPoint("Savoir communiquer à l'écrit, de façon claire et structurée, en français ou en anglais, sur un sujet technique");
							
						Point pointAnalyserEcrit = new Point("Analyser et synthétiser à l'écrit");
							SousPoint sousPtAnalyserEcrit1 = new SousPoint("Analyser et synthétiser, à l'écrit, ses idées scientifiques de façon pertinente tout en s'adaptant à son public");
			//  ____________________________________
			//  Enregistrement des objets dans la BDD :
							
			em.getTransaction().begin();
			
			em.persist(grilleModel);
			
			em.persist(competenceCommunication);
				em.persist(sousCompetenceEchanger);
					em.persist(pointEcouter);
						em.persist(sousPtEcouter1);
						em.persist(sousPtEcouter2);
					em.persist(pointDialoguer);
						em.persist(sousPtDialoguer1);
						em.persist(sousPtDialoguer2);
				em.persist(sousCompetenceCommuniquerOral);
					em.persist(pointCommuniquerOral);
						em.persist(sousPtCommuniquerOral1);
					em.persist(pointAnalyserOral);
						em.persist(sousPtAnalyserOral1);
				em.persist(sousCompetenceCommuniquerEcrit);
					em.persist(pointCommuniquerEcrit);
						em.persist(sousPtCommuniquer1);
					em.persist(pointAnalyserEcrit);
						em.persist(sousPtAnalyserEcrit1);
					
			em.getTransaction().commit();								// Attention !!! les id's ne sont générées qu'après le commit de l'instance persistante associée !
			//  ____________________________________
			//  Association de la compétence communication à la grille :
							
							sousPtEcouter1.setPointID(pointEcouter.getID());
							sousPtEcouter2.setPointID(pointEcouter.getID());
						pointEcouter.addSousPointID(sousPtEcouter1.getID());
						pointEcouter.addSousPointID(sousPtEcouter2.getID());
						pointEcouter.setSousCompetenceID(sousCompetenceEchanger.getID());
							
							sousPtDialoguer1.setPointID(pointDialoguer.getID());
							sousPtDialoguer2.setPointID(pointDialoguer.getID());
						pointDialoguer.addSousPointID(sousPtDialoguer1.getID());
						pointDialoguer.addSousPointID(sousPtDialoguer2.getID());
						pointDialoguer.setSousCompetenceID(sousCompetenceEchanger.getID());
							
					sousCompetenceEchanger.addPointID(pointEcouter.getID());
					sousCompetenceEchanger.addPointID(pointDialoguer.getID());
					sousCompetenceEchanger.setCompetenceID(competenceCommunication.getID());
							
							sousPtCommuniquerOral1.setPointID(pointCommuniquerOral.getID());
						pointCommuniquerOral.addSousPointID(sousPtCommuniquerOral1.getID());
						pointCommuniquerOral.setSousCompetenceID(sousCompetenceCommuniquerOral.getID());
							
							sousPtAnalyserOral1.setPointID(pointAnalyserOral.getID());
						pointAnalyserOral.addSousPointID(sousPtAnalyserOral1.getID());
						pointAnalyserOral.setSousCompetenceID(sousCompetenceCommuniquerOral.getID());
							
					sousCompetenceCommuniquerOral.addPointID(pointCommuniquerOral.getID());
					sousCompetenceCommuniquerOral.addPointID(pointAnalyserOral.getID());
					sousCompetenceCommuniquerOral.setCompetenceID(competenceCommunication.getID());
							
							sousPtCommuniquer1.setPointID(pointCommuniquerEcrit.getID());
						pointCommuniquerEcrit.addSousPointID(sousPtCommuniquer1.getID());
						pointCommuniquerEcrit.setSousCompetenceID(sousCompetenceCommuniquerEcrit.getID());
							
							sousPtAnalyserEcrit1.setPointID(pointAnalyserEcrit.getID());
						pointAnalyserEcrit.addSousPointID(sousPtAnalyserEcrit1.getID());
						pointAnalyserEcrit.setSousCompetenceID(sousCompetenceCommuniquerEcrit.getID());
						
					sousCompetenceCommuniquerEcrit.addPointID(pointCommuniquerEcrit.getID());
					sousCompetenceCommuniquerEcrit.addPointID(pointAnalyserEcrit.getID());
					sousCompetenceCommuniquerEcrit.setCompetenceID(competenceCommunication.getID());
							
				competenceCommunication.addSousCompetenceID(sousCompetenceEchanger.getID());
				competenceCommunication.addSousCompetenceID(sousCompetenceCommuniquerOral.getID());
				competenceCommunication.addSousCompetenceID(sousCompetenceCommuniquerEcrit.getID());
				competenceCommunication.setGrilleID(grilleModel.getEleveID());
				
			grilleModel.addCompetenceID(competenceCommunication.getID());
			//  ____________________________________
			//  Enregistrement de la MAJ des objets dans la BDD :
									
			em.getTransaction().begin();
					
			em.persist(grilleModel);
					
			em.persist(competenceCommunication);
				em.persist(sousCompetenceEchanger);
					em.persist(pointEcouter);
						em.persist(sousPtEcouter1);
						em.persist(sousPtEcouter2);
					em.persist(pointDialoguer);
						em.persist(sousPtDialoguer1);
						em.persist(sousPtDialoguer2);
				em.persist(sousCompetenceCommuniquerOral);
					em.persist(pointCommuniquerOral);
						em.persist(sousPtCommuniquerOral1);
					em.persist(pointAnalyserOral);
						em.persist(sousPtAnalyserOral1);
				em.persist(sousCompetenceCommuniquerEcrit);
					em.persist(pointCommuniquerEcrit);
						em.persist(sousPtCommuniquer1);
					em.persist(pointAnalyserEcrit);
						em.persist(sousPtAnalyserEcrit1);
					
			em.getTransaction().commit();
			
			//  ____________________________________
			//  ____________________________________
			//  ____________________________________
			//  Création de la compétence travail en équipe :
			
				Competence competenceTravailEnEquipe = new Competence("Travail en équipe", "2-Agir en acteur dynamique et efficace dans une équipe");
				
					SousCompetence sousCompetenceEquipe = new SousCompetence("Travailler en équipe");
					
						Point pointParticiper = new Point("Participer à la vie de l'équipe");
							SousPoint sousPtParticiper1 = new SousPoint("Se montrer ouvert, collaboratif et participatif");
							SousPoint sousPtParticiper2 = new SousPoint("Agir avec coordination et entre-aide");
						
						Point pointAnimer = new Point("Animer une équipe et la motiver");
							SousPoint sousPtAnimer1 = new SousPoint("Animer en maintenant la cohésion de l'équipe et un minimum d'intérêt");
							SousPoint sousPtAnimer2 = new SousPoint("Motiver les membres de l'équipe");
			
					SousCompetence sousCompetenceConflits = new SousCompetence("Gérer les conflits, la diversité et les différences");
					
						Point pointDetecter = new Point("Détecter les conflits");
							SousPoint sousPtDetecter1 = new SousPoint("Détecter les conflits et accepter la diversité et les différences");
							
						Point pointApporter = new Point("Apporter des solutions");
							SousPoint sousPtApporter1 = new SousPoint("Apporter des solutions aux conflits et s'ouvrir aux différences ");
							
					SousCompetence sousCompetenceProposition = new SousCompetence("Être force de proposition");
					
						Point pointEmettre = new Point("Emettre une idée pertinente");
							SousPoint sousPtEmettre1 = new SousPoint("Emettre une idée pertinente");
						
						Point pointJustifier = new Point("Justifier et défendre une idée");
							SousPoint sousPtJustifier1 = new SousPoint("Justifier et défendre une idée");
							
					SousCompetence sousCompetenceOutilsCollaboratif = new SousCompetence("Utiliser des outils de travail collaboratif");
					
						Point pointUtiliser = new Point("Utiliser des outils collaboratifs");
							SousPoint sousPtUtiliser1 = new SousPoint("Utiliser des outils de stockage et de partage de documents (Google drive, dropbox, ...)");
							
						Point pointOrganiser = new Point("Organiser et versionner");
							SousPoint sousPtOrganiser1 = new SousPoint("Organiser et versionner les dossiers et les fichiers de façon claire et structurée dans les espaces de stockage");
				//  ____________________________________
				//  Enregistrement des objets dans la BDD :
								
				em.getTransaction().begin();
				
				em.persist(competenceTravailEnEquipe);
					em.persist(sousCompetenceEquipe);
						em.persist(pointParticiper);
							em.persist(sousPtParticiper1);
							em.persist(sousPtParticiper2);
						em.persist(pointAnimer);
							em.persist(sousPtAnimer1);
							em.persist(sousPtAnimer2);
					em.persist(sousCompetenceConflits);
						em.persist(pointDetecter);
							em.persist(sousPtDetecter1);
						em.persist(pointApporter);
							em.persist(sousPtApporter1);
					em.persist(sousCompetenceProposition);
						em.persist(pointEmettre);
							em.persist(sousPtEmettre1);
						em.persist(pointJustifier);
							em.persist(sousPtJustifier1);
					em.persist(sousCompetenceOutilsCollaboratif);
						em.persist(pointUtiliser);
							em.persist(sousPtUtiliser1);
						em.persist(pointOrganiser);
							em.persist(sousPtOrganiser1);
						
				em.getTransaction().commit();								// Attention !!! les id's ne sont générées qu'après le commit de l'instance persistante associée !
				//  ____________________________________
				//  Association de la compétence travail en équipe à la grille :
							
							sousPtParticiper1.setPointID(pointParticiper.getID());
							sousPtParticiper2.setPointID(pointParticiper.getID());
						pointParticiper.addSousPointID(sousPtParticiper1.getID());
						pointParticiper.addSousPointID(sousPtParticiper2.getID());
						pointParticiper.setSousCompetenceID(sousCompetenceEquipe.getID());
						
							sousPtAnimer1.setPointID(pointAnimer.getID());
							sousPtAnimer2.setPointID(pointAnimer.getID());
						pointAnimer.addSousPointID(sousPtAnimer1.getID());
						pointAnimer.addSousPointID(sousPtAnimer2.getID());
						pointAnimer.setSousCompetenceID(sousCompetenceEquipe.getID());
						
					sousCompetenceEquipe.addPointID(pointParticiper.getID());
					sousCompetenceEquipe.addPointID(pointAnimer.getID());
					sousCompetenceEquipe.setCompetenceID(competenceTravailEnEquipe.getID());
						
							sousPtDetecter1.setPointID(pointDetecter.getID());
						pointDetecter.addSousPointID(sousPtDetecter1.getID());
						pointDetecter.setSousCompetenceID(sousCompetenceConflits.getID());
						
							sousPtApporter1.setPointID(pointApporter.getID());
						pointApporter.addSousPointID(sousPtApporter1.getID());
						pointApporter.setSousCompetenceID(sousCompetenceConflits.getID());
						
					sousCompetenceConflits.addPointID(pointDetecter.getID());
					sousCompetenceConflits.addPointID(pointApporter.getID());
					sousCompetenceConflits.setCompetenceID(competenceTravailEnEquipe.getID());
					
							sousPtEmettre1.setPointID(pointEmettre.getID());
						pointEmettre.addSousPointID(sousPtEmettre1.getID());
						pointEmettre.setSousCompetenceID(sousCompetenceProposition.getID());
						
							sousPtJustifier1.setPointID(pointJustifier.getID());
						pointJustifier.addSousPointID(sousPtJustifier1.getID());
						pointJustifier.setSousCompetenceID(sousCompetenceProposition.getID());
						
					sousCompetenceProposition.addPointID(pointEmettre.getID());
					sousCompetenceProposition.addPointID(pointJustifier.getID());
					sousCompetenceProposition.setCompetenceID(competenceTravailEnEquipe.getID());
					
							sousPtUtiliser1.setPointID(pointUtiliser.getID());
						pointUtiliser.addSousPointID(sousPtUtiliser1.getID());
						pointUtiliser.setSousCompetenceID(sousCompetenceOutilsCollaboratif.getID());
							
							sousPtOrganiser1.setPointID(pointOrganiser.getID());
						pointOrganiser.addSousPointID(sousPtOrganiser1.getID());
						pointOrganiser.setSousCompetenceID(sousCompetenceOutilsCollaboratif.getID());
						
					sousCompetenceOutilsCollaboratif.addPointID(pointUtiliser.getID());
					sousCompetenceOutilsCollaboratif.addPointID(pointOrganiser.getID());
					sousCompetenceOutilsCollaboratif.setCompetenceID(competenceTravailEnEquipe.getID());
					
				competenceTravailEnEquipe.addSousCompetenceID(sousCompetenceEquipe.getID());
				competenceTravailEnEquipe.addSousCompetenceID(sousCompetenceConflits.getID());
				competenceTravailEnEquipe.addSousCompetenceID(sousCompetenceProposition.getID());
				competenceTravailEnEquipe.addSousCompetenceID(sousCompetenceOutilsCollaboratif.getID());
				competenceTravailEnEquipe.setGrilleID(grilleModel.getEleveID());
				
			grilleModel.addCompetenceID(competenceTravailEnEquipe.getID());
			
			//  ____________________________________
			//  Enregistrement de la MAJ des objets dans la BDD :
							
			em.getTransaction().begin();
			
			em.persist(grilleModel);
			
			em.persist(competenceTravailEnEquipe);
				em.persist(sousCompetenceEquipe);
					em.persist(pointParticiper);
						em.persist(sousPtParticiper1);
						em.persist(sousPtParticiper2);
					em.persist(pointAnimer);
						em.persist(sousPtAnimer1);
						em.persist(sousPtAnimer2);
				em.persist(sousCompetenceConflits);
					em.persist(pointDetecter);
						em.persist(sousPtDetecter1);
					em.persist(pointApporter);
						em.persist(sousPtApporter1);
				em.persist(sousCompetenceProposition);
					em.persist(pointEmettre);
						em.persist(sousPtEmettre1);
					em.persist(pointJustifier);
						em.persist(sousPtJustifier1);
				em.persist(sousCompetenceOutilsCollaboratif);
					em.persist(pointUtiliser);
						em.persist(sousPtUtiliser1);
					em.persist(pointOrganiser);
						em.persist(sousPtOrganiser1);
					
			em.getTransaction().commit();								// Attention !!! les id's ne sont générées qu'après le commit de l'instance persistante associée !
			
		} finally {
			// Close the database connection:
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			em.close();
		}
	}
}