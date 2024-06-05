package br.com.alan.screenmathc.controller;

import br.com.alan.screenmathc.dto.EpisodioDto;
import br.com.alan.screenmathc.dto.SerieDto;
import br.com.alan.screenmathc.model.Episodio;
import br.com.alan.screenmathc.model.Serie;
import br.com.alan.screenmathc.repository.SerieRepository;
import br.com.alan.screenmathc.servece.SerieServece;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.http.Path;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/series")
public class SerieController {

    @Autowired
    private SerieServece servece;

    @GetMapping()
    public List<SerieDto> obterSeries(){
        return servece.obterTodasAsSeries();
    }

    @GetMapping("/top5")
    public List<SerieDto> obterTop5Serie(){
        return servece.obterTop5Serie();
    }
    @GetMapping("/lancamentos")
    public List<SerieDto> obterLancamentos(){
        return servece.obterLancamentos();
    }
    @GetMapping("/{id}")
    public SerieDto obterPorId(@PathVariable Long id){
        return servece.obterPorId(id);
    }

    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodioDto> obterTodasAsTemporads(@PathVariable Long id){
        return servece.obterTodasTemporadas(id);
    }

    @GetMapping("/{id}/temporadas/{numero}")
    public List<EpisodioDto> obterTemporadasPorNumero(@PathVariable Long id, @PathVariable Long numero){
        return servece.obterTemporadasPorNumero(id, numero);
    }
    @GetMapping("/categoria/{nomeGenero}")
    public List<SerieDto> obterSeriePorCategoria(@PathVariable String nomeGenero){
        return  servece.obterSeriePorCategoria(nomeGenero);
    }
}
