package co.com.springbootconmongo.UseCase.Interfaces;

import co.com.springbootconmongo.DTOs.RecursoDTO;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface CrearRecurso {
    public Mono<RecursoDTO> apply(RecursoDTO dto);
}
