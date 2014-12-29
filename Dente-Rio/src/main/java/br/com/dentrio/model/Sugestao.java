package br.com.dentrio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SUGESTAO")
public class Sugestao extends Timestampable implements Serializable {

	private static final long serialVersionUID = -5988625296699742692L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String titulo;
	private String autor;

	@Column(name="sugestao", columnDefinition = "TEXT")
	private String sugestao;

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo
	 *            the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return the sugestao
	 */
	public String getSugestao() {
		return sugestao;
	}

	/**
	 * @param sugestao
	 *            the sugestao to set
	 */
	public void setSugestao(String sugestao) {
		this.sugestao = sugestao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (autor == null ? 0 : autor.hashCode());
		result = prime * result + (id == null ? 0 : id.hashCode());
		result = prime * result + (sugestao == null ? 0 : sugestao.hashCode());
		result = prime * result + (titulo == null ? 0 : titulo.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Sugestao)) {
			return false;
		}
		Sugestao other = (Sugestao) obj;
		if (autor == null) {
			if (other.autor != null) {
				return false;
			}
		} else if (!autor.equals(other.autor)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (sugestao == null) {
			if (other.sugestao != null) {
				return false;
			}
		} else if (!sugestao.equals(other.sugestao)) {
			return false;
		}
		if (titulo == null) {
			if (other.titulo != null) {
				return false;
			}
		} else if (!titulo.equals(other.titulo)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the autor
	 */
	public String getAutor() {
		return autor;
	}

	/**
	 * @param autor
	 *            the autor to set
	 */
	public void setAutor(String autor) {
		this.autor = autor;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

}