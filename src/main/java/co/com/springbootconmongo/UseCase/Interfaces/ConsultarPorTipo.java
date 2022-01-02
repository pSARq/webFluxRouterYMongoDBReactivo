package co.com.springbootconmongo.UseCase.Interfaces;

import co.com.springbootconmongo.DTOs.RecursoDTO;
import reactor.core.publisher.Flux;

@FunctionalInterface
public interface ConsultarPorTipo {
    Flux<RecursoDTO> findByTipo(String tipo);
}
