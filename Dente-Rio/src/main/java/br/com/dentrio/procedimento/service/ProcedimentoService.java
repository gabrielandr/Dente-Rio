package br.com.dentrio.procedimento.service;

import java.util.List;

import br.com.dentrio.model.Procedimento;

public interface ProcedimentoService {

	public void salvarProcedimento(Procedimento area);

	public List<Procedimento> listProcedimentos();

	public Procedimento getProcedimento(Integer areaId);

	public void deletarProcedimento(Procedimento area);

}
