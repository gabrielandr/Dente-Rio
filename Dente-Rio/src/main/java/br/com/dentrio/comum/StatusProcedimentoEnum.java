package br.com.dentrio.comum;

import java.util.Arrays;
import java.util.List;

public enum StatusProcedimentoEnum {

	NAO_INICIADO(1, "Não Iniciado"), EM_ANDAMENTO(2, "Em Andamento"), FINALIZADO(3, "Finalizado");

	private Integer codigo;
	private String descricao;

	private StatusProcedimentoEnum(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public static List<StatusProcedimentoEnum> listaTodos() {
		return Arrays.asList(StatusProcedimentoEnum.values());
	}

	public static StatusProcedimentoEnum getByCode(Integer codigo) {
		StatusProcedimentoEnum[] itensEnum = StatusProcedimentoEnum.values();
		for (StatusProcedimentoEnum item : itensEnum) {
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
