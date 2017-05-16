
/**
 * toggle on load
 */
$(document).ready(function() {
	$("[data-toggleStudent-element]").slideToggle();
	$('[data-toggle-element-promid]').hide(); 
	$('[data-toggle-element-groupeid]').hide(); 
 });

/**
 * function to toggle student
 */
$("[data-toggleStudent]").click(function(){
//	console.log($(this).data('groupeid'));
	$(this).parent().parent().children("[data-toggleStudent-element]").slideToggle();
	$(this).toggleClass('glyphicon-triangle-bottom glyphicon-menu-right');
}); 

/**
 * function to toggle group
 */
$("[data-groupeid]").click(function(){    
	$('[data-toggle-element-groupeid="'+$(this).data('groupeid')+'"]').slideToggle();    
	$(this).toggleClass('selected unselected');
}); 

/**
 * function to toggle prom
 */
$("[data-promid]").click(function(){    
	$(this).toggleClass('selected unselected');
	$('[data-toggle-element-promid="'+$(this).data('promid')+'"]').slideToggle(); 
	$('[data-toggle-element2-promid="'+$(this).data('promid')+'"]').hide(); 
}); 
