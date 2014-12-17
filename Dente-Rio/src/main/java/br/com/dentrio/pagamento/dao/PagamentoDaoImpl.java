package br.com.dentrio.pagamento.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.dentrio.comum.FormaPagamentoEnum;
import br.com.dentrio.model.Pagamento;

@Repository
@Transactional
public class PagamentoDaoImpl implements PagamentoDao, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void salvarPagamento(Pagamento pagamento) {
		getSessionFactory().getCurrentSession().merge(pagamento);
		getSessionFactory().getCurrentSession().flush();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Pagamento> listPagamentos() {
		return getSessionFactory().getCurrentSession().createCriteria(Pagamento.class).list();
	}

	@Override
	public Pagamento getPagamento(Integer pagamentoId) {
		return (Pagamento) getSessionFactory().getCurrentSession().get(Pagamento.class, pagamentoId);
	}

	@Override
	public void deletarPagamento(Pagamento pagamento) {
		getSessionFactory().getCurrentSession().delete(pagamento);
		getSessionFactory().getCurrentSession().flush();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Pagamento getLastInsertedRecord() {
		return (Pagamento) getSessionFactory().getCurrentSession().createQuery("from Pagamento ORDER BY id DESC")
				.setMaxResults(1).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pagamento> listarPagamentosDinheiro() {
		return getSessionFactory()
				.getCurrentSession()
				.createQuery(
						"SELECT pag from Pagamento pag where pag.formaPagamento = :formaPagamento and DATE(pag.createdAt) = CURDATE()")
						.setParameter("formaPagamento", FormaPagamentoEnum.DINHEIRO).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pagamento> listarPagamentosCartao() {
		return getSessionFactory()
				.getCurrentSession()
				.createQuery(
						"SELECT pag from Pagamento pag WHERE pag.formaPagamento = :formaPagamento and DATE(pag.createdAt) = CURDATE() AND estornado = false AND pagamento_estornado IS NULL")
						.setParameter("formaPagamento", FormaPagamentoEnum.CARTAO).list();
	}
}
