package br.com.dentrio.movimento.dao;

import java.util.List;

import br.com.dentrio.model.Movimento;

public interface MovimentoDao {

	public void estornarMovimento(Integer movimentoId);

	public List<Movimento> listMovimentos();

	public Movimento getMovimento(Integer movimentoId);

	public void deletarMovimento(Movimento movimento);

	public Movimento getLastInsertedRecord();

}
