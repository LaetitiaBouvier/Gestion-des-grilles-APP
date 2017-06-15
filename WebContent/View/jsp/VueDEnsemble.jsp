<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file = "./../html/headCommun.html" %>
<body>

<%@ include file = "NavBar.jsp" %>

<%
	HashMap<String, SousCompetence> sousCompetencesHashContent 	= (HashMap<String, SousCompetence>) sess.getAttribute("sousCompetencesHashContent");

	ArrayList<User> membresSousGroupe = (ArrayList<User>) sess.getAttribute("membresSousGroupe");
	
	/* tuteursList */
	ArrayList<User> tuteursList = (ArrayList<User>) sess.getAttribute("tuteursList");
	Groupe groupe 			= (Groupe) 		sess.getAttribute("groupe");
	SousGroupe sousGroupe 	= (SousGroupe) 	sess.getAttribute("sousGroupe");
%>

	<div class="container col-lg-10 col-sm-10 col-sm-12 col-xs-12">
		<div class="row">
			<div class="col-xs-12">
				<h1 class="title">Sous-Groupe <%=sousGroupe.getNom()%></h1>
			</div>
		</div>
		<div class="row">
		
		<% if(membresSousGroupe.size() < 6) {%> <a class="col-md-1"></a> <%} %>
		<%for(User userMembre : membresSousGroupe) { %>
		
		<a class="col-xs-4 col-md-2 picture">
			<img class="col-xs-12" src="https://s-media-cache-ak0.pinimg.com/736x/cd/90/d9/cd90d9de63fa2c8e5c5e7117e27b5c18.jpg" alt="<%=userMembre.getPrenom()%> <%=userMembre.getNom() %>">
			<span class="col-xs-12"><%=userMembre.getPrenom()%> <%=userMembre.getNom() %></span>
		</a>
		
		<%} %>
		</div>
		
		<div style="height:10vh;"></div>
		
		<div>
			<table class="table table-striped">
				
				<thead>
					<tr>
						<th>COMPETENCES & sous-compétences</th>
						<th>coef</th>
						
						<%for(User userMembre : membresSousGroupe) { %>
						
							<th><%=userMembre.getPrenom()%> <%=userMembre.getNom() %></th>
						<%} %>
					</tr>
				</thead>
				<tbody>
				
					<%for(Competence competence : competencesList) {%>
					
					<tr class="table-info">
						<td><%=competence.getTitre().toUpperCase()%></td>
						<td><%=competence.getCoefficient() %></td>
						
						<%for(User userMembre : membresSousGroupe) {%>
							<td> </td>
						<%} %>
					</tr>
					
						<%
						for(SousCompetence sscmp : sousCompetences.get(competence)){%>
						
						<tr>
							<td>        <%=sscmp.getContenu().toLowerCase()%></td>
							<td>        <%=sscmp.getCoefficient() %></td>
							
							<%for(User userMembre : membresSousGroupe) {
								SousCompetence sscmpSlct = sousCompetencesHashContent.get(userMembre.getID()+""+sscmp.getContenu());
							%>
							<td><%=sscmpSlct.getNiveau()%> </td>
							<%} %>
						</tr>
						
						<%} %>
					<%} %>
				</tbody>
			</table>
		</div>

		<!-- 		<div id="spacer" class="row"></div> -->

		<div id="separated" class="row">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Tuteur</th>
						<th>Contact</th>
						<th>Bureau</th>
					</tr>
				</thead>
				<tbody>
				
				<%for(User tuteur : tuteursList){ %>

					<tr>
						<td><%=tuteur.getPrenom()%> <%=tuteur.getNom()%></td>
						<td><a><%=tuteur.getEmail() %></a></td>
						<td><%=tuteur.getBureauTuteur()%></td>
					</tr>

				<%} %>
				</tbody>
			</table>
		</div>
	</div>
	
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<script src="https://code.highcharts.com/highcharts.js"></script>
	<script src="https://code.highcharts.com/highcharts-more.js"></script>
	<script src="https://code.highcharts.com/modules/exporting.js"></script>
	
	<div id="container" class="col-lg-10 col-sm-10 col-sm-12 col-xs-12 col-lg-offset-2 col-sm-offset-2 col-sm-offset-2 col-xs-offset-2">
		<div id="chart" class="chart" ></div>
	</div>
	
	<%
	// Calcul des scores :
		
		HashMap<User, ArrayList<Double>> scores = new HashMap<>();
	
		for(User userMembre : membresSousGroupe) {
			
			Grille grilleMembre = grillesMembres.get(userMembre.getNumero());
			ArrayList<Competence> competencesUserMembre = competences.get(grilleMembre);
			
			ArrayList<Double> scoresComp = new ArrayList<>();
			
			for(Competence comp : competencesUserMembre){
				ArrayList<SousCompetence> sousCompetencesUserMembre = sousCompetences.get(comp);
				
				double sommeDesCoeff = 0;
				double sommeDesScores = 0.;
				
				for(SousCompetence sousComp : sousCompetencesUserMembre)
				{
					double scoreNiveau = 0.;
					String niveau = sousComp.getNiveau();
					
					if(niveau.equals("Loin")) 		 scoreNiveau = 5.;
					if(niveau.equals("Proche")) 	 scoreNiveau = 10.;
					if(niveau.equals("Très proche")) scoreNiveau = 15.;
					if(niveau.equals("Attendu"))	 scoreNiveau = 17.5;
					if(niveau.equals("Au dela"))	 scoreNiveau = 20.0;
					
					sommeDesCoeff  += sousComp.getCoefficient();
					sommeDesScores += ( scoreNiveau * sousComp.getCoefficient() );
				}
				
				double compScore = sommeDesScores/sommeDesCoeff;
				double finalCompScore = (compScore-0.)/(20.-0.) * (1.-0.) +0.; // map le score entre 0 et 1
				
				scoresComp.add(finalCompScore);
			}
			
			scores.put(userMembre, scoresComp);
		}
	%>
	
	<script>
$(function () {
    var myChart = Highcharts.chart('chart', {

        chart: {
            polar: true,
            type: 'line'
        },

        title: {
            text: 'Aperçu des compétences',
            x: -80
        },

        pane: {
            size: '80%'
        },

        xAxis: {
            categories: [
            	<% for(int i = 0; i < competencesList.size(); i++) {
            		Competence comp = competencesList.get(i);
            	%>
	            	<% if(i != competencesList.size()-1) {%>
	            		<%=("'"+comp.getTitre()+"', ")%>
					<% } else { %>
						<%=("'"+comp.getTitre()+"'")%>
	            	<%} %>
            	
            	<%} %>
                ],
            tickmarkPlacement: 'on',
            lineWidth: 0
        },

        yAxis: {
            gridLineInterpolation: 'polygon',
            lineWidth: 0,
            min: 0
        },

        tooltip: {
            shared: true,
            pointFormat: '<span style="color:{series.color}">{series.name}: <b>{point.y:,.0f}/100</b><br/>'
        },

        legend: {
            align: 'right',
            verticalAlign: 'top',
            y: 70,
            layout: 'vertical'
        },

        series: [
        	
        <% for(int i = 0; i < membresSousGroupe.size(); i++) {
        	User userMembre = membresSousGroupe.get(i);
        %>
        
	        <% if(i != membresSousGroupe.size()-1) {%>
	        {
	            name: '<%=userMembre.getPrenom()%> <%=userMembre.getNom()%>',
	            data: [
	            	<% for(int j = 0; j < scores.get(userMembre).size(); j++) {
	            		double score = scores.get(userMembre).get(j);
	            	%>
		            	<% if(j != scores.get(userMembre).size()-1) {%>
	            			<%=(score+", ")%>
						<% } else { %>
							<%=(score+"")%>
	            		<%} %>
	            	<%} %>
	            	  ],
	            pointPlacement: 'on'
	        },
	        <%} else { %>
	        {
	            name: '<%=userMembre.getPrenom()%> <%=userMembre.getNom()%>',
	            data: [
	            	<% for(int j = 0; j < scores.get(userMembre).size(); j++) {
	            		double score = scores.get(userMembre).get(j);
	            	%>
		            	<% if(j != scores.get(userMembre).size()-1) {%>
	            			<%=(score+", ")%>
						<% }  else { %>
							<%=(score+"")%>
	            		<%} %>
	            	<%} %>
	            	  ],
	            pointPlacement: 'on'
	        }
    		<%}%>
        <%}%>
        ]
    }); 
});
</script>


	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<!-- 	<script type="text/javascript">
		$(document).ready(function() {
			$("#spacer").css("height", $("#separated").height());
		});
	</script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#separated").css("width", $("#spacer").width());
		});
	</script> -->

</body>
</html>