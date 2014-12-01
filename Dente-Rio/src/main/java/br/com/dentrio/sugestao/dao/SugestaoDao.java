package br.com.dentrio.sugestao.dao;

import java.util.List;

import br.com.dentrio.model.Sugestao;

public interface SugestaoDao {

	public void addSugestao(Sugestao sugestao);

	public List<Sugestao> listSugestaos();

	public Sugestao getSugestao(Integer sugestaoId);

	public void deletarSugestao(Sugestao sugestao);

}
