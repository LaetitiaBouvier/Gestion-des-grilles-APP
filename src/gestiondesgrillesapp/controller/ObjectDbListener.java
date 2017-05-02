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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/gestiondesgrillesapp.odb");
        e.getServletContext().setAttribute("emf", emf);
    }
 
    // Release the EntityManagerFactory:
	@Override
    public void contextDestroyed(ServletContextEvent e) {
        EntityManagerFactory emf = (EntityManagerFactory)e.getServletContext().getAttribute("emf");
        emf.close();
    }
}