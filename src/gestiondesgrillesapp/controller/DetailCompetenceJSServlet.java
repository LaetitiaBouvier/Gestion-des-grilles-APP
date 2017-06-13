package gestiondesgrillesapp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		request.setCharacterEncoding("UTF-8");
		BufferedReader br = new BufferedReader(new InputStreamReader (request.getInputStream()));
		String str = br.readLine();
		System.out.println(str);
		
		byte[] ptext = str.getBytes("ISO_8859_1"); 
		String value = new String(ptext, "UTF-8"); 
		
		System.out.println(value);
		
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

}
