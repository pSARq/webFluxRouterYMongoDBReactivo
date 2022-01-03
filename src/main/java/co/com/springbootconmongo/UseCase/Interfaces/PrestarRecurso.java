package co.com.springbootconmongo.UseCase.Interfaces;

import reactor.core.publisher.Mono;

@FunctionalInterface
public interface PrestarRecurso {
    Mono<String> prestar(String id);
}
