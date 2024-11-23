package br.com.alura.screenmatch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.screenmatch.principal.Principal;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("**************************");
		System.out.println("Primeiro projeto spring sem WEB.");
		System.out.println("**************************");
		Principal principal = new Principal();
		principal.exibeMenu();
		System.out.println("\n**************************\n");
	}

}
