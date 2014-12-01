package br.com.dentrio.sugestao.service;

import java.util.List;

import br.com.dentrio.model.Sugestao;

public interface SugestaoService {

	public void addSugestao(Sugestao sugestao);

	public List<Sugestao> listSugestoes();

	public Sugestao getSugestao(Integer sugestaoId);

	public void deletarSugestao(Sugestao sugestao);

}
