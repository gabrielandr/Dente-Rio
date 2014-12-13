package br.com.dentrio.movimento.service;

import java.util.List;

import br.com.dentrio.model.Movimento;

public interface MovimentoService {

	public void estornarMovimento(Integer movimentoId);

	public List<Movimento> listMovimentos();

	public Movimento getMovimento(Integer movimentoId);

	public Movimento getLastInsertedRecord();

}
