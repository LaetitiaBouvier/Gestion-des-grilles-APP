
	function addInput(name,champ){

		var i=1;
	    	    	    
		var competence = document.createElement("input");
		competence.name = "nom_competence"+(i);
		competence.type = "text";
		champ.appendChild(competence);
		
				
		var coef = document.createElement("input");
		coef.name = "coef_competence"+(i);
		coef.type = "number";
		champ.appendChild(coef);
				
		var description = document.createElement("input");
		description.name = "desc_competence"+(i);
		description.type = "text";
		champ.appendChild(description);
		
		var i=i+1;
	}
	
	function addField(nom,champ) {
		document.getElementById('fields').innerHTML = "<div class='form-group'>" +
		addInput(nom,champ) +
		"</div>";
	}