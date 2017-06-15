package gestiondesgrillesapp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gestiondesgrillesapp.model.Groupe;
import gestiondesgrillesapp.model.User;

/**
 * Servlet implementation class Navigation
 */
@WebServlet("/NavigationServlet")
public class NavigationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NavigationServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Obtain a database connection:
		EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();

		HttpSession sess = request.getSession(false);

		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader (request.getInputStream()));
//			String content = parse(br.readLine());
			String content = br.readLine();
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ==> id model : "+content);
			
			long modelID  = Long.parseLong(content.split(Pattern.quote("+"))[0]);
			long groupeID = Long.parseLong(content.split(Pattern.quote("+"))[1]);
			
			HashMap<Groupe, ArrayList<User>> hashUsersGroupe = (HashMap<Groupe, ArrayList<User>>) sess.getAttribute("hashUsersGroupe");
			
			List<Groupe> groupeListTemp = em.createQuery("SELECT c FROM Groupe c WHERE id="+groupeID, Groupe.class).getResultList();
			Groupe groupe = (Groupe) ObjectDBUtilServlet.extractOnlyOneObjectManagingExceptions(groupeListTemp);
			
			ArrayList<User> groupeUsers = hashUsersGroupe.get(groupe);
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