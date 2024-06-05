package br.com.alan.screenmathc.servece;

import br.com.alan.screenmathc.dto.EpisodioDto;
import br.com.alan.screenmathc.dto.SerieDto;
import br.com.alan.screenmathc.model.Categoria;
import br.com.alan.screenmathc.model.Serie;
import br.com.alan.screenmathc.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SerieServece {
    @Autowired
    private SerieRepository repository;



    public List<SerieDto> obterTodasAsSeries() {
        return converteDados(repository.findAll());
    }

    public List<SerieDto> obterTop5Serie() {
        return converteDados(repository.findTop5ByOrderByAvaliacaoDesc());
    }

    private List<SerieDto> converteDados(List<Serie> series){
        return series.stream()
                .map(s -> new SerieDto(s.getId(), s.getTitulo(), s.getTotalTemporadas(),
                        s.getAvaliacao(), s.getGenero(), s.getAtores(), s.getPoster(), s.getSinopse()))
                .collect(Collectors.toList());
    }

    public List<SerieDto> obterLancamentos() {
        return converteDados(repository.lancamentoMaisRecentes());
    }

    public SerieDto obterPorId(Long id) {
        Optional<Serie> serie = repository.findById(id);

        if (serie.isPresent()){
            Serie s = serie.get();
            return new SerieDto(s.getId(), s.getTitulo(), s.getTotalTemporadas(),
                    s.getAvaliacao(), s.getGenero(), s.getAtores(), s.getPoster(), s.getSinopse());
        }

        return null;
    }

    public List<EpisodioDto> obterTodasTemporadas(Long id) {
        Optional<Serie> serie = repository.findById(id);


        if (serie.isPresent()){
            Serie s = serie.get();
            return s.getEpisodios().stream()
                    .map(e -> new EpisodioDto(e.getTemporada(), e.getNumeroEpisodio(), e.getTitulo()))
                    .collect(Collectors.toList());
        }

        return null;
    }

    public List<EpisodioDto> obterTemporadasPorNumero (Long id, Long numero){
        return repository.obterEpisodioPorTemporadas(id, numero)
                .stream()
                .map(e -> new EpisodioDto(e.getTemporada(), e.getNumeroEpisodio(), e.getTitulo()))
                .collect(Collectors.toList());
    }

    public List<SerieDto> obterSeriePorCategoria(String nomeGenero) {
        Categoria categoria = Categoria.fromPortugues(nomeGenero);
        return converteDados(repository.findByGenero(categoria));
    }
}


