function validaCampoNumerico(valor) {
	$('.numerico').each(
			function() {
				if ($(this).val() == "" || $(this).val() == -1
						|| $(this).val() == 0) {
					mensagens.push("O campo " + $(this).attr('title')
							+ " \u00E9 de preenchimento obrigat\u00F3rio.");
				}
			});

}

function validarFormTratamento() {
	var comboFuncionarios = $(".comboFuncionarios option:selected").val();
	var dataInicio = PF('dataInicio').getDate();
	var procedimentos = $(".procedimentosTratamentoTable [type=checkbox]:checked").length;

	if (comboFuncionarios == "" && dataInicio == null && procedimentos == 0) {
		comboFuncionariosButton();
		campoDatainicio();
		campoProcedimentos();
	} else if (comboFuncionarios == "") {
		comboFuncionariosButton();
	} else if (dataInicio == null) {
		campoDatainicio();
	} else if (procedimentos == 0) {
		campoProcedimentos();
	} else {
		$("#formTratamento\\:cadastroTabView\\:botaoSalvar").click();
	}
}

function validarFormProcedimentosTratamento() {
	var procedimentos = $(".procedimentosTratamentoTable [type=checkbox]:checked").length;

	if (procedimentos == 0) {
		campoProcedimentos();
	} else {
		$("#formulario\\:dataTable\\:botaoSalvar").click();
	}
}

$(function() {
	$('.valorPagamento').maskMoney({
		prefix : 'R$ ',
		// affixesStay:false,
		thousands : '.',
		decimal : ',',
		allowZero : true,
		precision : 2,
	});
})

function limpaValor() {
	if ($('.valorPagamento').val() == 'R$ 0,00') {
		$('.valorPagamento').val(" ");
	}
}

function preencheValorTotal() {
	var restante = "R$ "
			+ $('#formulario\\:formPagamento\\:hiddenValorRestante').val();
	restante = restante.replace(".", ",");
	$('#formulario\\:formPagamento\\:valorPagamento').val(restante);
}

function resetFormPagamento() {
	$("#formulario\\:valorPagamento").val("R$ 0,00");
}

function trataCampoSocio() {
	if ($('.tiposFuncionario option:selected').val() == 'DENTISTA') {
		$('.trDentista').show();
	} else {
		$('.trDentista').hide();
	}
}

function trataCampoFormaPagamento() {
	if ($("#formulario\\:formaPagamento option:selected").val() == 'CARTAO') {
		$("#trNumeroParcelas").show();
		$("#trCodigoEstorno").show();
	} else {
		$("#trNumeroParcelas").hide();
		$("#trCodigoEstorno").hide();
	}
}