package co.com.springbootconmongo.UseCase.Interfaces;

import reactor.core.publisher.Mono;

@FunctionalInterface
public interface DevolverRecurso {
    Mono<String> devolverRecurso(String id);
}
