package br.com.dentrio.comum;

import java.util.Arrays;
import java.util.List;

public enum TiposOrcamentoEnum {

	ORCAMENTO(1, "Orçamento"),
	ORCAMENTO_CONTRATADO(2, "Orçamento Contratado"),
	FICHA_LIQUIDADA(3, "Ficha Liquidada"),
	ORCAMENTO_CONTRATADO_FICHA_LIQUIDADA(
			4, "Orçamento Contratado/Ficha Liquidada");
	// TODO colocar demais status do tratamento ou da ficha... sao status
	// diferentes.

	private Integer codigo;
	private String descricao;

	private TiposOrcamentoEnum(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public static List<TiposOrcamentoEnum> listaTodos() {
		return Arrays.asList(TiposOrcamentoEnum.values());
	}

	public static TiposOrcamentoEnum getByCode(Integer codigo) {
		TiposOrcamentoEnum[] itensEnum = TiposOrcamentoEnum.values();
		for (TiposOrcamentoEnum item : itensEnum) {
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
