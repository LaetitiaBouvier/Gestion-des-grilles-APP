<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*,gestiondesgrillesapp.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Création Grille de Compétences d'APP</title>
<script
		src="./../js/jquery.min.js"></script>
	<link href="./../css/bootstrap.min.css" rel="stylesheet">
	<script
		src="./../js/bootstrap.min.js"></script>
</head>
<body>
	<%@ include file = "NavBar.jsp" %>
	<div id="wrapper" class="container">
		<div id="page-wrapper" style="min-height: 683px;">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Création Grille de Compétences d'APP</h1>
				</div>
			</div>
			
			<div class="col-lg-12">
				<div class="panel panel-primary">
					<div class="panel-heading">Définition du nom de la grille</div>
					<!-- /.panel-heading -->
					<div class="panel-body">
						<div>
							<!-- Tab panes -->
							<div class="tab-content">
								<div role="tabpanel" class="tab-pane active" id="grille">
									<div class="container" style="margin-top: 30px;">
										
										<form method="POST" action="../../CreationGrilleServlet" class="form-horizontal">
											
											<div class="form-group">
												<label for="nom_grille" class="col-sm-2 control-label">Nom de la Grille</label>
												<div class="col-sm-6">
													<input type="text" class="form-control" id="nom_grille" name="nom_grille" placeholder="Nom de la grille d'APP ...">
														<br />
												</div>
											</div>
																
											<div align="center">
												<input type="submit" class="btn btn-primary" value="Enregistrer" />
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


	

</body>
</html>