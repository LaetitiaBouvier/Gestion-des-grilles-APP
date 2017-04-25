package gestiondesgrillesapp.controller;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;

public class ObjectDbListener {

	// Prepare the EntityManagerFactory & Enhance:
    public void contextInitialized(ServletContextEvent e) {
        com.objectdb.Enhancer.enhance("gestiondesgrillesapp.model.*");  // Ici on indique où se trouvent 
        EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("$objectdb/db/gestiondesgrillesapp.odb");
        e.getServletContext().setAttribute("emf", emf);
    }
 
    // Release the EntityManagerFactory:
    public void contextDestroyed(ServletContextEvent e) {
        EntityManagerFactory emf =
            (EntityManagerFactory)e.getServletContext().getAttribute("emf");
        emf.close();
    }
}
