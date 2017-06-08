<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file = "./../html/headCommun.html" %>
<body>

<%@ include file = "NavBar.jsp" %>

<%
	HashMap<SousCompetence, ArrayList<Point>> points = (HashMap<SousCompetence, ArrayList<Point>>) sess.getAttribute("points");
	HashMap<Point, ArrayList<SousPoint>> souspoints = (HashMap<Point, ArrayList<SousPoint>>) sess.getAttribute("souspoints");
	
	competenceSelected = (Competence) sess.getAttribute("competenceSelected");

	System.out.println("\nDetailCompetence : competenceSelected = "+competenceSelected.getTitre());
%>

	<!-- content of the page -->
	<div class="col-lg-10 col-sm-10 col-sm-12 col-xs-12">
		<!-- titre détaillé de la compétance -->
		<div class="row">
			<h3 id="idCompetence1"> <%=competenceSelected.getTitre() %> - <%=competenceSelected.getDescription() %></h3>
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
				<div class="text-center mySousCompetence" style="height: 74vh;">
					<h2 class="myVerticalCenter"><%=sousCompetence.getContenu()%></h2>
				</div>
				<!-- Dropdown bouton a adapter l'id-->
				<div>
					<a data-toggle="collapse" href="#CollapseSousCompetence1"><span
						class="fa fa-angle-double-down fa-2x pull-right fa-inverse myDropDownButton"></span></a>
				</div>
				<!-- commentaire tuteur a tuteur -->
				<textarea class="form-control myBoxSize myTuTu"
					placeholder="commentaire tuteur a tuteur"></textarea>
			</div>
			<!-- colone observation sur l'équipe -->
			<div class="col-lg-2 col-md-2 col-sm-6 col-xs-6">
				<h4 class="myBoxSizeTitle">Observation sur l'équipe</h4>
				<!-- textarea permettant l'observation global du groupe -->
				<div>
					<!-- 75vh a adapter en fonction du nb d'eleve -->
					<textarea class="form-control "
						placeholder="write here the evaluation of the team"
						style="height: 75vh;"></textarea>
				</div>
			</div>
			<!-- observation individuel niveau individuel et etudiant sont regroupé -> sur les petits écran ils sont les 3 ensemble -->
			<!-- ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -->
			<div class="col-lg-7 col-md-7 col-sm-12 col-xs-12">
				<!-- observation individuel -->
				<div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
					<h4 class="myBoxSizeTitle">Observation individuelles</h4>
					<!-- il faut créer un nb de textarea depedant du nb d'eleve -->
					<textarea class="form-control myBoxSize"
						placeholder="write here the evaluation of Eleve"></textarea>
					<textarea class="form-control myBoxSize"
						placeholder="write here the evaluation of Eleve"></textarea>
					<textarea class="form-control myBoxSize"
						placeholder="write here the evaluation of Eleve"></textarea>
					<textarea class="form-control myBoxSize"
						placeholder="write here the evaluation of Eleve"></textarea>
					<textarea class="form-control myBoxSize"
						placeholder="write here the evaluation of Eleve"></textarea>
				</div>
				<!-- niveau individuel -->
				<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 ">
					<h4 class="myBoxSizeTitle">Niveau individuel</h4>
					<div
						class="btn-group bootstrap-select open myBoxSize text-center col-xs-12">
						<select class="selectpicker selectwidthauto myVerticalCenter2">
							<option>Non assigné</option>
							<option>Au dela</option>
							<option>Attendu</option>
							<option>Très proche</option>
							<option>Proche</option>
							<option>Loin</option>
						</select>
					</div>
					<div
						class="btn-group bootstrap-select open myBoxSize text-center col-xs-12">
						<select class="selectpicker selectwidthauto myVerticalCenter2">
							<option>Non assigné</option>
							<option>Au dela</option>
							<option>Attendu</option>
							<option>Très proche</option>
							<option>Proche</option>
							<option>Loin</option>
						</select>
					</div>
					<div
						class="btn-group bootstrap-select open myBoxSize text-center col-xs-12">
						<select class="selectpicker selectwidthauto myVerticalCenter2">
							<option>Non assigné</option>
							<option>Au dela</option>
							<option>Attendu</option>
							<option>Très proche</option>
							<option>Proche</option>
							<option>Loin</option>
						</select>
					</div>
					<div
						class="btn-group bootstrap-select open myBoxSize text-center col-xs-12">
						<select class="selectpicker selectwidthauto myVerticalCenter2">
							<option>Non assigné</option>
							<option>Au dela</option>
							<option>Attendu</option>
							<option>Très proche</option>
							<option>Proche</option>
							<option>Loin</option>
						</select>
					</div>
					<div
						class="btn-group bootstrap-select open myBoxSize text-center col-xs-12">
						<select class="selectpicker selectwidthauto myVerticalCenter2">
							<option>Non assigné</option>
							<option>Au dela</option>
							<option>Attendu</option>
							<option>Très proche</option>
							<option>Proche</option>
							<option>Loin</option>
						</select>
					</div>
				</div>
				<!-- Etudiant -->
				<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
					<!-- titre de la collone -->
					<h4 class="myBoxSizeTitle">Etudiants</h4>
					<div class="btn-group bootstrap-select open col-lg-12 myBoxSize ">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 myVerticalCenter">
							<img
								src="https://s-media-cache-ak0.pinimg.com/736x/cd/90/d9/cd90d9de63fa2c8e5c5e7117e27b5c18.jpg"
								class="img-responsive" alt="photo identité">
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 myVerticalCenter">
							<ul>
								<li>Nom</li>
								<li>Prenom</li>
							</ul>
						</div>
					</div>
					<div class="btn-group bootstrap-select open col-lg-12  myBoxSize">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 myVerticalCenter">
							<img
								src="https://s-media-cache-ak0.pinimg.com/736x/cd/90/d9/cd90d9de63fa2c8e5c5e7117e27b5c18.jpg"
								class="img-responsive" alt="photo identité">
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 myVerticalCenter">
							<ul>
								<li>Nom</li>
								<li>Prenom</li>
							</ul>
						</div>
					</div>
					<div class="btn-group bootstrap-select open col-lg-12  myBoxSize">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 myVerticalCenter">
							<img
								src="https://s-media-cache-ak0.pinimg.com/736x/cd/90/d9/cd90d9de63fa2c8e5c5e7117e27b5c18.jpg"
								class="img-responsive" alt="photo identité">
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 myVerticalCenter">
							<ul>
								<li>Nom</li>
								<li>Prenom</li>
							</ul>
						</div>
					</div>
					<div class="btn-group bootstrap-select open col-lg-12  myBoxSize">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 myVerticalCenter">
							<img
								src="https://s-media-cache-ak0.pinimg.com/736x/cd/90/d9/cd90d9de63fa2c8e5c5e7117e27b5c18.jpg"
								class="img-responsive" alt="photo identité">
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 myVerticalCenter">
							<ul>
								<li>Nom</li>
								<li>Prenom</li>
							</ul>
						</div>
					</div>
					<div class="btn-group bootstrap-select open col-lg-12  myBoxSize">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 myVerticalCenter">
							<img
								src="https://s-media-cache-ak0.pinimg.com/736x/cd/90/d9/cd90d9de63fa2c8e5c5e7117e27b5c18.jpg"
								class="img-responsive" alt="photo identité">
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 myVerticalCenter">
							<ul>
								<li>Nom</li>
								<li>Prenom</li>
							</ul>
						</div>
					</div>
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
				<div id="CollapseSousCompetence1" class="panel-collapse collapse">

					<!-- ce div ne sert que de marge -->
					<div class="col-lg-3 col-md-3 col-sm-1 col-xs-1"></div>

					<!-- la structure est EXACTEMENT la même que celle de sous competances -->
					<div
						class="panel panel-default col-lg-9 col-md-9 col-sm-11 col-xs-11">

						<!-- titre du 1 point -->
						<div class="panel-heading">
							Point 1 <br /> ex: ecouter et se faire ecouter
						</div>

						<!-- description du sous point -->
						<div class=" panel-body row">
							<!-- colone sous competence -->
							<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
								<!-- chaque collone a un titre de la taille  myBoxSizeTitle-->
								<h4 class="myBoxSizeTitle"></h4>
								<!-- description du sous point -->
								<h4 class="text-center">être disponible à l'écoute et être
									capable d'exposé son point de vue</h4>
							</div>
							<!-- observation individuel niveau individuel et etudiant sont regroupé -> sur les petits écran ils sont les 3 ensemble -->
							<!-- ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -->
							<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
								<!-- observation individuel -->
								<div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
									<h4 class="myBoxSizeTitle">Observation individuelles</h4>
									<!-- il faut créer un nb de textarea depedant du nb d'eleve -->
									<textarea class="form-control myBoxSizeDD"
										placeholder="write here the evaluation of Eleve" /></textarea>
									<textarea class="form-control myBoxSizeDD"
										placeholder="write here the evaluation of Eleve" /></textarea>
									<textarea class="form-control myBoxSizeDD"
										placeholder="write here the evaluation of Eleve" /></textarea>
									<textarea class="form-control myBoxSizeDD"
										placeholder="write here the evaluation of Eleve" /></textarea>
									<textarea class="form-control myBoxSizeDD"
										placeholder="write here the evaluation of Eleve" /></textarea>
								</div>
								<!-- niveau individuel -->
								<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 ">
									<h4 class="myBoxSizeTitle">Niveau individuel</h4>
									<div
										class="btn-group bootstrap-select open myBoxSizeDD text-center col-xs-12">
										<select class="selectpicker selectwidthauto myVerticalCenter2">
											<option>Non assigné</option>
											<option>Acquis</option>
											<option>Non acquis</option>
										</select>
									</div>
									<div
										class="btn-group bootstrap-select open myBoxSizeDD text-center col-xs-12">
										<select class="selectpicker selectwidthauto myVerticalCenter2">
											<option>Non assigné</option>
											<option>Acquis</option>
											<option>Non acquis</option>
										</select>
									</div>
									<div
										class="btn-group bootstrap-select open myBoxSizeDD text-center col-xs-12">
										<select class="selectpicker selectwidthauto myVerticalCenter2">
											<option>Non assigné</option>
											<option>Acquis</option>
											<option>Non acquis</option>
										</select>
									</div>
									<div
										class="btn-group bootstrap-select open myBoxSizeDD text-center col-xs-12">
										<select class="selectpicker selectwidthauto myVerticalCenter2">
											<option>Non assigné</option>
											<option>Acquis</option>
											<option>Non acquis</option>
										</select>
									</div>
									<div
										class="btn-group bootstrap-select open myBoxSizeDD text-center col-xs-12">
										<select class="selectpicker selectwidthauto myVerticalCenter2">
											<option>Non assigné</option>
											<option>Acquis</option>
											<option>Non acquis</option>
										</select>
									</div>

								</div>
								<!-- Etudiant -->
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
									<h4 class="myBoxSizeTitle">Etudiants</h4>
									<div
										class="btn-group bootstrap-select open col-lg-12  myBoxSizeDD">
										<ul class="myVerticalCenter">
											<li>Nom</li>
											<li>Prenom</li>
										</ul>
									</div>
									<div
										class="btn-group bootstrap-select open col-lg-12  myBoxSizeDD">
										<ul class="myVerticalCenter">
											<li>Nom</li>
											<li>Prenom</li>
										</ul>
									</div>
									<div
										class="btn-group bootstrap-select open col-lg-12  myBoxSizeDD">
										<ul class="myVerticalCenter">
											<li>Nom</li>
											<li>Prenom</li>
										</ul>
									</div>
									<div
										class="btn-group bootstrap-select open col-lg-12  myBoxSizeDD">
										<ul class="myVerticalCenter">
											<li>Nom</li>
											<li>Prenom</li>
										</ul>
									</div>
									<div
										class="btn-group bootstrap-select open col-lg-12  myBoxSizeDD">
										<ul class="myVerticalCenter">
											<li>Nom</li>
											<li>Prenom</li>
										</ul>
									</div>
								</div>
								<!-- end of the individuel/etudiant pack -->
							</div>
							<!-- ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -->
							<!-- end of the sous-point -->
						</div>

						<!-- separateur -->
						<div class="mySeparateur"></div>

						<!-- deuxieme sous point -->
						<!-- /////////////// HUGE COPY PASTA /////////////// -->

						<!-- description du sous point -->
						<div class=" panel-body row">
							<!-- colone sous competence -->
							<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
								<!-- chaque collone a un titre de la taille  myBoxSizeTitle-->
								<h4 class="myBoxSizeTitle"></h4>
								<!-- description du sous point -->
								<h5 class="text-center">être disponible à l'écoute et être
									capable d'exposé son point de vue</h5>
							</div>
							<!-- observation individuel niveau individuel et etudiant sont regroupé -> sur les petits écran ils sont les 3 ensemble -->
							<!-- ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -->
							<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
								<!-- observation individuel -->
								<div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
									<h4 class="myBoxSizeTitle">Observation individuelles</h4>
									<!-- il faut créer un nb de textarea depedant du nb d'eleve -->
									<textarea class="form-control myBoxSizeDD"
										placeholder="write here the evaluation of Eleve" /></textarea>
									<textarea class="form-control myBoxSizeDD"
										placeholder="write here the evaluation of Eleve" /></textarea>
									<textarea class="form-control myBoxSizeDD"
										placeholder="write here the evaluation of Eleve" /></textarea>
									<textarea class="form-control myBoxSizeDD"
										placeholder="write here the evaluation of Eleve" /></textarea>
									<textarea class="form-control myBoxSizeDD"
										placeholder="write here the evaluation of Eleve" /></textarea>
								</div>
								<!-- niveau individuel -->
								<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 ">
									<h4 class="myBoxSizeTitle">Niveau individuel</h4>
									<div
										class="btn-group bootstrap-select open myBoxSizeDD text-center col-xs-12">
										<select class="selectpicker selectwidthauto myVerticalCenter2">
											<option>Non assigné</option>
											<option>Acquis</option>
											<option>Non acquis</option>
										</select>
									</div>
									<div
										class="btn-group bootstrap-select open myBoxSizeDD text-center col-xs-12">
										<select class="selectpicker selectwidthauto myVerticalCenter2">
											<option>Non assigné</option>
											<option>Acquis</option>
											<option>Non acquis</option>
										</select>
									</div>
									<div
										class="btn-group bootstrap-select open myBoxSizeDD text-center col-xs-12">
										<select class="selectpicker selectwidthauto myVerticalCenter2">
											<option>Non assigné</option>
											<option>Acquis</option>
											<option>Non acquis</option>
										</select>
									</div>
									<div
										class="btn-group bootstrap-select open myBoxSizeDD text-center col-xs-12">
										<select class="selectpicker selectwidthauto myVerticalCenter2">
											<option>Non assigné</option>
											<option>Acquis</option>
											<option>Non acquis</option>
										</select>
									</div>
									<div
										class="btn-group bootstrap-select open myBoxSizeDD text-center col-xs-12">
										<select class="selectpicker selectwidthauto myVerticalCenter2">
											<option>Non assigné</option>
											<option>Acquis</option>
											<option>Non acquis</option>
										</select>
									</div>

								</div>
								<!-- Etudiant -->
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
									<h4 class="myBoxSizeTitle">Etudiants</h4>
									<div
										class="btn-group bootstrap-select open col-lg-12  myBoxSizeDD">
										<ul class="myVerticalCenter">
											<li>Nom</li>
											<li>Prenom</li>
										</ul>
									</div>
									<div
										class="btn-group bootstrap-select open col-lg-12  myBoxSizeDD">
										<ul class="myVerticalCenter">
											<li>Nom</li>
											<li>Prenom</li>
										</ul>
									</div>
									<div
										class="btn-group bootstrap-select open col-lg-12  myBoxSizeDD">
										<ul class="myVerticalCenter">
											<li>Nom</li>
											<li>Prenom</li>
										</ul>
									</div>
									<div
										class="btn-group bootstrap-select open col-lg-12  myBoxSizeDD">
										<ul class="myVerticalCenter">
											<li>Nom</li>
											<li>Prenom</li>
										</ul>
									</div>
									<div
										class="btn-group bootstrap-select open col-lg-12  myBoxSizeDD">
										<ul class="myVerticalCenter">
											<li>Nom</li>
											<li>Prenom</li>
										</ul>
									</div>
								</div>
								<!-- end of the individuel/etudiant pack -->
							</div>
							<!-- ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -->
							<!-- end of the sous-point -->
						</div>

						<!-- /////////////// END OF HUGE COPY PASTA /////////////// -->
						<!-- fin du deuxieme sous point -->
					</div>
					<!-- copy pasta of point and one sous-point -->
					<!-- ________________________________________________________________________________________________________________________________________________________ -->

					<!-- ce div ne sert que de marge -->
					<div class="col-lg-3 col-md-3 col-sm-1 col-xs-1"></div>

					<!-- la structure est EXACTEMENT la même que celle de sous competances -->
					<div
						class="panel panel-default col-lg-9 col-md-9 col-sm-11 col-xs-11">

						<!-- titre du 1 point -->
						<div class="panel-heading">
							Point 2 <br /> ex: ecouter et se faire ecouter
						</div>

						<!-- description du sous point -->
						<div class=" panel-body row">
							<!-- colone sous competence -->
							<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
								<!-- chaque collone a un titre de la taille  myBoxSizeTitle-->
								<h4 class="myBoxSizeTitle"></h4>
								<!-- description du sous point -->
								<h5 class="text-center">être disponible à l'écoute et être
									capable d'exposé son point de vue</h5>
							</div>
							<!-- observation individuel niveau individuel et etudiant sont regroupé -> sur les petits écran ils sont les 3 ensemble -->
							<!-- ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -->
							<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
								<!-- observation individuel -->
								<div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
									<h4 class="myBoxSizeTitle">Observation individuelles</h4>
									<!-- il faut créer un nb de textarea depedant du nb d'eleve -->
									<textarea class="form-control myBoxSizeDD"
										placeholder="write here the evaluation of Eleve" /></textarea>
									<textarea class="form-control myBoxSizeDD"
										placeholder="write here the evaluation of Eleve" /></textarea>
									<textarea class="form-control myBoxSizeDD"
										placeholder="write here the evaluation of Eleve" /></textarea>
									<textarea class="form-control myBoxSizeDD"
										placeholder="write here the evaluation of Eleve" /></textarea>
									<textarea class="form-control myBoxSizeDD"
										placeholder="write here the evaluation of Eleve" /></textarea>
								</div>
								<!-- niveau individuel -->
								<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 ">
									<h4 class="myBoxSizeTitle">Niveau individuel</h4>
									<div
										class="btn-group bootstrap-select open myBoxSizeDD text-center col-xs-12">
										<select class="selectpicker selectwidthauto myVerticalCenter2">
											<option>Non assigné</option>
											<option>Acquis</option>
											<option>Non acquis</option>
										</select>
									</div>
									<div
										class="btn-group bootstrap-select open myBoxSizeDD text-center col-xs-12">
										<select class="selectpicker selectwidthauto myVerticalCenter2">
											<option>Non assigné</option>
											<option>Acquis</option>
											<option>Non acquis</option>
										</select>
									</div>
									<div
										class="btn-group bootstrap-select open myBoxSizeDD text-center col-xs-12">
										<select class="selectpicker selectwidthauto myVerticalCenter2">
											<option>Non assigné</option>
											<option>Acquis</option>
											<option>Non acquis</option>
										</select>
									</div>
									<div
										class="btn-group bootstrap-select open myBoxSizeDD text-center col-xs-12">
										<select class="selectpicker selectwidthauto myVerticalCenter2">
											<option>Non assigné</option>
											<option>Acquis</option>
											<option>Non acquis</option>
										</select>
									</div>
									<div
										class="btn-group bootstrap-select open myBoxSizeDD text-center col-xs-12">
										<select class="selectpicker selectwidthauto myVerticalCenter2">
											<option>Non assigné</option>
											<option>Acquis</option>
											<option>Non acquis</option>
										</select>
									</div>

								</div>
								<!-- Etudiant -->
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
									<h4 class="myBoxSizeTitle">Etudiants</h4>
									<div
										class="btn-group bootstrap-select open col-lg-12  myBoxSizeDD">
										<ul class="myVerticalCenter">
											<li>Nom</li>
											<li>Prenom</li>
										</ul>
									</div>
									<div
										class="btn-group bootstrap-select open col-lg-12  myBoxSizeDD">
										<ul class="myVerticalCenter">
											<li>Nom</li>
											<li>Prenom</li>
										</ul>
									</div>
									<div
										class="btn-group bootstrap-select open col-lg-12  myBoxSizeDD">
										<ul class="myVerticalCenter">
											<li>Nom</li>
											<li>Prenom</li>
										</ul>
									</div>
									<div
										class="btn-group bootstrap-select open col-lg-12  myBoxSizeDD">
										<ul class="myVerticalCenter">
											<li>Nom</li>
											<li>Prenom</li>
										</ul>
									</div>
									<div
										class="btn-group bootstrap-select open col-lg-12  myBoxSizeDD">
										<ul class="myVerticalCenter">
											<li>Nom</li>
											<li>Prenom</li>
										</ul>
									</div>
								</div>
								<!-- end of the individuel/etudiant pack -->
							</div>
							<!-- ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -->
							<!-- end of the sous-point -->
						</div>
						<!-- ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -->
						<!-- end of the sous-point -->

						<!-- end of copy-past for point and sous-point -->
						<!-- ________________________________________________________________________________________________________________________________________________________ -->
					</div>
				</div>
			</div>
		</div>
		<!-- end of the drop down button -->
		<%
			}
		%>
	</div>


</body>
</html>