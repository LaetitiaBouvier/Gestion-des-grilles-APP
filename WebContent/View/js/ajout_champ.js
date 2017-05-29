	/*function addLabelCompetence(field,i) {

		var labelcompetence = document.createElement("label");
		labelcompetence.setAttribute("for", "nom_grille");
		labelcompetence.setAttribute("class", "col-sm-2 control-label");
		labelcompetence.id = "labelcompetence";
		field.appendChild(labelcompetence);
	}*/
	
	function addInputCompetence(field,i){

		var competence = document.createElement("input");
		competence.name = "nom_competence"+(i);
		competence.type = "text";
		competence.placeholder = "compétence";
		competence.id = "nom_competence";
		field.appendChild(competence);
			
	}
	
	function addInputCoef(field,i){

		var coef = document.createElement("input");
		coef.name = "coef_competence"+(i);
		coef.type = "number";
		coef.placeholder = "coefficient";
		coef.id = "coefficient";
		field.appendChild(coef);
		
	}
	function addInputDescription(field,i){

		var description = document.createElement("input");
		description.name = "desc_competence"+(i);
		description.type = "text";
		description.placeholder = "description";
		description.id = "description";
		field.appendChild(description);
		
	}
	
	function addField(field,i) {
		
		var f=document.getElementById(field);
		f.setAttribute("class","form-group col-sm-6");
		
		var lc=document.getElementById(field);
		
		//console.log(f);
		addInputCompetence(f,i);
		
		addInputCoef(f,i);
		addInputDescription(f,i);
		i=i+1;
		
	}