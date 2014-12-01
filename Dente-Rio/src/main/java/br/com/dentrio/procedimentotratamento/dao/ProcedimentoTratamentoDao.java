package br.com.dentrio.procedimentotratamento.dao;

import java.util.List;

import br.com.dentrio.model.ProcedimentoTratamento;

public interface ProcedimentoTratamentoDao {

	public void salvarProcedimentoTratamento(ProcedimentoTratamento procedimentoTratamento);

	public void editarProcedimentoTratamento(ProcedimentoTratamento procedimentoTratamento);

	public List<ProcedimentoTratamento> listProcedimentoTratamentos();

	public ProcedimentoTratamento getProcedimentoTratamento(Integer procedimentoTratamentoId);

	public void deletarProcedimentoTratamento(ProcedimentoTratamento procedimentoTratamento);

}
