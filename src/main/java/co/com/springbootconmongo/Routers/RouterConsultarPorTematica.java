package co.com.springbootconmongo.Routers;

import co.com.springbootconmongo.DTOs.RecursoDTO;
import co.com.springbootconmongo.UseCase.UseCaseConsultarPorDisponibilidad;
import co.com.springbootconmongo.UseCase.UseCaseConsultarPorTematica;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConsultarPorTematica {

    @Bean
    public RouterFunction<ServerResponse> consultarPorTematica(UseCaseConsultarPorTematica useCaseConsultarPorTematica){
        return route(
                GET("/tematica/{tematica}").and(accept(MediaType.APPLICATION_JSON)),
                request ->ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCaseConsultarPorTematica.findByTematica(request.pathVariable("tematica")), RecursoDTO.class)));
    }
}
