package br.com.dentrio.comum;

import java.util.Arrays;
import java.util.List;

public enum RolesEnum {

	ADMIN(1, "Admin"),
	SOCIO(2, "Sócio"),
	DENTISTA(3, "Dentista"),
	SECRETARIA(4, "Secretária");

	private Integer codigo;
	private String descricao;

	private RolesEnum(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public static List<RolesEnum> listaTodos() {
		return Arrays.asList(RolesEnum.values());
	}

	public static RolesEnum getByCode(Integer codigo) {
		RolesEnum[] itensEnum = RolesEnum.values();
		for (RolesEnum item : itensEnum) {
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
