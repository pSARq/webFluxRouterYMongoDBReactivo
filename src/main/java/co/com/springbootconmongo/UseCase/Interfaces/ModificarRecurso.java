package co.com.springbootconmongo.UseCase.Interfaces;

import co.com.springbootconmongo.DTOs.RecursoDTO;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface ModificarRecurso {
    Mono<RecursoDTO> modificar(RecursoDTO dto);
}
