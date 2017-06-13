package gestiondesgrillesapp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader (request.getInputStream()));
		String str = br.readLine();
		System.out.println(str);
		
		String[] strs = str.split("=");
		String code = strs[0];
		System.out.println(code);
		String content = "";
		for(int i = 1; i < strs.length; i++)
		{
			content+=strs[i];
		}
		
		content = parse(content);
		System.out.println(content);
		
//		JSONParser parser = new JSONParser();
//		try {
//			// Get object from string
//			Object object = parser.parse(str);
//			
//			// Convert Object to JSONObject
//			JSONObject jsonObject = (JSONObject)object;
//			
//			System.out.println(jsonObject.values().size());
//			
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
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
		str.replaceAll("\\+", 		" "); // ==> cf. https://stackoverflow.com/questions/16217627/string-split-at-a-meta-character#16217659
		str.replaceAll("%C2%B2",	"²");
		str.replaceAll("%26", 		"&");
		str.replaceAll("%C3%A9", 	"é");
		str.replaceAll("%22", 		"\"");
		str.replaceAll("%23", 		"#");
		str.replaceAll("%7B", 		"{");
		str.replaceAll("%5B", 		"[");
		str.replaceAll("%7C", 		"|");
		str.replaceAll("%C3%A8", 	"è");
		str.replaceAll("%60", 		"`");
		str.replaceAll("%5C", 		"\\");
		str.replaceAll("%C3%A7", 	"ç");
		str.replaceAll("%5E", 		"^");
		str.replaceAll("%C3%A0", 	"à");
		str.replaceAll("%40", 		"@");
		str.replaceAll("%C2%B0", 	"°");
		str.replaceAll("%5D", 		"]");
		str.replaceAll("%2B", 		"+");
		str.replaceAll("%7D", 		"}");
		str.replaceAll("%C2%A8", 	"¨");
		str.replaceAll("%5E", 		"^");
		str.replaceAll("%24", 		"$");
		str.replaceAll("%C2%A3", 	"£");
		str.replaceAll("%C2%A4", 	"¤");
		str.replaceAll("%C2%B5", 	"µ");
		str.replaceAll("%C3%B9", 	"ù");
		str.replaceAll("%C2%A7", 	"§");
		str.replaceAll("%3A", 		":");
		str.replaceAll("%2F", 		"/");
		str.replaceAll("%3B", 		";");
		str.replaceAll("%2C", 		",");
		str.replaceAll("%3F", 		"?");
		str.replaceAll("%3C", 		"<");
		str.replaceAll("%3E", 		">");
		
		str.replaceAll("%3D", 		"=");
		str.replaceAll("%25", 		"%");
		//² 		&	é		"	#	{	[	|	è		`	\	ç		^	à		@	°		]	=	+	}	¨		^	$	£		¤		µ		ù		%	§		:	/	;	,	?	<	>
		//%C2%B2	%26	%C3%A9	%22	%23	%7B	%5B	%7C	%C3%A8	%60	%5C	%C3%A7	%5E	%C3%A0	%40	%C2%B0	%5D	%3D	%2B	%7D	%C2%A8	%5E	%24	%C2%A3	%C2%A4	%C2%B5	%C3%B9	%25	%C2%A7	%3A	%2F	%3B	%2C	%3F	%3C	%3E
		
		return str;
	}

}
