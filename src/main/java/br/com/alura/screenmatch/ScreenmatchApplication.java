package br.com.alura.screenmatch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Primeiro projeto spring sem WEB.");
		String baseUrl = "http://www.omdbapi.com/";
		String apikey = "a1dd25c";
		String titulo = "The Chosen";
		
		String endereco = baseUrl + "?t=" + titulo.replace(" ", "+") + "&apikey=" + apikey;
		
		
		ConsumoAPI consumoAPI = new ConsumoAPI();
		String json = consumoAPI.obterDados(endereco);
		System.out.println(json);
		
		ConverteDados conversor = new ConverteDados();
		DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
		
		System.out.println(dados);
	}

}
