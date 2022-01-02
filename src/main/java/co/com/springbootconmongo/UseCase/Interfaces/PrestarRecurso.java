package co.com.springbootconmongo.UseCase.Interfaces;

import co.com.springbootconmongo.DTOs.RecursoDTO;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface PrestarRecurso {
    Mono<String> prestar(RecursoDTO dto);
}
