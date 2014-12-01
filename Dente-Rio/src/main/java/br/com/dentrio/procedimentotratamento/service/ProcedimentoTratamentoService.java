package br.com.dentrio.procedimentotratamento.service;

import java.util.List;

import br.com.dentrio.model.ProcedimentoTratamento;

public interface ProcedimentoTratamentoService {

	public void salvarProcedimentoTratamento(ProcedimentoTratamento tratamento);

	public void editarProcedimentoTratamento(ProcedimentoTratamento tratamento);

	public List<ProcedimentoTratamento> listProcedimentoTratamentos();

	public ProcedimentoTratamento getProcedimentoTratamento(Integer tratamentoId);

	public void deletarProcedimentoTratamento(ProcedimentoTratamento tratamento);

}
