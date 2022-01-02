package co.com.springbootconmongo.UseCase.Interfaces;

import reactor.core.publisher.Mono;

@FunctionalInterface
public interface BorrarRecurso {
    Mono<Void> deleteById(String id);
}
