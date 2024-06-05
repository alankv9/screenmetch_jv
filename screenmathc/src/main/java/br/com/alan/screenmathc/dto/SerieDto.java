package br.com.alan.screenmathc.dto;

import br.com.alan.screenmathc.model.Categoria;
public record SerieDto(Long id,
                       String titulo,
                       Integer totalTemporadas,
                       Double avaliacao,
                       Categoria genero,
                       String atores,
                       String poster,
                       String sinopse) {

}
