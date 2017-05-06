<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*,gestiondesgrillesapp.model.*"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
 
<html>
    <head>
        <title>JPA Guest Book Web Application Tutorial</title>
    </head>
 
    <body>
        <form method="POST" action="ObjectDbTestServlet">
            Content : <input type="text" name="content" />
            <input type="submit" value="Add" />
        </form>
 
        <hr><ol> <%
            @SuppressWarnings("unchecked")
            List<SousPoint> spList = (List<SousPoint>) request.getAttribute("spList");
        	if(spList != null){
	            for (SousPoint sp : spList) { %>
	                <li> <%= sp.getContenu()%> : <%= sp.getID()%> </li> <%
	            }
        	}%>
        </ol><hr>
 
        <!-- <iframe src="http://www.objectdb.com/pw.html?web-eclipse"
            frameborder="0" scrolling="no" width="100%" height="30"> </iframe> -->
     </body>
 </html>