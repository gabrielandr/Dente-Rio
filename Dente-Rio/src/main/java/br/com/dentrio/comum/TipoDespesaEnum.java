package br.com.dentrio.comum;

import java.util.Arrays;
import java.util.List;

public enum TipoDespesaEnum {

	OUTROS(1, "Outros"), ORTODENTISTA(2, "Ortodentista");

	private Integer codigo;
	private String descricao;

	private TipoDespesaEnum(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public static List<TipoDespesaEnum> listaTodos() {
		return Arrays.asList(TipoDespesaEnum.values());
	}

	public static TipoDespesaEnum getByCode(Integer codigo) {
		TipoDespesaEnum[] itensEnum = TipoDespesaEnum.values();
		for (TipoDespesaEnum item : itensEnum) {
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
