package br.com.dentrio.comum;

import java.util.Arrays;
import java.util.List;

public enum FormaPagamentoEnum {

	DINHEIRO(1, "Dinheiro"), CARTAO(2, "Cartão");

    private Integer codigo;
    private String descricao;

    private FormaPagamentoEnum(Integer codigo, String descricao) {
	this.codigo = codigo;
	this.descricao = descricao;
    }

    public static List<FormaPagamentoEnum> listaTodos() {
	return Arrays.asList(FormaPagamentoEnum.values());
    }

    public static FormaPagamentoEnum getByCode(Integer codigo) {
	FormaPagamentoEnum[] itensEnum = FormaPagamentoEnum.values();
	for (FormaPagamentoEnum item : itensEnum) {
	    if (item.getCodigo().equals(codigo)) {
		return item;
	    }
	}
	return null;
    }

    public Integer getCodigo() {
	return codigo;
    }

    public void setCodigo(Integer codigo) {
	this.codigo = codigo;
    }

    public String getDescricao() {
	return descricao;
    }

    public void setDescricao(String descricao) {
	this.descricao = descricao;
    }

}
