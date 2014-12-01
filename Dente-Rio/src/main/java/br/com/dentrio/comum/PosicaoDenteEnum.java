package br.com.dentrio.comum;

import java.util.Arrays;
import java.util.List;

public enum PosicaoDenteEnum {

	INCISIVO_CENTRAL_SUPERIOR_ESQUERDO(11, "Incisivo Central Superior Esquerdo"), INCISIVO_LATERAL_SUPERIOR_ESQUERDO(
			12, "Incisivo Lateral Superior Esquerdo"), CANINO_SUPERIOR_ESQUERDO(13, "Canino Superior Esquerdo"), PRIMEIRO_PREMOLAR_SUPERIOR_ESQUERDO(
			14, "Primeiro Pré-Molar Superior Esquerdo"), SEGUNDO_PREMOLAR_SUPERIOR_ESQUERDO(15,
			"Segundo Pré-Molar Superior Esquerdo"), PRIMEIRO_MOLAR_SUPERIOR_ESQUERDO(16,
			"Primeiro Molar Superior Esquerdo"), SEGUNDO_MOLAR_SUPERIOR_ESQUERDO(17, "Segundo Molar Superior Esquerdo"), TERCEIRO_MOLAR_SUPERIOR_ESQUERDO(
			18, "Terceiro Molar Superior Esquerdo"),

	INCISIVO_CENTRAL_SUPERIOR_DIREITO(21, "Incisivo Central Superior Direito"), INCISIVO_LATERAL_SUPERIOR_DIREITO(22,
			"Incisivo Lateral Superior Direito"), CANINO_SUPERIOR_DIREITO(23, "Canino Superior Direito"), PRIMEIRO_PREMOLAR_SUPERIOR_DIREITO(
			24, "Primeiro Pré-Molar Superior Direito"), SEGUNDO_PREMOLAR_SUPERIOR_DIREITO(25,
			"Segundo Pré-Molar Superior Direito"), PRIMEIRO_MOLAR_SUPERIOR_DIREITO(26,
			"Primeiro Molar Superior Direito"), SEGUNDO_MOLAR_SUPERIOR_DIREITO(27, "Segundo Molar Superior Direito"), TERCEIRO_MOLAR_SUPERIOR_DIREITO(
			28, "Terceiro Molar Superior Direito"),

	INCISIVO_CENTRAL_INFERIOR_ESQUERDO(31, "Incisivo Central Inferior Esquerdo"), INCISIVO_LATERAL_INFERIOR_ESQUERDO(
			32, "Incisivo Lateral Inferior Esquerdo"), CANINO_INFERIOR_ESQUERDO(33, "Canino Inferior Esquerdo"), PRIMEIRO_PREMOLAR_INFERIOR_ESQUERDO(
			34, "Primeiro Pré-Molar Inferior Esquerdo"), SEGUNDO_PREMOLAR_INFERIOR_ESQUERDO(35,
			"Segundo Pré-Molar Inferior Esquerdo"), PRIMEIRO_MOLAR_INFERIOR_ESQUERDO(36,
			"Primeiro Molar Inferior Esquerdo"), SEGUNDO_MOLAR_INFERIOR_ESQUERDO(37, "Segundo Molar Inferior Esquerdo"), TERCEIRO_MOLAR_INFERIOR_ESQUERDO(
			38, "Terceiro Molar Inferior Esquerdo"),

	INCISIVO_CENTRAL_INFERIOR_DIREITO(41, "Incisivo Central Inferior Direito"), INCISIVO_LATERAL_INFERIOR_DIREITO(42,
			"Incisivo Lateral Inferior Direito"), CANINO_INFERIOR_DIREITO(43, "Canino Inferior Direito"), PRIMEIRO_PREMOLAR_INFERIOR_DIREITO(
			44, "Primeiro Pré-Molar Inferior Direito"), SEGUNDO_PREMOLAR_INFERIOR_DIREITO(45,
			"Segundo Pré-Molar Inferior Direito"), PRIMEIRO_MOLAR_INFERIOR_DIREITO(46,
			"Primeiro Molar Inferior Direito"), SEGUNDO_MOLAR_INFERIOR_DIREITO(47, "Segundo Molar Inferior Direito"), TERCEIRO_MOLAR_INFERIOR_DIREITO(
			48, "Terceiro Molar Inferior Direito");

	private Integer codigo;
	private String descricao;

	private PosicaoDenteEnum(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public static List<PosicaoDenteEnum> listaTodos() {
		return Arrays.asList(PosicaoDenteEnum.values());
	}

	public static PosicaoDenteEnum getByCode(Integer codigo) {
		PosicaoDenteEnum[] itensEnum = PosicaoDenteEnum.values();
		for (PosicaoDenteEnum item : itensEnum) {
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
