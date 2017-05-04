package gestiondesgrillesapp.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServlet;

import gestiondesgrillesapp.model.Competence;
import gestiondesgrillesapp.model.Eleve;
import gestiondesgrillesapp.model.Grille;
import gestiondesgrillesapp.model.Groupe;
import gestiondesgrillesapp.model.Point;
import gestiondesgrillesapp.model.SousCompetence;
import gestiondesgrillesapp.model.SousGroupe;
import gestiondesgrillesapp.model.SousPoint;
import gestiondesgrillesapp.model.Tuteur;

public class ObjectDBUtilServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ObjectDBUtilServlet() {
		super();
	}
	
	public void initializeDataBase(){
		
		emptyDataBase();

		fillUsers();
	}
	
	public void emptyDataBase(){

		// Obtain a database connection:
		EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();

		try{
			em.createQuery("DELETE FROM Object").executeUpdate(); // cf http://www.objectdb.com/java/jpa/query/jpql/delete 
			//=> this query can be used to delete all the objects in the database

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
					Tuteur tuteurJB = new Tuteur("Bond", "James", "007", "james.bond@jesuisunespion.com", "MI6", true);

					Eleve eleveST = new Eleve("Stéphane", "Tzvetkov", "9482", "stephane.tzvetkov@isep.fr");
					Eleve elevePPC = new Eleve("Pierre-Philippe", "Cordier", "9551", "pierre-philippe.corder@isep.fr");
					Eleve eleveND = new Eleve("Nicolas", "Dubes", "9502", "nicolas.dubes@isep.fr");

					Eleve elebeLB = new Eleve("Laëtitia", "Bouvier", "9555", "laetitia.bouvier@isep.fr");
					Eleve elebeCB = new Eleve("Camille", "Duboue", "9648", "camille.duboue@isep.fr");
					
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

					sousGroupeGarçons.addEleveID(eleveST.getID());
					eleveST.setSousGroupeID(sousGroupeGarçons.getID());

					sousGroupeGarçons.addEleveID(elevePPC.getID());
					elevePPC.setSousGroupeID(sousGroupeGarçons.getID());

					sousGroupeGarçons.addEleveID(eleveND.getID());
					eleveND.setSousGroupeID(sousGroupeGarçons.getID());

					sousGroupeFilles.addEleveID(elebeLB.getID());
					elebeLB.setSousGroupeID(sousGroupeFilles.getID());

					sousGroupeFilles.addEleveID(elebeCB.getID());
					elebeCB.setSousGroupeID(sousGroupeFilles.getID());

					groupeLogiciel.addSousGroupeID(sousGroupeGarçons.getID());
					sousGroupeGarçons.setGroupeID(groupeLogiciel.getID());

					groupeLogiciel.addTuteurID(tuteurJB.getID());
					tuteurJB.addGroupeID(groupeLogiciel.getID());

					groupeSI.addSousGroupeID(sousGroupeFilles.getID());
					sousGroupeFilles.setGroupeID(groupeSI.getID());

					groupeSI.addTuteurID(tuteurJB.getID());
					tuteurJB.addGroupeID(groupeSI.getID());
					
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