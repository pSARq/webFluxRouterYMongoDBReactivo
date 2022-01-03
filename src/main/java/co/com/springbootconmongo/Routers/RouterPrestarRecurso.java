package co.com.springbootconmongo.Routers;

import co.com.springbootconmongo.DTOs.RecursoDTO;
import co.com.springbootconmongo.UseCase.UseCasePrestarRecurso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterPrestarRecurso {

    @Bean
    public RouterFunction<ServerResponse> prestarRecurso(UseCasePrestarRecurso useCasePrestarRecurso){
        return route(PUT("/prestar/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(useCasePrestarRecurso.prestar(request.pathVariable("id")), RecursoDTO.class));
    }
}
