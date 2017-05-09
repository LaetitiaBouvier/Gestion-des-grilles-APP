package gestiondesgrillesapp.controller;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ObjectDbListener implements ServletContextListener {

	// Prepare the EntityManagerFactory & Enhance:
	@Override
    public void contextInitialized(ServletContextEvent e) {
        com.objectdb.Enhancer.enhance("gestiondesgrillesapp.model.Commentaire");  // Ici on indique où se trouvent les classes du model "mappées" avec @Entity
        com.objectdb.Enhancer.enhance("gestiondesgrillesapp.model.SousPoint");
        com.objectdb.Enhancer.enhance("gestiondesgrillesapp.model.Point");
        com.objectdb.Enhancer.enhance("gestiondesgrillesapp.model.SousCompetence");
        com.objectdb.Enhancer.enhance("gestiondesgrillesapp.model.Competence");
        com.objectdb.Enhancer.enhance("gestiondesgrillesapp.model.Grille");
        com.objectdb.Enhancer.enhance("gestiondesgrillesapp.model.SousGroupe");
        com.objectdb.Enhancer.enhance("gestiondesgrillesapp.model.Groupe");
        com.objectdb.Enhancer.enhance("gestiondesgrillesapp.model.Promotion");
        com.objectdb.Enhancer.enhance("gestiondesgrillesapp.model.User");
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/gestiondesgrillesapp.odb");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("/BDD/gestiondesgrillesapp.odb");
        e.getServletContext().setAttribute("emf", emf);
    }
 
    // Release the EntityManagerFactory:
	@Override
    public void contextDestroyed(ServletContextEvent e) {
        EntityManagerFactory emf = (EntityManagerFactory)e.getServletContext().getAttribute("emf");
        emf.close();
    }
}