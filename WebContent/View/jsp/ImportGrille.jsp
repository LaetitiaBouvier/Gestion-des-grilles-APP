<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<title>Import Grille</title>
<link href="./../css/bootstrap.min.css" rel="stylesheet">
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
					<div class="container">
					<p>Merci de veiller à remplir le tableau de la façon suivante et d'utiliser l'entête présentée sur la première ligne de votre fichier .csv.</br></br></br>
					
  <img src="../ressources/tableauGrille.png" height="250px"/></br></br>
  Veillez à ne pas laisser de champ vide, au besoin, entrer un point "." dans les cases vides.</p>
</div>
<form method="POST" action="../../CreationEleveServlet" class="form-horizontal">

						<div id="dataTables_wrapper" class="dataTables_wrapper form-inline no-footer">
							<div class="row">
								<div class="col-md-4">
									<div id="dataTables_length" class="dataTables_length">
										<label>
											Show
											<select class="form-control input-sm" name="dataTables_length" aria-controls="dataTables">
												<option value="10">10</option>
												<option value="25">25</option>
												<option value="50">50</option>
												<option value="100">100</option>
											</select>
											entries
										</label>
									</div>
								</div>
								<div class="col-md-4">
									<div id="dataTables_filter" class="dataTables_filter">
										<label>
											Search:
											<input class="form-control input-sm" placeholder="" aria-controls="dataTables" type="search">
										</label>
									</div>
								</div>
								<div class="col-md-4">									
									<div id="inputs" class="clearfix">
							    		<input type="file" id="files" name="files[]" multiple />
							 		 </div>
							  		<hr />
							  		<output id="list">
							  		</output>
							  		<hr />
								</div>
							</div>
							
							<div class="row">
							  <div class="col-xs-6"></div>
							  <div class="col-xs-6">
							  
							  </div>
							</div>
							<table class="table table-striped table-bordered table-hover table-condensed" id="contents" style="width:100%; height:400px;">
			  				</table>
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
  <script src="./../js/jquery.csv.js"></script>
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
      };
      reader.onerror = function(){ alert('Unable to read ' + file.fileName); };
    }
  </script>
</body>

</html>