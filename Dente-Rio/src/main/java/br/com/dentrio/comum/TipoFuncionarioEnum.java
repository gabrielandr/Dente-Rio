package br.com.dentrio.comum;

import java.util.Arrays;
import java.util.List;

public enum TipoFuncionarioEnum {

    SECRETARIA(1, "Secretária"), DENTISTA(2, "Dentista");

    private Integer codigo;
    private String descricao;

    private TipoFuncionarioEnum(Integer codigo, String descricao) {
	this.codigo = codigo;
	this.descricao = descricao;
    }

    public static List<TipoFuncionarioEnum> listaTodos() {
	return Arrays.asList(TipoFuncionarioEnum.values());
    }

    public static TipoFuncionarioEnum getByCode(Integer codigo) {
	TipoFuncionarioEnum[] itensEnum = TipoFuncionarioEnum.values();
	for (TipoFuncionarioEnum item : itensEnum) {
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
