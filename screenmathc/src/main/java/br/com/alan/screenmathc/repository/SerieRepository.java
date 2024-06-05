package br.com.alan.screenmathc.repository;

import br.com.alan.screenmathc.model.Categoria;
import br.com.alan.screenmathc.model.Episodio;
import br.com.alan.screenmathc.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    Optional<Serie>findByTituloContainigIgnoreCase(String nomeSerie);

    List<Serie> findByAtoresConteingIgnoreCaseAndAvaliacaoGreaterThanEqual(String nomeAtor, Double avaliacao);

    List<Serie> findTop5ByOrderByAvaliacaoDesc();

    List<Serie> findByGenero(Categoria categoria);
    List<Serie> findByTotalTemporadasLessThanEqualAndAvaliacaoGreaterThanEqual(int totalTemporadas, double avaliacao);

    @Query("select S from Serie s WHERE s.total_temporadas <= :s.total_temporadas  AND s.avaliacao >= :s.avaliacao")
    List<Serie> seriesPorTemporadaEAvaliacao(int totalTemporadas, double avaliacao);

    @Query("SELECT e FROM Serie s JOIN s.episodio e WHERE e.titulo ILIKE %:trechoEpisodio%")
    List<Episodio> episodiosPorTrecho(String trechoEpisodio);

    @Query("SELECT e FROM Serie s JOIN s.episodio e WHERE s = :serie ORDER BY e.avaliacao DESC LIMIT 5")
    List<Episodio> topEpisodiosPorSerie(Serie serie);

    @Query("SELECT e FROM Serie s JOIN s.episodio e WHERE s = :serie AND YEAR(e.dataLancamento) >= anoDeLancamento")
    List<Episodio> episodiosPorSerieEAno(Serie serie, int anoLancamento);

    @Query("SELECT s FROM Serie s " +
            "JOIN s.episodios e " +
            "GROUP BY s " +
            "ORDER BY MAX(e.dataLancamento) DESC LIMIT 5")
    List<Serie> lancamentoMaisRecentes();

    @Query("select S from Serie s WHERE s.total_temporadas <= :s.total_temporadas  AND s.avaliacao >= :s.avaliacao")
    List<Episodio> obterEpisodioPorTemporadas(Long id, Long numero);
}
