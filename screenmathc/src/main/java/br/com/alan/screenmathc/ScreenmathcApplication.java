package br.com.alan.screenmathc;

import br.com.alan.screenmathc.model.DadosEpisodio;
import br.com.alan.screenmathc.model.DadosSerie;
import br.com.alan.screenmathc.model.DadosTemporada;
import br.com.alan.screenmathc.principal.Principal;
import br.com.alan.screenmathc.repository.SerieRepository;
import br.com.alan.screenmathc.servece.ConsumoApi;
import br.com.alan.screenmathc.servece.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmathcApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmathcApplication.class, args);
	}

}
