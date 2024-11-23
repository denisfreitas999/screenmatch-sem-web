package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosSerie {
	@JsonAlias("Title")
    private String titulo;

    @JsonAlias("imdbRating")
    private String avaliacao;

    @JsonAlias("totalSeasons")
    private Integer totalDeTemporadas;
	
	public DadosSerie() {
        // Construtor padrão necessário
    }

	public DadosSerie(String titulo, String avaliacao, Integer totalDeTemporadas) {
        this.titulo = titulo;
        this.avaliacao = avaliacao;
        this.totalDeTemporadas = totalDeTemporadas;
    }

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(String avaliacao) {
		this.avaliacao = avaliacao;
	}

	public Integer getTotalDeTemporadas() {
		return totalDeTemporadas;
	}

	public void setTotalDeTemporadas(Integer totalDeTemporadas) {
		this.totalDeTemporadas = totalDeTemporadas;
	}
	
	@Override
	public String toString() {
	    return "Dados da Serie: {" +
	            "titulo = '" + titulo + '\'' +
	            ", avaliacao = '" + avaliacao + '\'' +
	            ", totalDeTemporadas = " + totalDeTemporadas +
	            '}';
	}
}
