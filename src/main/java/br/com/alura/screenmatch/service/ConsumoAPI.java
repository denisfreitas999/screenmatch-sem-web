package br.com.alura.screenmatch.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {

	public String obterDados(String endereco) {
		HttpClient client = HttpClient.newHttpClient();
		
		URI uri = URI.create(endereco);
		HttpRequest request = HttpRequest.newBuilder()
				.uri(uri).build();
		
		HttpResponse<String> response = null;
		
		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			System.out.println("Error: " + e);
			System.out.println("Message: " + e.getMessage());
		}
		
		String json = response.body();
		
		return json;
	}
}
