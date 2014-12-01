package br.com.dentrio.procedimento.dao;

import java.util.List;

import br.com.dentrio.model.Procedimento;

public interface ProcedimentoDao {

	public void salvarProcedimento(Procedimento area);

	public List<Procedimento> listProcedimentos();

	public Procedimento getProcedimento(Integer areaId);

	public void deletarProcedimento(Procedimento area);
}
