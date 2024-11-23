package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosEpisodio {
	@JsonAlias("Title")
	private String titulo;

	@JsonAlias("Episode")
	private Integer numero;

	@JsonAlias("imdbRating")
	private String avaliacao;

	@JsonAlias("Released")
	private String dataDeLancamento;

	public DadosEpisodio(String titulo, Integer numero, String avaliacao, String dataDeLancamento) {
		this.titulo = titulo;
		this.numero = numero;
		this.avaliacao = avaliacao;
		this.dataDeLancamento = dataDeLancamento;
	}

	public DadosEpisodio() {

	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(String avaliacao) {
		this.avaliacao = avaliacao;
	}

	public String getDataDeLancamento() {
		return dataDeLancamento;
	}

	public void setDataDeLancamento(String dataDeLancamento) {
		this.dataDeLancamento = dataDeLancamento;
	}

	@Override
	public String toString() {
		return "Dados do Episódio: {titulo = " + titulo + ", numero = " + numero + ", avaliacao = " + avaliacao
				+ ", dataDeLancamento = " + dataDeLancamento + "}";
	}

}
