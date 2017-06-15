package gestiondesgrillesapp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gestiondesgrillesapp.model.SousCompetence;
import gestiondesgrillesapp.model.SousPoint;
import gestiondesgrillesapp.model.User;

/**
 * Servlet implementation class DetailCompetenceJSServlet
 */
@WebServlet("/DetailCompetenceJSServlet")
public class DetailCompetenceJSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DetailCompetenceJSServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// Obtain a database connection:
		EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();
		
		HttpSession sess = request.getSession(false);

		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader (request.getInputStream()));
			String str = br.readLine();
			
			String[] strs = str.split("=");
			String code = strs[0];
			String content = "";
			for(int i = 1; i < strs.length; i++)
			{
				content+=strs[i];
			}
			content = parse(content);


			if(code.contains("Commentaire"))
			{
				String[] codes = code.split("_");
				String codeCommentaire = codes[0];
				String codeObjet = parse(codes[1]);

				if(codeCommentaire.equals("CommentaireTuteurTuteurSousCompetence"))
				{
					ArrayList<User> membresSousGroupe = (ArrayList<User>) sess.getAttribute("membresSousGroupe");
					HashMap<String, SousCompetence> sousCompetencesHashContent = (HashMap<String, SousCompetence>) sess.getAttribute("sousCompetencesHashContent");
					
					for(User userMembre : membresSousGroupe)
					{
						// Ici codeObjet correspond au contenu des sous-compétences dont on veut modifier les commentaires tuteur-tuteur
						SousCompetence sousCompetenceSelected = sousCompetencesHashContent.get(userMembre.getID()+""+codeObjet);
						sousCompetenceSelected = em.find(SousCompetence.class, sousCompetenceSelected.getID());
						
						// MAJ BDD
						// cf. http://www.objectdb.com/java/jpa/persistence/update
						em.getTransaction().begin();
						sousCompetenceSelected.setCommentaireTuteurTuteur(content);
						em.getTransaction().commit();
						
						// MAJ Session première étape
						LoginServlet.fillSession(em, sess, (User) sess.getAttribute("userFill"));
					}
				}
				
				if(codeCommentaire.equals("CommentaireEquipeSousCompetence"))
				{
					ArrayList<User> membresSousGroupe = (ArrayList<User>) sess.getAttribute("membresSousGroupe");
					HashMap<String, SousCompetence> sousCompetencesHashContent = (HashMap<String, SousCompetence>) sess.getAttribute("sousCompetencesHashContent");
					
					for(User userMembre : membresSousGroupe)
					{
						// Ici codeObjet correspond au contenu des sous-compétences dont on veut modifier les commentaires d'équipes
						SousCompetence sousCompetenceSelected = sousCompetencesHashContent.get(userMembre.getID()+""+codeObjet);
						sousCompetenceSelected = em.find(SousCompetence.class, sousCompetenceSelected.getID());
						
						// MAJ BDD
						// cf. http://www.objectdb.com/java/jpa/persistence/update
						em.getTransaction().begin();
						sousCompetenceSelected.setCommentaireEquipe(content);
						em.getTransaction().commit();
						
						// MAJ Session première étape
						LoginServlet.fillSession(em, sess, (User) sess.getAttribute("userFill"));
					}
				}
				
				if(codeCommentaire.equals("CommentaireIndividuelSousCompetence"))
				{
					SousCompetence sousCompetenceSelected = em.find(SousCompetence.class, Long.parseLong(codeObjet)); // Ici le code objet est directement l'id de la sous competence select
					
					// MAJ BDD
					// cf. http://www.objectdb.com/java/jpa/persistence/update
					em.getTransaction().begin();
					sousCompetenceSelected.setCommentaireIndividuel(content);
					em.getTransaction().commit();
					
					// MAJ Session
					LoginServlet.fillSession(em, sess, (User) sess.getAttribute("userFill"));
				}
				
				if(codeCommentaire.equals("CommentaireIndividuelSousPoint"))
				{
					SousPoint sousPointSelected = em.find(SousPoint.class, Long.parseLong(codeObjet)); // Ici le code objet est directement l'id du sous point selectionné
					
					// MAJ BDD
					// cf. http://www.objectdb.com/java/jpa/persistence/update
					em.getTransaction().begin();
					sousPointSelected.setCommentaireIndividuel(content);
					em.getTransaction().commit();
					
					// MAJ Session
					LoginServlet.fillSession(em, sess, (User) sess.getAttribute("userFill"));
				}
			}
			else
			{
				String[] codes = code.split("_");
				String codeNiveau = codes[0];
				String codeObjet = parse(codes[1]);
				
				System.out.println("==>"+codeNiveau+" : "+codeObjet+"<==");
				
				if(codeNiveau.equals("NiveauSousCompetence"))
				{
					SousCompetence sousCompetenceSelected = em.find(SousCompetence.class, Long.parseLong(codeObjet)); // Ici le code objet est directement l'id de la sous competence selectionné
					
					// MAJ BDD
					// cf. http://www.objectdb.com/java/jpa/persistence/update
					em.getTransaction().begin();
					sousCompetenceSelected.setNiveau(content);
					em.getTransaction().commit();
					
					// MAJ Session
					LoginServlet.fillSession(em, sess, (User) sess.getAttribute("userFill"));
				}
				
				if(codeNiveau.equals("NiveauSousPoint"))
				{
					SousPoint sousPointSelected = em.find(SousPoint.class, Long.parseLong(codeObjet)); // Ici le code objet est directement l'id du sous point selectionné
					
					// MAJ BDD
					// cf. http://www.objectdb.com/java/jpa/persistence/update
					em.getTransaction().begin();
					sousPointSelected.setNiveau(content);
					em.getTransaction().commit();
					
					// MAJ Session
					LoginServlet.fillSession(em, sess, (User) sess.getAttribute("userFill"));
				}
			}
		}
		finally
		{
			// Close the database connection:
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			em.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	//² 		&	é		"	#	{	[	|	è		`	\	ç		^	à		@	°		]	=	+	}	¨		^	$	£		¤		µ		ù		%	§		:	/	;	,	?	<	>
	//%C2%B2	%26	%C3%A9	%22	%23	%7B	%5B	%7C	%C3%A8	%60	%5C	%C3%A7	%5E	%C3%A0	%40	%C2%B0	%5D	%3D	%2B	%7D	%C2%A8	%5E	%24	%C2%A3	%C2%A4	%C2%B5	%C3%B9	%25	%C2%A7	%3A	%2F	%3B	%2C	%3F	%3C	%3E
	private String parse(String str)
	{
		str = str.replaceAll(Pattern.quote("+"), 		" ");
		str = str.replaceAll(Pattern.quote("%C2%B2"),	"²");
		str = str.replaceAll(Pattern.quote("%26"), 		"&");
		str = str.replaceAll(Pattern.quote("%C3%A9"), 	"é");
		str = str.replaceAll(Pattern.quote("%22"), 		"\"");
		str = str.replaceAll(Pattern.quote("%23"), 		"#");
		str = str.replaceAll(Pattern.quote("%7B"), 		"{");
		str = str.replaceAll(Pattern.quote("%5B"), 		"[");
		str = str.replaceAll(Pattern.quote("%7C"), 		"|");
		str = str.replaceAll(Pattern.quote("%C3%A8"), 	"è");
		str = str.replaceAll(Pattern.quote("%60"), 		"`");
		str = str.replaceAll(Pattern.quote("%5C"), 		"\\");
		str = str.replaceAll(Pattern.quote("%C3%A7"), 	"ç");
		str = str.replaceAll(Pattern.quote("%5E"), 		"^");
		str = str.replaceAll(Pattern.quote("%C3%A0"), 	"à");
		str = str.replaceAll(Pattern.quote("%40"), 		"@");
		str = str.replaceAll(Pattern.quote("%C2%B0"), 	"°");
		str = str.replaceAll(Pattern.quote("%5D"), 		"]");
		str = str.replaceAll(Pattern.quote("%2B"), 		"+");
		str = str.replaceAll(Pattern.quote("%7D"), 		"}");
		str = str.replaceAll(Pattern.quote("%C2%A8"), 	"¨");
		str = str.replaceAll(Pattern.quote("%5E"), 		"^");
		str = str.replaceAll(Pattern.quote("%24"), 		"$");
		str = str.replaceAll(Pattern.quote("%C2%A3"), 	"£");
		str = str.replaceAll(Pattern.quote("%C2%A4"), 	"¤");
		str = str.replaceAll(Pattern.quote("%C2%B5"), 	"µ");
		str = str.replaceAll(Pattern.quote("%C3%B9"), 	"ù");
		str = str.replaceAll(Pattern.quote("%C2%A7"), 	"§");
		str = str.replaceAll(Pattern.quote("%3A"), 		":");
		str = str.replaceAll(Pattern.quote("%2F"), 		"/");
		str = str.replaceAll(Pattern.quote("%3B"), 		";");
		str = str.replaceAll(Pattern.quote("%2C"), 		",");
		str = str.replaceAll(Pattern.quote("%3F"), 		"?");
		str = str.replaceAll(Pattern.quote("%3C"), 		"<");
		str = str.replaceAll(Pattern.quote("%3E"), 		">");

		str = str.replaceAll(Pattern.quote("%3D"), 		"=");
		str = str.replaceAll(Pattern.quote("%25"), 		"%");
		//² 		&	é		"	#	{	[	|	è		`	\	ç		^	à		@	°		]	=	+	}	¨		^	$	£		¤		µ		ù		%	§		:	/	;	,	?	<	>
		//%C2%B2	%26	%C3%A9	%22	%23	%7B	%5B	%7C	%C3%A8	%60	%5C	%C3%A7	%5E	%C3%A0	%40	%C2%B0	%5D	%3D	%2B	%7D	%C2%A8	%5E	%24	%C2%A3	%C2%A4	%C2%B5	%C3%B9	%25	%C2%A7	%3A	%2F	%3B	%2C	%3F	%3C	%3E

		return str;
	}

}
