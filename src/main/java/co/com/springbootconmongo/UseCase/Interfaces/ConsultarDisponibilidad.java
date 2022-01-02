package co.com.springbootconmongo.UseCase.Interfaces;

import reactor.core.publisher.Mono;

@FunctionalInterface
public interface ConsultarDisponibilidad {
    Mono<String> findById(String id);
}
