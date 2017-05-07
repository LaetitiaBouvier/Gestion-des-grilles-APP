<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*,gestiondesgrillesapp.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Création Grille de Compétences d'APP</title>
<script type="text/javascript"> 

var i =1;
			function addField(){
				i=i+1;
			    var field = "<div class='form-group'>"
				+"<label for='nom_grille' class='col-sm-2 control-label'>Compétence n°"+(i)+"</label>"
				+"<div class='form-vertical'>"
				+"<div id='fields'>"
				+"<div class='col-sm-6'>"	
			    +"<div class='input-group'>"
			    +"<div class='input-group-addon'>nom : </div>"
			    +"<input type='text' class='form-control' id='nom_competence' name='nom_competence"+(i)+"' placeholder='Nom de la compétence'>"
			    +"<div class='input-group-addon'>coef. :</div>"
			    +"<input type='text' class='form-control' id='coef_competence' name='coef_competence"+(i)+"' placeholder='Coefficient de la compétence'>"
			    +"</div><br /><div class='input-group'>"
			    +"<div class='input-group-addon'>desc. : </div>"
				+"<input type='text' class='form-control' id='desc_competence' name='desc_competence"+(i)+"' placeholder='Description de la compétence'>"
				+"</div></div>"
			    +"</div></div></div></div></div>"
			    +"<br />"
			    document.getElementById('fields').innerHTML += field;
			    +"<br />"
			    
			}
							

		</script>
</head>
<body>

	<div id="wrapper" class="container">
		<div id="page-wrapper" style="min-height: 683px;">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Création Grille de Compétences d'APP</h1>
				</div>
			</div>
			<div class="col-lg-12">
				<div class="panel panel-primary">
					<div class="panel-heading">Création de la grille et des compétences associées</div>
					<!-- /.panel-heading -->
					<div class="panel-body">
						<div>
							<!-- Nav tabs -->
<!-- 							<ul class="nav nav-tabs" role="tablist"> -->
<!-- 								<li role="presentation" class="active"><a href="#grille" -->
<!-- 									aria-controls="grille" role="tab" data-toggle="tab">La -->
<!-- 										Grille et les compétences associées</a></li> -->
<!-- 							</ul> -->
							<!-- Tab panes -->
							<div class="tab-content">
								<div role="tabpanel" class="tab-pane active" id="grille">
									<div class="container" style="margin-top: 30px;">
										<form method="POST" action="../../CreationGrilleServlet"
											class="form-horizontal">
											<div class="form-group">
												<label for="nom_grille" class="col-sm-2 control-label">Nom
													de la Grille</label>
												<div class="col-sm-6">
													<input type="text" class="form-control" id="nom_grille"
														name="nom_grille" placeholder="Nom de la grille d'APP ...">
												</div>
											</div>
											<div id="fields">
											<div class="form-group">
												<label for="nom_grille" class="col-sm-2 control-label">Compétence n°1</label>
												<div class="form-vertical">
													
														<div class="col-sm-6">
															<div class="input-group">
																<div class="input-group-addon">nom :</div>
																<input type="text" class="form-control"
																	id="nom_competence" name="nom_competence1"
																	placeholder="Nom de la compétence">
											
																<div class="input-group-addon">coef. :</div>
																<input type="text" class="form-control"
																	id="coef_competence" name="coef_competence1"
																	placeholder="Coefficient de la compétence">
															</div><br />
															<div class="input-group">
															<div class="input-group-addon">desc. :</div>
 																<input type="text" class="form-control" 
 																	id="desc_competence" name="desc_competence1" 
 																	placeholder="Description de la compétence"></div>
<!-- 																	<textarea class="form-control" id="desc_competence" name="desc_competence1"	placeholder="Description de la compétence"></textarea> -->
														</div>
													</div>
												</div>
												</div>
												<input type="button" class="btn btn-success" value="Ajouter une compétence à évaluer" onClick="addField();" />
												</div><br /><br />
											
											<div class="form-group">
												<div class="col-sm-offset-2 col-sm-10">
													<input type="submit" class="btn btn-default"
														value="Enregistrer" />
													<!--  <a  href="creation_competences.html"><button type="button" class="btn btn-default">Enregistrer</button></a>-->
												</div>
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>
</html>