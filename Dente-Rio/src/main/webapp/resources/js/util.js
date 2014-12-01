function validaCampoNumerico(valor){
	$('.numerico').each(function() {
		if($(this).val() == "" || $(this).val() == -1 || $(this).val() == 0) {
			mensagens.push("O campo "+ $(this).attr('title') + " \u00E9 de preenchimento obrigat\u00F3rio.");
		}
	});
	
}