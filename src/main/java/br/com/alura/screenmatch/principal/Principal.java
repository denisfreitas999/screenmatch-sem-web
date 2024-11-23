package br.com.alura.screenmatch.principal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;

public class Principal {

	private Scanner leitor = new Scanner(System.in);
	private final String BASE_URL = "http://www.omdbapi.com/?t=";
	private final String API_KEY = "&apikey=a1dd25c";
	private ConsumoAPI consumoAPI = new ConsumoAPI();
	private ConverteDados conversor = new ConverteDados();

	public void exibeMenu() {
		System.out.println("Digite nome da série para consulta");
		String nomeSerie = leitor.nextLine();
		nomeSerie = nomeSerie.replace(" ", "+");
		String json = consumoAPI.obterDados(BASE_URL + nomeSerie + API_KEY);
		DadosSerie dadosSerie = conversor.obterDados(json, DadosSerie.class);
		System.out.println(dadosSerie);

		List<DadosTemporada> temporadas = new ArrayList<>();
		for (int i = 1; i < dadosSerie.getTotalDeTemporadas(); i++) {
			String endereco3 = BASE_URL + nomeSerie + "&season=" + i + API_KEY;
			json = consumoAPI.obterDados(endereco3);
			DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}

		temporadas.forEach(System.out::println);

		System.out.println("\n--------------------------------------\n");

		temporadas.forEach(t -> t.getEpisodios().forEach(e -> System.out.println(e.getTitulo())));

		System.out.println("\n--------------------------------------\n");

		List<DadosEpisodio> dadosEpisodios = temporadas.stream().flatMap(t -> t.getEpisodios().stream())
				.collect(Collectors.toList());

		System.out.println(dadosEpisodios);

		System.out.println("\n####################################\n");

//		System.out.println("\nTop 10 episódios da série");
//		dadosEpisodios.stream()
//				.filter(e -> !e.getAvaliacao().equalsIgnoreCase("N/A"))
//				.peek(e -> System.out.println("Primeiro Filtro(N/A) " + e))
//				.sorted(Comparator.comparing(DadosEpisodio::getAvaliacao).reversed())
//				.peek(e -> System.out.println("Ordenação " + e))
//				.limit(10)
//				.peek(e -> System.out.println("Limite " + e))
//				.map(e -> e.getTitulo().toUpperCase())
//				.peek(e -> System.out.println("Mapeamento " + e))
//				.forEach(System.out::println);

		System.out.println("\n####################################\n");

		List<Episodio> episodios = temporadas.stream()
				.flatMap(t -> t.getEpisodios().stream().map(d -> new Episodio(t.getNumero(), d)))
				.collect(Collectors.toList());

		episodios.forEach(System.out::println);

//		System.out.println("Digite um trecho do título do episódio");
//		String trechoTitulo = leitor.nextLine();
//		Optional<Episodio> episodioBuscado = episodios.stream()
//				.filter(e -> e.getTitulo().toUpperCase().contains(trechoTitulo.toUpperCase())).findFirst();
//
//		if (episodioBuscado.isPresent()) {
//			System.out.println("Episodio encontrado!");
//			System.out.println("Episodio:" + episodioBuscado.get());
//		} else {
//			System.out.println("Episodio não encontrado.");
//		}

//		System.out.println("A partir de que ano você deseja ver os episódios?");
//		int ano = leitor.nextInt();
//		leitor.nextLine();
//
//		LocalDate dataBusca = LocalDate.of(ano, 1, 1);
//		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//		
//		System.out.println("\n####################################\n");
//		
//		episodios.stream().filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
//				.forEach(e -> System.out.println("Temporada: " + e.getTemporada() + " " + "Episodio: "
//						+ e.getTitulo() + " " + "Data de Lançamento: " + e.getDataLancamento().format(formatador)));

		Map<Integer, Double> avaliacoesPorTemporada = episodios.stream()
				.filter(e -> e.getAvaliacao() > 0.0)
				.collect(Collectors.groupingBy(Episodio::getTemporada, Collectors.averagingDouble(Episodio::getAvaliacao)));
		System.out.println(avaliacoesPorTemporada);
		
		DoubleSummaryStatistics est = episodios.stream()
				.filter(e -> e.getAvaliacao() > 0.0)
				.collect(Collectors.summarizingDouble(Episodio::getAvaliacao));
		
		System.out.println(est);
	}
}
