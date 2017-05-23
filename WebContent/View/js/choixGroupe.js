
/**
 * toggle on load
 */
$(document).ready(function() {
	$("[data-toggleStudent-element]").slideToggle();
	$('[data-toggle-element-promid]').hide();
	$('[data-toggle-element2-promid]').hide(); 
	
 });

/**
 * function to toggle student
 */
$("[data-toggleStudent]").click(function(){
	console.log($(this).data('groupeid'));
	$(this).parent().parent().children("[data-toggleStudent-element]").slideToggle();
	$(this).toggleClass('glyphicon-triangle-bottom glyphicon-menu-right');
}); 

//function hide(groupe)
//{
//$	normalement il reste à repasser en unselectedc
//}


function toggleGroup(div, prom)
{
	div.each(function(){
		if($(this).data('toggle-element2-promid')==prom)
		{
			$(this).slideToggle();
		}
	});
}

/**
 * function to toggle group
 */
$("[data-groupeid]").click(function(){   
//	for each
	toggleGroup($('[data-toggle-element-groupeid="'+$(this).data('groupeid')+'"]'), $(this).parent().parent().data('toggle-element-promid'));
	$(this).toggleClass('selected unselected');
}); 


/**
 * function to toggle prom
 */
$("[data-promid]").click(function(){    
	$(this).toggleClass('selected unselected');
	$('[data-toggle-element-promid="'+$(this).data('promid')+'"]').slideToggle(); 
	$('[data-toggle-element-promid="'+$(this).data('promid')+'"]').children().find(".selected").toggleClass('selected unselected');
	$('[data-toggle-element2-promid="'+$(this).data('promid')+'"]').hide();
}); 
