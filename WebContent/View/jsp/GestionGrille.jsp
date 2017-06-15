<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file = "./../html/headCommun.html" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Menu Responsable Module</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
	</head>
	<body>
<%-- 		<%@ include file = "NavBar.jsp" %> --%>
		<div id="wrapper" class="container">
			
			<div id="page-wrapper" style="min-height: 683px;">
				
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">Gestion des Grilles d'APP</h1>
					</div>
				</div>
				
				<div class="col-lg-12">

					<div class="panel panel-primary">
						<div class="panel-heading">
							Menu
						</div>
						<div class="panel-body">
							<div class="row">
				                <div class="col-lg-3 col-md-6">
				                    <div class="panel panel-success">
				                        <div class="panel-heading">
				                            <div class="row">
				                                <div class="col-xs-3">
				                                    <i class="fa fa-save fa-5x"></i>
				                                </div>
				                                <div class="col-xs-9 text-right">
				                                    <div>Créer/Importer une Grille de Compétences</div>
				                                </div>
				                            </div>
				                        </div>
				                        <a href="CreationGrille.jsp">
				                            <div class="panel-footer">
				                                <span class="pull-left">View Details</span>
				                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
				                                <div class="clearfix"></div>
				                            </div>
				                        </a>
				                    </div>
				                </div>
				                <div class="col-lg-3 col-md-6">
				                    <div class="panel panel-danger">
				                        <div class="panel-heading">
				                            <div class="row">
				                                <div class="col-xs-3">
				                                    <i class="fa fa-save fa-5x"></i>
				                                </div>
				                                <div class="col-xs-9 text-right">
				                                    <div>Créer/Importer promotions / groupes /sous-groupes</div>
				                                </div>
				                            </div>
				                        </div>
				                        <a href="ImportEleves.jsp">
				                            <div class="panel-footer">
				                                <span class="pull-left">View Details</span>
				                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
				                                <div class="clearfix"></div>
				                            </div>
				                        </a>
				                    </div>
				                </div>
				                <div class="col-lg-3 col-md-6">
				                    <div class="panel panel-warning">
				                        <div class="panel-heading">
				                            <div class="row">
				                                <div class="col-xs-3">
				                                    <i class="fa fa-tasks fa-5x"></i>
				                                </div>
				                                <div class="col-xs-9 text-right">
				                                    <div>Gestion des promotions / groupes /sous-groupes</div>
				                                </div>
				                            </div>
				                        </div>
				                        <a href="gestion_promotions.html">
				                            <div class="panel-footer">
				                                <span class="pull-left">View Details</span>
				                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
				                                <div class="clearfix"></div>
				                            </div>
				                        </a>
				                    </div>
				                </div>
				            </div>
						</div>
					</div>
				</div>
				
				<div class="col-lg-12">
					<div class="panel panel-primary">
						<div class="panel-heading">
							Grilles de Compétences

						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
						
							<div class="fluid-container">
								<div class="row">
									<div class="col-xs-6 col-sm-3">
										<div class="list-group">
											<!-- Default Grille -->

											<a href="#" class="list-group-item list-group-item-info">
												Grille d'APP A1
												<button type="button" class="btn btn-default btn-xs" data-toggle="tooltip" data-placement="bottom" title="Modifier">
													<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
												</button>
												<button type="button" class="btn btn-default btn-xs" data-toggle="tooltip" data-placement="bottom" title="Supprimer">

													<span class="glyphicon" aria-hidden="true">x</span>

												</button>
											</a>
											<!-- List group -->
											<a href="#" class="list-group-item">Vue d'ensemble</a>
											<a href="#" class="list-group-item">Communication</a>

											<a href="#" class="list-group-item">Travail en équipe</a>

											<a href="#" class="list-group-item">Conduite de projet</a>
											<a href="#" class="list-group-item">Professionnel responsable</a>
											<a href="#" class="list-group-item">Electronique</a>
											<a href="#" class="list-group-item">Informatique</a>

											<a href="#" class="list-group-item">Télécommunications</a>
											<a href="#" class="list-group-item">Traitement du signal</a>
											<a href="#" class="list-group-item">Présence</a>
										</div>
									</div>
									<div class="col-xs-6 col-sm-3">
										<div class="list-group">
											<!-- Default Grille -->

											<a href="#" class="list-group-item list-group-item-info">

												Grille d'APP A1 Inter.
												<button type="button" class="btn btn-default btn-xs" data-toggle="tooltip" data-placement="bottom" title="Modifier">
													<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
												</button>
												<button type="button" class="btn btn-default btn-xs" data-toggle="tooltip" data-placement="bottom" title="Supprimer">

													<span class="glyphicon" aria-hidden="true">x</span>
												</button>
											</a>
											<!-- List group -->
											<a href="#" class="list-group-item">Vue d'ensemble</a>
											<a href="#" class="list-group-item">Communication</a>
											<a href="#" class="list-group-item">Travail en équipe</a>

											<a href="#" class="list-group-item">Conduite de projet</a>
											<a href="#" class="list-group-item">Professionnel responsable</a>
											<a href="#" class="list-group-item">Electronique</a>
											<a href="#" class="list-group-item">Informatique</a>

											<a href="#" class="list-group-item">Télécommunications</a>
											<a href="#" class="list-group-item">Traitement du signal</a>
											<a href="#" class="list-group-item">Présence</a>

										</div>
									</div>	
									<div class="col-xs-6 col-sm-3">
										<div class="list-group">
											<!-- Default Grille -->

											<a href="#" class="list-group-item list-group-item-info">

												Grille d'APP A2-S3
												<button type="button" class="btn btn-default btn-xs" data-toggle="tooltip" data-placement="bottom" title="Modifier">
													<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
												</button>
												<button type="button" class="btn btn-default btn-xs" data-toggle="tooltip" data-placement="bottom" title="Supprimer">

													<span class="glyphicon" aria-hidden="true">x</span>

												</button>
											</a>
											<!-- List group -->
											<a href="#" class="list-group-item">Vue d'ensemble</a>
											<a href="#" class="list-group-item">Communication</a>

											<a href="#" class="list-group-item">Travail en équipe</a>

											<a href="#" class="list-group-item">Conduite de projet</a>
											<a href="#" class="list-group-item">Professionnel responsable</a>
											<a href="#" class="list-group-item">Electronique</a>
											<a href="#" class="list-group-item">Informatique</a>

											<a href="#" class="list-group-item">Télécommunications</a>
											<a href="#" class="list-group-item">Traitement du signal</a>
											<a href="#" class="list-group-item">Présence</a>
										</div>
									</div>	
									<div class="col-xs-6 col-sm-3" id="liste">
										<div class="list-group">
											<!-- Default Grille -->
											<a href="#" class="list-group-item list-group-item-info">

												Grille d'APP A2-S4
												<button type="button" class="btn btn-default btn-xs" data-toggle="tooltip" data-placement="bottom" title="Modifier">
													<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
												</button>

												<button type="button" id="remove" class="btn btn-default btn-xs" data-toggle="tooltip" data-placement="bottom" title="Supprimer">
													<span class="glyphicon" aria-hidden="true">x</span>

												</button>
											</a>
											<!-- List group -->
											<a href="#" class="list-group-item">Vue d'ensemble</a>
											<a href="#" class="list-group-item">Communication</a>

											<a href="#" class="list-group-item">Travail en équipe</a>

											<a href="#" class="list-group-item">Conduite de projet</a>
											<a href="#" class="list-group-item">Professionnel responsable</a>
											<a href="#" class="list-group-item">Electronique</a>
											<a href="#" class="list-group-item">Informatique</a>

											<a href="#" class="list-group-item">Télécommunications</a>
											<a href="#" class="list-group-item">Traitement du signal</a>
											<a href="#" class="list-group-item">Présence</a>
										</div>
									</div>	
									

								</div>
								
							</div>
														
						</div>
					</div>
				</div>
				
			</div>
			
		</div>
		
		<!-- tooltip -->
		<script type="text/javascript">

			
		</script>
		
		<script src="https://raw.githack.com/LaetitiaBouvier/Gestion-des-grilles-APP/master/WebContent/View/jquery/jquery.min.js"></script>
		

</body>
</html>