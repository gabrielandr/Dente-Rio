$(document).ready(function() {
	escDialog();
	
	showHideMenusTopo('hide');
	
	PrimeFaces.locales['pt_BR'] = {  
            closeText: 'Fechar',  
            prevText: 'Anterior',  
            nextText: 'Próximo',  
            currentText: 'Começo',  
            monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],  
            monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun', 'Jul','Ago','Set','Out','Nov','Dez'],  
            dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],  
            dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb'],  
            dayNamesMin: ['D','S','T','Q','Q','S','S'],  
            weekHeader: 'Semana',  
            firstDay: 1,  
            isRTL: false,  
            showMonthAfterYear: false,  
            yearSuffix: '',  
            timeOnlyTitle: 'Só Horas',  
            timeText: 'Tempo',  
            hourText: 'Hora',  
            minuteText: 'Minuto',  
            secondText: 'Segundo',  
            currentText: 'Data Atual',  
            ampm: false,  
            month: 'Mês',  
            week: 'Semana',  
            day: 'Dia',  
            allDayText : 'Todo Dia'  
        };  
});

function showHideMenusTopo(acao){
	if($("#hiddenCurrentUser").val() == ''){
		$("#liPaciente").hide();
		$("#liMovimento").hide();
		$("#liCadastros").hide();
		$("#liRelatorios").hide();
//		$("#liContato").hide();
	} else {
		$("#liPaciente").show();
		$("#liMovimento").show();
		$("#liCadastros").show();
		$("#liRelatorios").show();
//		$("#liContato").show();
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
	if ($('#formFuncionario\\:tiposFuncionario option:selected').val() == 'DENTISTA') {
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

function limparFormLoginPagamento(){
	$("#formulario\\:pagamentosTableTratamento\\:0\\:login").val("");
	$("#formulario\\:pagamentosTableTratamento\\:0\\:password").val("");
}
function limparFormLogin(){
	$("#formLogin\\:login").val("");
	$("#formLogin\\:password").val("");
}