<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@page import="java.util.*,gestiondesgrillesapp.model.*"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Création Grille de Compétences d'APP</title>
		<script type="text/javascript">
			$('#myTabs a').click(function (e) {
				  e.preventDefault()
				  $(this).tab('show')
				})
			$('#myTabs a[href="#vue_ensemble"]').tab('show') // Select tab by name
			$('#myTabs a:first').tab('show') // Select first tab
			$('#myTabs a:last').tab('show') // Select last tab
			$('#myTabs li:eq(2) a').tab('show') // Select third tab (0-indexed)
			
			function addField(){
			    var field = "<div class='col-sm-6'>"	
			    +"<div class='input-group'>"
			    +"<input name='champ' type='text' class='form-control' id='nom_grille' placeholder='Nom de la compétence'>"
			    +"<div class='input-group-addon'>.coef</div>"
			    +"<input type='text' class='form-control' placeholder='Coefficient de la compétence'>"
			    +"</div></div>";
			    document.getElementById('fields').innerHTML += field;
			}
		</script>
	</head>
	<body>
			<%
            @SuppressWarnings("unchecked")
            List<Competence> compList = (List<Competence>) request.getAttribute("compList");
        	if(compList != null){
	            for (Competence comp : compList) { %>
	                <li> <%= comp.getContenu()%> : <%= comp.getID()%> </li> <%
	            }
        	}%>
		<div id="wrapper" class="container">
			
			<div id="page-wrapper" style="min-height: 683px;">
				
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">Création Grille de Compétences d'APP</h1>
					</div>
				</div>
				
				<div class="col-lg-12">
					<div class="panel panel-primary">
						<div class="panel-heading">
			
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
																					
							<div>

							  <!-- Nav tabs -->
							  <ul class="nav nav-tabs" role="tablist">
							    <li role="presentation" class="active"><a href="#vue_ensemble" aria-controls="vue_ensemble" role="tab" data-toggle="tab">Vue d'ensemble<span class="badge" style="margin-left:2px;">15</span></a></li>
							    <li role="presentation"><a href="#communication" aria-controls="communication" role="tab" data-toggle="tab">Communication<span class="badge" style="margin-left:2px;">2</span></a></li>
							    <li role="presentation"><a href="#travail_equipe" aria-controls="travail_equipe" role="tab" data-toggle="tab">Travail en équipe<span class="badge" style="margin-left:2px;">2</span></a></li>
							  	<li role="presentation"><a href="#conduite_projet" aria-controls="conduite_projet" role="tab" data-toggle="tab">Conduite de projet<span class="badge" style="margin-left:2px;">2</span></a></li>
							  	<li role="presentation"><a href="#professionnel_responsable" aria-controls="professionnel_responsable" role="tab" data-toggle="tab">Professionnel responsable<span class="badge" style="margin-left:2px;">2</span></a></li>
							  	<li role="presentation"><a href="#electronique" aria-controls="electronique" role="tab" data-toggle="tab">Electronique<span class="badge" style="margin-left:2px;">3</span></a></li>
							  	<li role="presentation"><a href="#informatique" aria-controls="informatique" role="tab" data-toggle="tab">Informatique<span class="badge" style="margin-left:2px;">3</span></a></li>
							  	<li role="presentation"><a href="#telecommunications" aria-controls="telecommunications" role="tab" data-toggle="tab">Télécommunications<span class="badge" style="margin-left:2px;">3</span></a></li>
							  	<li role="presentation"><a href="#traitement_signal" aria-controls="traitement_signal" role="tab" data-toggle="tab">Traitement du signal<span class="badge" style="margin-left:2px;">3</span></a></li>
							  	<li role="presentation"><a href="#presence" aria-controls="presence" role="tab" data-toggle="tab">Présence<span class="badge" style="margin-left:2px;">1</span></a></li>
							  </ul>
							
							  <!-- Tab panes -->
							  <div class="tab-content">
							    <div role="tabpanel" class="tab-pane active" id="vue_ensemble">
							    	<p class="lead" style="margin-top: 20px;">évaluation de l'Apprentissage Par Projet par l'Approche Par Compétence</p>
							    	<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true" style="margin-top:20px;">
							    	
									  <div class="panel panel-danger">
									    <div class="panel-heading" role="tab" id="headingOne">
									      <h4 class="panel-title">
									        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
									          Agir avec probité professionnelle et honnêteté intellectuelle  <span class="badge" style="margin-left:2px;">2</span>
									        </a>
									      </h4>
									    </div>
									    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
									      <div class="panel-body">
									      	 <ul class="list-group">
									      	 	<li class="list-group-item list-group-item-warning">Etre présent au travail, ponctuel et participatif</li>
									      	 	<li class="list-group-item">- Description du sous point...</li>
									      	 </ul>
									      	 <ul class="list-group">
									      	 	<li class="list-group-item list-group-item-warning">Réaliser son travail, et assumer ses responsabilités individuelles et collectives</li>
									      	 	<li class="list-group-item"><textarea class="form-control" placeholder="Description du sous point" rows="3"></textarea><button class="btn btn-default" type="submit" style="margin-top: 5px;">Valider</button></li>
									      	 </ul>
									      	 <ul class="list-group">
									      	 	<li class="list-group-item list-group-item-warning"><div class="col-xs-3"><input type="text" class="form-control" placeholder="Ajouter un point"></div><button class="btn btn-default" type="submit">Valider</button></li>
									      	 </ul>
									      	 <ul class="list-group">
									      	 	<li class="list-group-item list-group-item-success">Ajouter un sous-point
									      	 		<button type="button" class="btn btn-default btn-xs" data-toggle="tooltip" data-placement="bottom" title="Cr�er" style="margin-left: 70px;">
														<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
													</button>
									      	 	</li>
									      	 </ul>
									      </div>
									    </div>
									  </div>
									  
									  <div class="panel panel-danger">
									    <div class="panel-heading" role="tab" id="headingTwo">
									      <h4 class="panel-title">
									        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
									          Respecter les délais  <span class="badge" style="margin-left:2px;">2</span>
									        </a>
									      </h4>
									    </div>
									    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
									      <div class="panel-body">
									        <ul class="list-group">
									      	 	<li class="list-group-item list-group-item-success">Ajouter un sous-point
									      	 		<button type="button" class="btn btn-default btn-xs" data-toggle="tooltip" data-placement="bottom" title="Cr�er" style="margin-left: 70px;">
														<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
													</button>
									      	 	</li>
									      	 </ul>
								      	 </div>
									    </div>
									  </div>
									  <div class="panel panel-danger">
									    <div class="panel-heading" role="tab" id="headingThree">
									      <h4 class="panel-title">
									        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
									          <div class="col-xs-3"><input type="text" class="form-control" placeholder="Ajouter un point"></div><button class="btn btn-default" type="submit">Valider</button>
									        </a>
									      </h4>
									    </div>
									    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
									      <div class="panel-body">
									        <ul class="list-group">
									      	 	<li class="list-group-item list-group-item-success">Ajouter un sous-point
									      	 		<button type="button" class="btn btn-default btn-xs" data-toggle="tooltip" data-placement="bottom" title="Cr�er" style="margin-left: 70px;">
														<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
													</button>
									      	 	</li>
									      	 </ul>
								      	 </div>
									    </div>
									  </div>
									  <div class="col-md-3 col-md-offset-3 text-center" style="margin-top:10px">
									    <button type="button" class="btn btn-success">Ajouter un point<span class="glyphicon glyphicon-plus" aria-hidden="true"></span></button>
									</div>
									  
									  
									</div>
							    	
							    </div>
							    <div role="tabpanel" class="tab-pane" id="communication">
							    
							    </div>
							    <div role="tabpanel" class="tab-pane" id="travail_equipe">
							    
							    </div>
							    <div role="tabpanel" class="tab-pane" id="conduite_projet">
							    
							    </div>
							    <div role="tabpanel" class="tab-pane" id="professionnel_responsable">
							    
							    </div>
							    <div role="tabpanel" class="tab-pane" id="electronique">
							    
							    </div>
							    <div role="tabpanel" class="tab-pane" id="informatique">
							    
							    </div>
							    <div role="tabpanel" class="tab-pane" id="telecommunications">
							    
							    </div>
							    <div role="tabpanel" class="tab-pane" id="traitement_signal">
							    
							    </div>
							    <div role="tabpanel" class="tab-pane" id="presence">
							    
							    </div>
							  </div>
							
							</div>
														
						</div>
					</div>
				</div>
				
			</div>
			
		</div>
		
		
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</body>
</html>