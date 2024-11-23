package br.com.alura.screenmatch.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosTemporada {
	@JsonAlias("Season")
	private Integer numero;

	@JsonAlias("Episodes")
	private List<DadosEpisodio> episodios;

	public DadosTemporada(Integer numero, List<DadosEpisodio> episodios) {
		this.numero = numero;
		this.episodios = episodios;
	}

	public DadosTemporada() {

	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public List<DadosEpisodio> getEpisodios() {
		return episodios;
	}

	public void setEpisodios(List<DadosEpisodio> episodios) {
		this.episodios = episodios;
	}

	@Override
	public String toString() {
		return "Dados da Temporada: \n"
				+ "{numero = " + numero + ", episodios = " + episodios + "}"
						+ "\n";
	}

}
