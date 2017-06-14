<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file = "./../html/headCommun.html" %>
<body>

<%@ include file = "NavBar.jsp" %>

<%
	HashMap<String, SousCompetence> sousCompetencesHashContent 	= (HashMap<String, SousCompetence>) sess.getAttribute("sousCompetencesHashContent");
	HashMap<String, SousPoint> 		sousPointsHashContent 		= (HashMap<String, SousPoint>) 		sess.getAttribute("sousPointsHashContent");

	HashMap<SousCompetence	, ArrayList<Point>> 	points 		= (HashMap<SousCompetence	, ArrayList<Point>>) 		sess.getAttribute("points");
	HashMap<Point			, ArrayList<SousPoint>> sousPoints 	= (HashMap<Point			, ArrayList<SousPoint>>) 	sess.getAttribute("sousPoints");
	
	ArrayList<User> membresSousGroupe = (ArrayList<User>) sess.getAttribute("membresSousGroupe");
	
	competenceSelected = (Competence) sess.getAttribute("competenceSelected");

	System.out.println("\nDetailCompetence : competenceSelected = "+competenceSelected.getTitre());
	
	for(SousCompetence sousCompetence : sousCompetences.get(competenceSelected))
	{
			System.out.println(sousCompetence.getContenu()+" :"+sousCompetence.getCommentaireIndividuel());
	}
%>

	<!-- content of the page -->
	<div class="col-lg-10 col-sm-10 col-sm-12 col-xs-12">
		<!-- titre détaillé de la compétance -->
		<div class="row">
			<h3 id="idCompetence<%=competenceSelected.getID()%>"> <%=competenceSelected.getTitre() %> - <%=competenceSelected.getDescription() %></h3>
		</div>
		<!-- premiere partie du tableau -->
		
		<%
			for(SousCompetence sousCompetence : sousCompetences.get(competenceSelected))
			{
		%>
		
		<div class="row">
			<!-- colone sous competence -->
			<div class="col-lg-3 col-md-3 col-sm-6 col-xs-6">
				<h4 class="myBoxSizeTitle" id="<%=competenceSelected.getID()%>-<%=sousCompetence.getID()%>"><%=sousCompetence.getContenu()%></h4>
				<!-- Nom de la sous competence || le 74vh c'est nbEleve*15 - 1 pour que le bouton soit aligner a adapter-->
				
				<!--Attention au nb d'élèves ! 83-1 pour 5 élèves   -->
				<div class="text-center mySousCompetence" style="height: <%=16.4*membresSousGroupe.size()%>vh;">
				
					<h2 class="myVerticalCenter"><%=sousCompetence.getContenu()%></h2>
				</div>
				<!-- Dropdown bouton a adapter l'id-->
				<div>
					<a data-toggle="collapse" href="#CollapseSousCompetence<%=sousCompetence.getID()%>"><span
						class="fa fa-angle-double-down fa-2x pull-right fa-inverse myDropDownButton"></span></a>
				</div>
				<!-- commentaire tuteur a tuteur -->
				<form class="jsCallText" method="POST" action="DetailCompetenceJSServlet">
					<div>
						<textarea class="form-control myBoxSize myTuTu" placeholder="commentaire tuteur a tuteur" name="CommentaireTuteurTuteurSousCompetence_<%=sousCompetence.getContenu()%>"><%=sousCompetence.getCommentaireTuteurTuteur()%></textarea>
						<button type="submit" class="btn btn-primary mySaveButton fa fa-floppy-o"></button>	
					</div>
				</form>
			</div>
			<!-- colone observation sur l'équipe -->
			<div class="col-lg-2 col-md-2 col-sm-6 col-xs-6">
				<h4 class="myBoxSizeTitle">Observation sur l'équipe</h4>
				<!-- textarea permettant l'observation global du groupe -->
				<div>
					<!-- 75vh a adapter en fonction du nb d'eleve -->
					<form class="jsCallText" method="POST" action="DetailCompetenceJSServlet">
						<div>
<!--Attention au nb d'élèves ! 84-1 pour 5 élèves   -->
							<textarea class="form-control " placeholder="write here the evaluation of the team" name="CommentaireEquipeSousCompetence_<%=sousCompetence.getContenu()%>" style="height:<%=16.6*membresSousGroupe.size()%>vh;"><%=sousCompetence.getCommentaireEquipe()%></textarea>
							<button type="submit" class="btn btn-primary mySaveButton fa fa-floppy-o"></button>	
						</div>
					</form>
				</div>
			</div>
			<!-- observation individuel niveau individuel et etudiant sont regroupé -> sur les petits écran ils sont les 3 ensemble -->
			<!-- ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -->
			<div class="col-lg-7 col-md-7 col-sm-12 col-xs-12">
				<!-- observation individuel -->
				<div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
					<h4 class="myBoxSizeTitle">Observation individuelles</h4>
					<!-- il faut créer un nb de textarea depedant du nb d'eleve -->
					<%
					for(User userMembre : membresSousGroupe)
					{
						SousCompetence sousCompetenceSelected = sousCompetencesHashContent.get(userMembre.getID()+""+sousCompetence.getContenu());
						if(sousCompetenceSelected == null) throw new RuntimeException("La sous compétence sélectionnée ne doit pas être null !");
					%>
					<form class="jsCallText myBoxSizePlus" method="POST" action="DetailCompetenceJSServlet">
						<div>
							<textarea 	class="form-control myBoxSize"
										<%-- <%if(sousCompetenceSelected.getCommentaireIndividuel().equals("")){%> placeholder="write here the evaluation of Eleve" <%}%> --%>
										placeholder="write here the evaluation of Eleve"
										name="CommentaireIndividuelSousCompetence_<%=sousCompetenceSelected.getID()%>"><%=sousCompetenceSelected.getCommentaireIndividuel()%></textarea>
							<button type="submit" class="btn btn-primary mySaveButton fa fa-floppy-o"></button>	
						</div>
					</form>
					<%
					}
					%>
				</div>
				<!-- niveau individuel -->
				<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 ">
					<h4 class="myBoxSizeTitle">Niveau individuel</h4>
					<%
					for(User userMembre : membresSousGroupe)
					{
						SousCompetence sousCompetenceSelected = sousCompetencesHashContent.get(userMembre.getID()+""+sousCompetence.getContenu());
						if(sousCompetenceSelected == null) throw new RuntimeException("La sous compétence sélectionnée ne doit pas être null !");
					%>
					<form class="jsCallText myBoxSizePlus" method="POST" action="DetailCompetenceJSServlet">
						<div class="btn-group bootstrap-select open col-xs-12" style="margin-left:-2.1vh;">
							<select class="selectpicker selectwidthauto" name="NiveauSousCompetence_<%=sousCompetenceSelected.getID()%>">
								<option value="Non assigné"	<% if(sousCompetenceSelected.getNiveau().equals("Non assigné"))	{ %> <%=("selected")%> <% } %>>Non assigné</option>
								<option value="Au dela"		<% if(sousCompetenceSelected.getNiveau().equals("Au dela"))		{ %> <%=("selected")%> <% } %>>Au dela</option>
								<option value="Attendu"		<% if(sousCompetenceSelected.getNiveau().equals("Attendu"))		{ %> <%=("selected")%> <% } %>>Attendu</option>
								<option value="Très proche"	<% if(sousCompetenceSelected.getNiveau().equals("Très proche"))	{ %> <%=("selected")%> <% } %>>Très proche</option>
								<option value="Proche"		<% if(sousCompetenceSelected.getNiveau().equals("Proche"))		{ %> <%=("selected")%> <% } %>>Proche</option>
								<option value="Loin"		<% if(sousCompetenceSelected.getNiveau().equals("Loin"))		{ %> <%=("selected")%> <% } %>>Loin</option>
							</select>	
						</div>
						<div style="position:relative;">
							<button type="submit" class="btn btn-primary fa fa-floppy-o"></button>
						</div>	
					</form>
					<%
					}
					%>
				</div>
				<!-- Etudiant -->
				<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
				<!-- titre de la collone -->
					<h4 class="myBoxSizeTitle">Etudiants</h4>
					<%
					for(User userMembre : membresSousGroupe)
					{
					%>
					<div class="btn-group bootstrap-select open col-lg-12 myBoxSizePhoto ">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 myVerticalCenter">
							<img src="https://s-media-cache-ak0.pinimg.com/736x/cd/90/d9/cd90d9de63fa2c8e5c5e7117e27b5c18.jpg" class="img-responsive" alt="photo identité">
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 myVerticalCenter">
							<ul>
								<li><%= userMembre.getNom() %></li>
								<li><%= userMembre.getPrenom() %></li>
							</ul>
						</div>
					</div>
					<%
					}
					%>
				  </div>
				<!-- end of the groupe observation individuelles niveau et etudiant -->
				<!-- ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -->
			</div>
			<!-- end of the 5 collones -->
		</div>

		<!-- start of the drop down button -->
		<div class="panel-group">
		<div class="panel panel-default">
		<!-- a adapter l'id -->
		<div id="CollapseSousCompetence<%=sousCompetence.getID()%>" class="panel-collapse collapse">
			
			<!-- ce div ne sert que de marge -->
			<div class="col-lg-3 col-md-3 col-sm-1 col-xs-1"></div>
			
			<!-- la structure est EXACTEMENT la même que celle de sous competances -->
			<div class="panel panel-default col-lg-9 col-md-9 col-sm-11 col-xs-11">
				
			<%
			for(Point point : points.get(sousCompetence))
			{
			%>
				<!-- titre du 1 point -->
				<div class="panel-heading"><%= point.getTitre() %><br/></div>
				
				<%
				for(SousPoint sousPoint : sousPoints.get(point))
				{
				%>
				<!-- description du sous point -->
				<div class=" panel-body row">
					<!-- colone sous competence -->
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<!-- chaque collone a un titre de la taille  myBoxSizeTitle-->
						<h4 class="myBoxSizeTitle"></h4>
						<!-- description du sous point -->
						<h4 class="text-center"><%= sousPoint.getContenu() %></h4>
					</div>
					<!-- observation individuel niveau individuel et etudiant sont regroupé -> sur les petits écran ils sont les 3 ensemble -->
					<!-- ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -->
					<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
						<!-- observation individuel -->
						<div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
							<h4 class="myBoxSizeTitle">Observation individuelles</h4>
							<!-- il faut créer un nb de textarea depedant du nb d'eleve -->
							<%
							for(User userMembre : membresSousGroupe)
							{
								SousPoint sousPointSelected = sousPointsHashContent.get(userMembre.getID()+""+sousPoint.getContenu());
								if(sousPointSelected == null) throw new RuntimeException("Le sous point sélectionnée ne doit pas être null !");
							%>
							<form class="jsCallText myBoxSizeDDPlus" method="POST" action="DetailCompetenceJSServlet">
								<div>
									<textarea class="form-control myBoxSizeDD" placeholder="write here the evaluation of Eleve" name="CommentaireIndividuelSousPoint_<%=sousPointSelected.getID()%>"><%=sousPointSelected.getCommentaireIndividuel()%></textarea>
									<button type="submit" class="btn btn-primary mySaveButton fa fa-floppy-o"></button>	
								</div>
							</form>
							<%
							}
							%>
						</div>
						<!-- niveau individuel -->
						<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 ">
							<h4 class="myBoxSizeTitle">Niveau individuel</h4>
							<%
							for(User userMembre : membresSousGroupe)
							{
								SousPoint sousPointSelected = sousPointsHashContent.get(userMembre.getID()+""+sousPoint.getContenu());
								if(sousPointSelected == null) throw new RuntimeException("Le sous point sélectionnée ne doit pas être null !");
							%>
							<form class="jsCallText myBoxSizeDDPlus" method="POST" action="DetailCompetenceJSServlet">
								<div class="btn-group bootstrap-select open col-xs-12" style="margin-left:-2.1vh;">
									<select class="selectpicker selectwidthauto" name="NiveauSousPoint_<%=sousPointSelected.getID()%>">
										<option value="Non assigné"	<% if(sousPointSelected.getNiveau().equals("Non assigné"))	{ %> <%=("selected")%> <% } %>>Non assigné</option>
										<option value="Acquis"		<% if(sousPointSelected.getNiveau().equals("Acquis")) 		{ %> <%=("selected")%> <% } %>>Acquis</option>
										<option value="Non acquis"	<% if(sousPointSelected.getNiveau().equals("Non acquis")) 	{ %> <%=("selected")%> <% } %>>Non acquis</option>
									</select>
								</div>
								<div style="position:relative;">
									<button type="submit" class="btn btn-primary fa fa-floppy-o"></button>
								</div>
							</form>
							<%
							}
							%>
						</div>
						<!-- Etudiant -->
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
							<h4 class="myBoxSizeTitle">Etudiants</h4>
							<%
							for(User userMembre : membresSousGroupe)
							{
							%>
							<div class="btn-group bootstrap-select open col-lg-12  myBoxSizeDDPlus">
								<ul class="myVerticalCenter">
									<li><%= userMembre.getNom() %></li>
									<li><%= userMembre.getPrenom() %></li>
								</ul>
							</div>
							<%
							}
							%>
						</div>
					<!-- end of the individuel/etudiant pack -->
					</div>
				<!-- ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -->
				<!-- end of the sous-point -->
				</div>
				
				<!-- separateur -->
				<div class="mySeparateur"></div>
				<%
				}
				%>
			<%
			}
			%>
			</div>
		</div>
	</div>
	<!-- end of the drop down button -->
	<%
	}
	%>
	</div>
</div>

	<script>
		console.log($('form.jsCallText'));
		$('form.jsCallText').submit(function(e){
			e.preventDefault();
			$.ajax({
				method : 'POST',
				url : 'DetailCompetenceJSServlet',
				data : $(this).serializeArray(),
				dataType : 'JSON',
				success : function() {
					console.log('success');
				},
				error : function() {
				}
			});
		})
	</script>

</body>
</html>