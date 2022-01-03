package co.com.springbootconmongo.Routers;

import co.com.springbootconmongo.DTOs.RecursoDTO;
import co.com.springbootconmongo.UseCase.UseCaseDevolverRecurso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterDevolverRecurso {

    @Bean
    public RouterFunction<ServerResponse> devolverRecurso(UseCaseDevolverRecurso useCaseDevolverRecurso){
        return route(PUT("/devolver/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(useCaseDevolverRecurso.devolverRecurso(request.pathVariable("id")), RecursoDTO.class));
    }
}
