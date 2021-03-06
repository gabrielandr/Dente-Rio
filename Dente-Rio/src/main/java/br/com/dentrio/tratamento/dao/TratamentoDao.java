package br.com.dentrio.tratamento.dao;

import java.util.List;

import br.com.dentrio.model.Tratamento;

public interface TratamentoDao {

	public void salvarTratamento(Tratamento tratamento);

	public void editarTratamento(Tratamento tratamento);

	public List<Tratamento> listTratamentos();

	public Tratamento getTratamento(Integer tratamentoId);

	public void deletarTratamento(Tratamento tratamento);

	public Tratamento getLastInsertedRecord();

}
