package br.com.dentrio.sugestao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dentrio.model.Sugestao;
import br.com.dentrio.sugestao.dao.SugestaoDao;

@Service
public class SugestaoServiceImpl implements SugestaoService {

	@Autowired
	public SugestaoDao sugestaoDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addSugestao(Sugestao sugestao) {
		sugestaoDao.addSugestao(sugestao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Sugestao> listSugestoes() {
		return sugestaoDao.listSugestaos();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Sugestao getSugestao(Integer sugestaoId) {
		return sugestaoDao.getSugestao(sugestaoId);
	}

	//
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deletarSugestao(Sugestao sugestao) {
		sugestaoDao.deletarSugestao(sugestao);

	}

	public SugestaoDao getSugestaoDao() {
		return sugestaoDao;
	}

	public void setSugestaoDao(SugestaoDao sugestaoDao) {
		this.sugestaoDao = sugestaoDao;
	}

}
