package co.com.springbootconmongo.UseCase.Interfaces;

import co.com.springbootconmongo.Collections.Recurso;
import co.com.springbootconmongo.DTOs.RecursoDTO;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface DevolverRecurso {
    Mono<String> devolverRecurso(RecursoDTO dto);
}
