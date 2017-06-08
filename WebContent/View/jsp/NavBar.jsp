
<%@page
	import="java.util.*,gestiondesgrillesapp.model.*, javax.servlet.http.HttpServlet"%>

<%
	HttpSession sess = request.getSession(false);
	User user = (User) sess.getAttribute("user");
	String prenom;
	String nom;
	String numero;
	if (user == null) {
		prenom = "___";
		nom = "___";
		numero = "___";
	} else {
		prenom = user.getPrenom();
		nom = user.getNom();
		numero = user.getNumero();
	}

	ArrayList<Competence> competencesList = (ArrayList<Competence>) sess.getAttribute("competences");
	HashMap<Competence, ArrayList<SousCompetence>> sousCompetences = (HashMap<Competence, ArrayList<SousCompetence>>) sess
			.getAttribute("souscompetences");

	/* Competence competenceSelected = competencesList.get(0); */
	Competence competenceSelected = (Competence) sess.getAttribute("competenceSelected");

	System.out.println("\nNavBar : competenceSelected = " + competenceSelected.getTitre());
%>

<!-- NavBar -->
<div class="col-md-2 col-sm-2" style="position: fixed;">
	<!-- Navigation -->
	<nav class="navbar navbar-default navbar-static-top" role="navigation"
		style="border: none;">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<!-- <a class="navbar-brand" href="index.html">SB Admin v2.0</a> -->
		</div>

		<div class="navbar-default" role="navigation" style="margin-top: 0px;">
			<div class="sidebar-nav navbar-collapse">
				<ul class="nav" id="side-menu">
					<li><a href="http://www.isep.fr/"> <i class="fa"> <img
								src="http://www.isep.fr/parcours/logoISEP.png"
								class="img-fluid img-responsive" alt="ISEP logo">
						</i>
					</a></li>
					<li><h2 class="text-center">Evaluation de l'APP</h2></li>
					<!-- ---------------------------------------------------------------------------------------------------------------------------------------------------------- !-->
					<li>
						<!-- <table class="table" style="margin-bottom:0px">
								<tbody>
									<tr>
										<td class="text-center">Nom</td>
										<td class="text-center">Prenom</td>
									</tr>
									<tr>
										<td class="text-center"><button type="button" class="btn btn-primary">déconnexion</button></td>
										<td class="text-center" style="vertical-align:middle;">N°Etudiant</td>
									</tr>
								</tbody>
							</table> -->
						<p class="text-center">
							<%=nom%>
							-
							<%=prenom%>
							-
							<%=numero%></p>
						<div class="text-center" style="margin-bottom: 3px;">
							<button type="button" class="btn btn-primary">déconnexion</button>
						</div>

					</li>
					<!-- ---------------------------------------------------------------------------------------------------------------------------------------------------------- !-->
					<li class="sidebar-search">
						<div class="input-group custom-search-form">
							<input type="text" class="form-control" placeholder="Search...">
							<span class="input-group-btn">
								<button class="btn btn-default" type="button">
									<i class="fa fa-search"></i>
								</button>
							</span>
						</div> <!-- /input-group -->
					</li>
					<li class="myAccordion">
						<div class="panel-group" id="NavCompetenceAccordion">
							<%
								for (int i = 0; i < competencesList.size(); i++) {
									Competence competence = competencesList.get(i);
									String nomCompetence = competence.getTitre();
							%>

							<div 
							<%
								if (competence == competenceSelected) {
							%>
								class="panel panel-default in"
							<%
								} else {
							%>
								class="panel panel-default"
							<%
								}
							%>
							>
									<div class="panel-heading">
										<form action="NavBarServlet" method="post">
											<h4 class="panel-title">
												<%-- <a data-toggle="collapse"
											data-parent="#NavCompetenceAccordion"
											href="#CollapseNavCompetenceAccordion<%=i%>"><%=nomCompetence%></a> --%>
												<input type="submit" name="submitbutton"
													value="<%=nomCompetence%>">
											</h4>
										</form>
									</div>
										<div id="CollapseNavCompetenceAccordion<%=i%>" 
									<%
										if (competence == competenceSelected) {
									%>
										class="panel-collapse collapse in"
									<%
										} else {
									%>
										class="panel-collapse collapse"
									<%
										}
									%>
										>
											<%-- <div class="panel-body">
												<ul class="list-group">

													<%
														ArrayList<SousCompetence> sousCompetencesList = sousCompetences.get(competence);

															for (SousCompetence sousCompetence : sousCompetencesList) {
																String contenuSousCompetence = sousCompetence.getContenu();
													%>
													<li class="list-group-item" style="border: none;"><a
														href="#<%=competence.getID()%>-<%=sousCompetence.getID()%>"
														onclick="<%competenceSelected = competence;%>"> <%=contenuSousCompetence%></a></li>
													<%
														}
													%>
												</ul>
											</div> --%>
										</div>
									</div>
									<%
										}
									%>
								</div>
							</div>
					</li>
				</ul>
			</div>
			<!-- /.sidebar-collapse -->
		</div>
		<!-- /.navbar-static-side -->
	</nav>
</div>
<div class="col-md-2 col-sm-2"></div>


<!-- jQuery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- Bootstrap JavaScript for the select -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/bootstrap-select.min.js"></script>
