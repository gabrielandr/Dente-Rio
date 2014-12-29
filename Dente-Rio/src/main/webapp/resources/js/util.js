$(document).ready(function() {
	escDialog();
	
	showHideMenusTopo('hide')
});

function showHideMenusTopo(acao){
	if($("#hiddenCurrentUser").val() == ''){
		$("#liPaciente").hide();
		$("#liMovimento").hide();
		$("#liCadastros").hide();
		$("#liRelatorios").hide();
		$("#liContato").hide();
	} else {
		$("#liPaciente").show();
		$("#liMovimento").show();
		$("#liCadastros").show();
		$("#liRelatorios").show();
		$("#liContato").show();
	}
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

function preencheRestanteValorPagamento() {
	var restante = "R$ " + $('#formulario\\:hiddenValorRestante').val();
	restante = restante.replace(".", ",");
	$('#formulario\\:valorPagamento').val(restante);
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

function escDialog() {
	$(document).keyup(function(e) {
		if (e.keyCode == 27) { // esc code is 27
			closeAllDialog();
		}
	});
}

function closeAllDialog() {
	for ( var propertyName in PrimeFaces.widgets) {
		if (PrimeFaces.widgets[propertyName] instanceof PrimeFaces.widget.Dialog
				|| PrimeFaces.widgets[propertyName] instanceof PrimeFaces.widget.LightBox) {
			PrimeFaces.widgets[propertyName].hide();
		}
	}
}