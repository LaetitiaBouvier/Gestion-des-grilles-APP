package gestiondesgrillesapp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gestiondesgrillesapp.model.Competence;
import gestiondesgrillesapp.model.Grille;
import gestiondesgrillesapp.model.User;

/**
 * Servlet implementation class NavBarServlet
 */
@WebServlet("/NavBarServlet")
public class NavBarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NavBarServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO : Dans la navBar il faudrait régler le problème d'affichage des sous compétences dans les compétences associées
		
		request.setCharacterEncoding("UTF-8");
		
		String onglet = (String) request.getParameter("submitbutton");
		
		if(onglet.equals("Vue d'ensemble"))
		{
			request.getRequestDispatcher("/View/jsp/VueDEnsemble.jsp").forward(request, response);
		}
		else
		{
			String competenceSelectedTitle = onglet;
			System.out.println(competenceSelectedTitle);
			
			Competence competenceSelected = null;
			HttpSession sess = request.getSession(false);
			
			User user = (User) sess.getAttribute("user");
			HashMap<String, Grille> grillesMembres = (HashMap<String, Grille>) sess.getAttribute("grillesMembres");
			Grille grille = (Grille) sess.getAttribute("grilleUtilisateur");
			
			HashMap<Grille, ArrayList<Competence>> competencesList = (HashMap<Grille, ArrayList<Competence>>) sess.getAttribute("competences");
			System.out.println(competencesList.size());
			
			for(Competence competence : competencesList.get(grille))
			{
				if(competence.getTitre().equals(competenceSelectedTitle))
				{
					competenceSelected = competence;
				}
			}
			
			if(competenceSelected == null)
			{
				throw new RuntimeException("The competence selected cannot be null !");
			}
			
			sess.removeAttribute("competenceSelected");
			sess.setAttribute("competenceSelected", competenceSelected);
			request.getRequestDispatcher("/View/jsp/DetailCompetenceJS.jsp").forward(request, response);
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
