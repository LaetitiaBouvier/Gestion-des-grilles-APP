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
$("[data-toggle]").click(function(){
	
	console.log($(this).data('groupeid'));
    
    $('[data-toggle-element-groupeid="'+$(this).data('groupeid')+'"]').slideToggle();
    
}); 
//
//
///**
// * function to toggle prom
// */
//$("[data-toggle]").click(function(){
//	
//	console.log($(this).data('groupeid'));
//    
//    $(this).parent().parent().children("[data-toggle-element]").slideToggle();
//    
//    $('[data-toggle-element-groupeid="'+$(this).data('groupeid')+'"]').slideToggle();
//    
//}); 