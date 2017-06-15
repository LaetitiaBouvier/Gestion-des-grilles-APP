<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<title>Import Grille</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
	<div id="wrapper" class="container">
	
		<div id="page-wrapper" style="min-height: 683px">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Import des compétences</h1>
				</div>
			</div>
			<div class="row">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3>Liste des compétences, sous-compétences, points et sous-points</h3>
					</div>
					<div class="panel-body">
<<<<<<< HEAD
=======

						<form method="POST" action="../../CreationEleveServlet" class="form-horizontal">
>>>>>>> branch 'camille2' of https://github.com/LaetitiaBouvier/Gestion-des-grilles-APP.git

					<form method="POST" action="../../CreationEleveServlet" class="form-horizontal">
						<div id="dataTables_wrapper" class="dataTables_wrapper form-inline no-footer">
<<<<<<< HEAD
=======
							

>>>>>>> branch 'camille2' of https://github.com/LaetitiaBouvier/Gestion-des-grilles-APP.git
							<div class="row" style="margin-bottom: 10px;">
								<div class="col-md-8" style=" margin-left: 20px; margin-bottom: 10px;">
									<br/><br/><h6>Merci de veiller à remplir le tableau de la façon suivante et d'utiliser l'entête présentée sur la première ligne de votre fichier .csv.</h6>
								</div>
								<div class="row" style="margin-bottom: 10px;">
									<div class="col-md-12" style=" margin-left: 20px; margin-bottom: 10px;">		
									  	<img src="https://raw.githack.com/LaetitiaBouvier/Gestion-des-grilles-APP/master/WebContent/View/ressources/tableauGrille.png" style="width:80%;"/>
								  	</div>
								</div>
								<div class="row" style=" margin-bottom: 10px;">
								  	<div class="col-md-6" style=" margin-left: 20px; margin-bottom: 10px;">
								  		<h6>Veillez à ne pas laisser de champ vide, inscrire un point "." dans les cases vides.</h6><br/><br/>
								  	</div>
							  	</div>
							</div>
							<div class="row" style=" margin-left: 20px; margin-bottom: 10px;">
								<div class="col-md-4" style="">
									<h6>
										Veuillez sélectionner un fichier CSV à importer.<br/><br/>
									</h6>
								</div>
<<<<<<< HEAD
								<div class="col-md-4">		
=======
								<div class="col-md-4">								
>>>>>>> branch 'camille2' of https://github.com/LaetitiaBouvier/Gestion-des-grilles-APP.git
									<div id="inputs" class="clearfix">
							    		<input type="file" id="files" name="files[]" multiple />
							 		 </div>
							  		<hr />
							  		<output id="list">
							  		</output>
							  		<hr />
								</div>
								<div class="col-md-4" >
				                    <div class="panel panel-warning">
				                        <div class="panel-heading">
				                            <div class="row">
				                                <div class="col-xs-3">
				                                    <i class="fa fa-support fa-5x"></i>
				                                </div>
				                                <div class="col-xs-9 text-right">
				                                    <div>Créer un fichier CSV à partir d'un fichier type</div>
				                                </div>
				                            </div>
				                        </div>
				                        <a href="gestion_promotions2.html">
				                            <div class="panel-footer">
				                                <span class="pull-left">View Details</span>
				                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
				                                <div class="clearfix"></div>
				                            </div>
				                        </a>
				                    </div>
								</div>
							</div>
							
							<div class="row">
								<div class="col-md-12">
									<div class="alert alert-success alert-dismissable" style="display:none;">
									    <button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button>
							            <strong>Success!</strong> Votre fichier a bien été intégré à la base de données.
							        </div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<table id="contents" class="table table-striped table-bordered table-hover dataTable no-footer dtr-inline collapsed" role="grid" style="width:100%;">
			  						</table>
								</div>
							</div>
						</div>
						<input type="submit" class="btn btn-primary" value="Soumettre cette grille" />
						</form>
					</div>
				</div>
			</div>
				<div class="col-lg-12">
			  		
				</div>
			</div>
	
	</div>
  
	
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
  <script src="https://raw.githack.com/LaetitiaBouvier/Gestion-des-grilles-APP/master/WebContent/View/js/jquery.csv.js"></script>
  <script>
    $(document).ready(function() {
      if(isAPIAvailable()) {
        $('#files').bind('change', handleFileSelect);
      }
    });

    function isAPIAvailable() {
      // Check for the various File API support.
      if (window.File && window.FileReader && window.FileList && window.Blob) {
        // Great success! All the File APIs are supported.
        return true;
      } else {
        // source: File API availability - http://caniuse.com/#feat=fileapi
        // source: <output> availability - http://html5doctor.com/the-output-element/
        document.writeln('The HTML5 APIs used in this form are only available in the following browsers:<br />');
        // 6.0 File API & 13.0 <output>
        document.writeln(' - Google Chrome: 13.0 or later<br />');
        // 3.6 File API & 6.0 <output>
        document.writeln(' - Mozilla Firefox: 6.0 or later<br />');
        // 10.0 File API & 10.0 <output>
        document.writeln(' - Internet Explorer: Not supported (partial support expected in 10.0)<br />');
        // ? File API & 5.1 <output>
        document.writeln(' - Safari: Not supported<br />');
        // ? File API & 9.2 <output>
        document.writeln(' - Opera: Not supported');
        return false;
      }
    }

    function handleFileSelect(evt) {
      var files = evt.target.files; // FileList object
      var file = files[0];

      // read the file metadata
      var output = ''
          output += '<span style="font-weight:bold;">' + escape(file.name) + '</span><br />\n';
          output += ' - FileType: ' + (file.type || 'n/a') + '<br />\n';
          output += ' - FileSize: ' + file.size + ' bytes<br />\n';
          output += ' - LastModified: ' + (file.lastModifiedDate ? file.lastModifiedDate.toLocaleDateString() : 'n/a') + '<br />\n';

      // read the file contents
      printTable(file);

      // post the results
      $('#list').append(output);
    }

    function printTable(file) {
      var reader = new FileReader();
      reader.readAsText(file);
      reader.onload = function(event){
        var csv = event.target.result;
        var data = $.csv.toArrays(csv);
        var ligne = -1;
        
        var html = '';
//         html += '<form method="POST" action="./../CreationEleve" class="form-horizontal">';
        for(var row in data) {
          ligne += 1;
          var colonne = -1;
          html += '<tr role="row">\r\n';
          for(var item in data[row]) {
        	  if (data[row][item] !== null){
            colonne += 1;
            html += '<td><input type="hidden" id="champ['+ligne+']['+colonne+']" name="champ['+ligne+']['+colonne+']" value="'+ data[row][item]+'"/>'+ data[row][item] +'</td>\r\n';
        	  }}
          html += '</tr>\r\n';
        }
        html += '<input type="hidden" id="ligne" name="ligne" value="'+ligne+'"/>';
        html += '<input type="hidden" id="colonne" name="colonne" value="'+colonne+'"/>';
        html += '<br /><br />';
//         html +='<input type="submit" class="btn btn-primary" value="Soumettre cette grille" />';
//         html += '</form>';
        $('#contents').html(html);
        $('.alert').show()
      };
      reader.onerror = function(){ alert('Unable to read ' + file.fileName); };
    }
  </script>
</body>

</html>