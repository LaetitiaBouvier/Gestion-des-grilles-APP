<%@page import="gestiondesgrillesapp.controller.ObjectDBUtilServlet"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" import="javax.persistence.EntityManager"%>

<%@ include file = "./../html/headCommun.html" %>
<body>

<%@ include file = "NavBar.jsp" %>

<%
	// Ouvre la BDD
	EntityManager em = (EntityManager) sess.getAttribute("em");

	List<Promotion> promotionListTemp = em.createQuery("SELECT c FROM Promotion c", Promotion.class).getResultList();
	List<Promotion> promotionList = (List<Promotion>) ObjectDBUtilServlet.extractMultipleObjectsManagingExceptions(promotionListTemp);
	
	HashMap<Promotion, ArrayList<Groupe>> hashGroupes = new HashMap<>();
	HashMap<Groupe, ArrayList<SousGroupe>> hashSousGroupes = new HashMap<>();
	
	HashMap<Groupe, ArrayList<User>> hashUsersGroupe = new HashMap<>();
	HashMap<SousGroupe, ArrayList<User>> hashUsersSousGroupe = new HashMap<>();
	
	List<Grille> grilleListTemp = em.createQuery("SELECT g FROM Grille g WHERE isModel=true", Grille.class).getResultList();
	List<Grille> modelsList = (List<Grille>) ObjectDBUtilServlet.extractMultipleObjectsManagingExceptions(grilleListTemp);
	
	for (Promotion promotion : promotionList) {
		System.out.println("" + promotion.getAnneeObtensionDiplome());
		
		ArrayList<Groupe> groupeList = new ArrayList<>();
		for(long groupeID : promotion.getGroupesIDs())
		{
			List<Groupe> groupeListTemp = em.createQuery("SELECT c FROM Groupe c WHERE id="+groupeID, Groupe.class).getResultList();
			Groupe groupe = (Groupe) ObjectDBUtilServlet.extractOnlyOneObjectManagingExceptions(groupeListTemp);
			
			ArrayList<User> usersGroupeList = new ArrayList<>();
			for(long userID : groupe.getElevesIDs())
			{
				List<User> usersListTemp = em.createQuery("SELECT c FROM User c WHERE id="+userID, User.class).getResultList();
				User u = (User) ObjectDBUtilServlet.extractOnlyOneObjectManagingExceptions(usersListTemp);
				
				usersGroupeList.add(u);
			}
			hashUsersGroupe.put(groupe, usersGroupeList);
			
			groupeList.add(groupe);
			
			ArrayList<SousGroupe> sousGroupeList = new ArrayList<>();
			for(long sousGroupeID : groupe.getSousGroupesIDs())
			{
				List<SousGroupe> sousGroupeListTemp = em.createQuery("SELECT c FROM SousGroupe c WHERE id="+sousGroupeID, SousGroupe.class).getResultList();
				SousGroupe sousGroupe = (SousGroupe) ObjectDBUtilServlet.extractOnlyOneObjectManagingExceptions(sousGroupeListTemp);
				
				ArrayList<User> usersSousGroupeList = new ArrayList<>();
				for(long userID : sousGroupe.getElevesIDs())
				{
					List<User> usersListTemp = em.createQuery("SELECT c FROM User c WHERE id="+userID, User.class).getResultList();
					User u = (User) ObjectDBUtilServlet.extractOnlyOneObjectManagingExceptions(usersListTemp);
					
					usersSousGroupeList.add(u);
				}
				hashUsersSousGroupe.put(sousGroupe, usersSousGroupeList);
				
				sousGroupeList.add(sousGroupe);
			}
			hashSousGroupes.put(groupe, sousGroupeList);
		}
		hashGroupes.put(promotion, groupeList);
	}
	
	sess.removeAttribute("hashUsersGroupe");
	sess.setAttribute(	 "hashUsersGroupe", hashUsersGroupe);

	// Ferme la BDD
	if (em.getTransaction().isActive()) em.getTransaction().rollback();
	em.close();
%>

<style type="text/css">

thead th {
	background-color: #337ab7;
	color: white;
	text-align: center;
	height: 6em;
}

.list_full {
	padding: 0px; /*unrecognized => in div */
	height: 100vh;
}

.glyphicon {
	float: right;
	display: block;
	position: absolute;
	border-top-left-radius: 4px;
	border-top-right-radius: 4px;
	height: 60%;
	top: 20%;
	right: 5px;
	z-index: 1;
	border-top-right-radius: 4px;
}

.bigButton {
	height: 100%; 
	z-index: 0;
	width: 100%; 

}

.list-group-item:first-child {
	border-top-left-radius: 0px;
	border-top-right-radius: 0px;
}

.list-group-item:last-child {
	margin-bottom: 0;
	border-bottom-right-radius: 0px;
	border-bottom-left-radius: 0px;
}

.list-group-item {
	border-top-left-radius: 0px;
	border-top-right-radius: 0px;
}

.fullButton {
	padding: 0px;
	white-space: nowrap;
}

.diebootstrapdie {
	position: absolute;
	visibility: hidden;
	margin: 0px;
	width: 0px;
	height: 0px;
}

.diebootstrapdiedie {
	padding-left: 30px;
}

.allStudent {
	
}

.student {
	font-size: 0.6em;
	padding-top: 3px;
	padding-bottom: 3px;
	background-color: #eee;
}

button.list-group-item:focus.selected {
	color: #555;
	text-decoration: none;
	background-color: orange;
}

button.list-group-item:focus.unselected {
	color: #555;
	text-decoration: none;
}

.selected {
	background-color: orange;
}

.unselected {
	
}

</style>

	<div class="col-lg-10 col-sm-10 col-sm-12 col-xs-12">
	<div class="row">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th class="col-xs-4 "><p class="text-center">Promotions</p></th>
					<th class="col-xs-4 "><p class="text-center">Groupes</p></th>
					<th class="col-xs-4 "><p class="text-center">Sous-Groupes</p></th>
				</tr>
			</thead>
			<tbody>

				<tr>
					<td class="col-xs-4 list_full" style="padding: 0px;">
						<!-- style="padding:0px;" -->
						
						<% for(Promotion promotion : promotionList) {%>
						<div class="list-group-item fullButton">
							<button type="button"
								class="list-group-item bigButton diebootstrapdiedie  unselected"
								data-promid="<%=(""+promotion.getAnneeObtensionDiplome())%>"><%=(""+promotion.getAnneeObtensionDiplome())%></button>
							<button type="button" class="diebootstrapdie"></button>
						</div>
						<%} %>
					</td>



					<td class="col-xs-4 list_full" style="padding: 0px;">
					
						<% for(Promotion promotion : promotionList) {%>
								
							<% for(Groupe groupe : hashGroupes.get(promotion)){ %>
							
								<div data-toggle-element-promid="<%=(""+promotion.getAnneeObtensionDiplome())%>">
									<div class="list-group-item fullButton">
										<button type="button" class="list-group-item bigButton unselected"
											data-groupeid="<%=groupe.getNom()%>"><%=groupe.getNom()%></button>

									<%if(user.isRespoModule()) {%>
										<form action="NavigationServlet" method="post" class="jsCallText form-inline text-center col-xs-offset-4"
											style="margin-top: -5vh; width: 50%;">
											<div class="btn-group bootstrap-select open"
												style="width: 50%;">
												<select name="submitbutton" class="selectpicker selectwidthauto">
												
												<%for(Grille model : modelsList) {%>
													<option value="<%=model.getID()%> <%=groupe.getID()%>"><%=model.getTitre()%></option>
												<%} %>
												</select>
											</div>
											<button type="submit" class="btn btn-primary fa fa-floppy-o"
												style="position: absolute; z-index: 4;"></button>
										</form>
									<%} %>

									<button class="glyphicon glyphicon-menu-right"
											aria-hidden="true" data-toggleStudent></button>
									</div>
									<div class="list-group-item fullButton allStudent " data-toggleStudent-element>
									
										<% for(User u : hashUsersGroupe.get(groupe)) {%>
											<button type="button" class="list-group-item student"><%=u.getPrenom()%> <%=u.getNom()%></button>
										<%} %>
									</div>
								</div>
							<%} %>
						<%} %>
					</td>

					<td class="col-xs-4 list_full" style="padding: 0px;">
					
					
					<% for(Promotion promotion : promotionList) {%>
								
						<% for(Groupe groupe : hashGroupes.get(promotion)){ %>
							
							<% for(SousGroupe sousGroupe : hashSousGroupes.get(groupe)){ %>
					
							<div data-toggle-element-groupeid="<%=groupe.getNom()%>" data-toggle-element2-promid="<%=(""+promotion.getAnneeObtensionDiplome())%>">
								<div class="list-group-item fullButton">
									<form action="NavBarServlet" method="post">
										<button type="submit" value="Vue d'ensemble :<%=sousGroupe.getID()%>" name="submitbutton" class="list-group-item bigButton"><%=sousGroupe.getNom()%></button>
									</form>
									<button class="glyphicon glyphicon-menu-right"
										aria-hidden="true" data-toggleStudent></button>
								</div>
								<div class="list-group-item fullButton allStudent " data-toggleStudent-element>
									<% for(User u : hashUsersSousGroupe.get(sousGroupe)) {%>
										<button type="button" class="list-group-item student"><%=u.getPrenom()%> <%=u.getNom()%></button>
									<%} %>
								</div>
							</div>
							<%}%>
						<%}%>
					<%}%>
						
						
						
						
						<div data-toggle-element-groupeid="1" data-toggle-element2-promid="2018">
							<div class="list-group-item fullButton">
								<button type="button" class="list-group-item bigButton">
									G1B</button>
								<!-- 		<button id="g1" class="glyphicon glyphicon-menu-up"
									aria-hidden="true"></button> -->
								<button class="glyphicon glyphicon-menu-right"
									aria-hidden="true" data-toggleStudent></button>
							</div>
							<div class="list-group-item fullButton allStudent "
								data-toggleStudent-element>
								<!-- data-toggleStudent-element-groupeid="2" -->
								<button type="button" class="list-group-item student">
									eleve</button>
								<button type="button" class="list-group-item student ">
									eleve</button>
								<button type="button" class="list-group-item student ">
									eleve</button>
								<button type="button" class="list-group-item student ">
									eleve</button>
								<!-- <button id="g1" class="glyphicon glyphicon-menu-right" aria-hidden="true"></button> -->
							</div>
						</div>
						<div data-toggle-element-groupeid="2" data-toggle-element2-promid="2018">
							<div class="list-group-item fullButton">
								<button type="button" class="list-group-item bigButton">
									G2A</button>
								<!-- 		<button id="g1" class="glyphicon glyphicon-menu-up"
									aria-hidden="true"></button> -->
								<button class="glyphicon glyphicon-menu-right"
									aria-hidden="true" data-toggleStudent></button>
							</div>
							<div class="list-group-item fullButton allStudent "
								data-toggleStudent-element>
								<!-- data-toggleStudent-element-groupeid="2" -->
								<button type="button" class="list-group-item student">
									eleve</button>
								<button type="button" class="list-group-item student ">
									eleve</button>
								<button type="button" class="list-group-item student ">
									eleve</button>
								<button type="button" class="list-group-item student ">
									eleve</button>
								<!-- <button id="g1" class="glyphicon glyphicon-menu-right" aria-hidden="true"></button> -->
							</div>
						</div>
						<div data-toggle-element-groupeid="2" data-toggle-element2-promid="2018">
							<div class="list-group-item fullButton">
								<button type="button" class="list-group-item bigButton">
									G2B</button>
								<!-- 		<button id="g1" class="glyphicon glyphicon-menu-up"
									aria-hidden="true"></button> -->
								<button class="glyphicon glyphicon-menu-right"
									aria-hidden="true" data-toggleStudent></button>
							</div>
							<div class="list-group-item fullButton allStudent "
								data-toggleStudent-element>
								<!-- data-toggleStudent-element-groupeid="2" -->
								<button type="button" class="list-group-item student">
									eleve</button>
								<button type="button" class="list-group-item student ">
									eleve</button>
								<button type="button" class="list-group-item student ">
									eleve</button>
								<button type="button" class="list-group-item student ">
									eleve</button>
								<!-- <button id="g1" class="glyphicon glyphicon-menu-right" aria-hidden="true"></button> -->
							</div>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script>
	
		/**
		 * toggle on load
		 */
		$(document).ready(function() {
			$("[data-toggleStudent-element]").slideToggle();
			$('[data-toggle-element-promid]').hide(); 
			$('[data-toggle-element-groupeid]').hide(); 
		 });

		/**
		 * function to toggle student
		 */
		$("[data-toggleStudent]").click(function(){
		//	console.log($(this).data('groupeid'));
			$(this).parent().parent().children("[data-toggleStudent-element]").slideToggle();
			$(this).toggleClass('glyphicon-triangle-bottom glyphicon-menu-right');
		}); 

		/**
		 * function to toggle group
		 */
		$("[data-groupeid]").click(function(){    
			$('[data-toggle-element-groupeid="'+$(this).data('groupeid')+'"]').slideToggle();    
			$(this).toggleClass('selected unselected');
		}); 

		/**
		 * function to toggle prom
		 */
		$("[data-promid]").click(function(){    
			$(this).toggleClass('selected unselected');
			$('[data-toggle-element-promid="'+$(this).data('promid')+'"]').slideToggle(); 
			$('[data-toggle-element2-promid="'+$(this).data('promid')+'"]').hide(); 
		}); 
	
	</script>
	
	<script>
	<!-- -------------------------- --> console.log($('form.myBoxSize'));
		$('form.jsCallText').submit( function(e) {
			e.preventDefault();
			
			$('#logoIsepForTick').append('<img id="2Bremoved" src="https://openclipart.org/image/2400px/svg_to_png/167549/Kliponious-green-tick.png" style="position:relative; top:0; left:0; right:0; bottom:0; margin-left:auto%; height:auto; width:100%; overflow:visible;"></img>')
				setTimeout(function() {
					$("#2Bremoved").remove();
			}, 800);
			
			$.ajax({
				method : 'POST',
				url : 'NavigationServlet',
				data : $(this).serializeArray(),
				dataType : 'JSON',
				success : function() {
					console.log('success');
				},
				error : function() {
				}
			});
		});
	</script>
	

</body>
</html>