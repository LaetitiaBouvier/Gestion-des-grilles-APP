
	function addInputCompetence(field){

		var i=2;
	    	    	    
		var competence = document.createElement("input");
		competence.name = "nom_competence"+(i);
		competence.type = "text";
		competence.placeholder = "compétence";
		competence.id = "nom_competence";
		field.appendChild(competence);
				
		var i=i+1;
	}
	
	function addInputCoef(field){

		var i=1;
	    	    	    
		var competence = document.createElement("input");
		competence.name = "nom_competence"+(i);
		competence.type = "text";
		competence.placeholder = "compétence";
		competence.id = "nom_competence";
		field.appendChild(competence);
		
				
		var coef = document.createElement("input");
		coef.name = "coef_competence"+(i);
		coef.type = "number";
		coef.placeholder = "coefficient";
		field.appendChild(coef);
				
		var description = document.createElement("input");
		description.name = "desc_competence"+(i);
		description.type = "text";
		description.placeholder = "description";
		field.appendChild(description);
		
		var i=i+1;
	}
	
	function addField(field) {
		var j=2;
		var f=document.getElementById(field);
		f.setAttribute("class","form-group");
	//	f.setAttribute("id","field"+j);
		//innerHTML = "<div class='form-group' id='field_"+(j)+"'>" +
		console.log(f);
		addInputCompetence(f);
		//+"</div>";
		var j=j+1;
	}