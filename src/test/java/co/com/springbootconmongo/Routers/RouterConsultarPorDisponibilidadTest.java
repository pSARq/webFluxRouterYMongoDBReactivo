package co.com.springbootconmongo.Routers;

import co.com.springbootconmongo.Collections.Recurso;
import co.com.springbootconmongo.DTOs.RecursoDTO;
import co.com.springbootconmongo.Mappers.RecursoMapper;
import co.com.springbootconmongo.Repositories.RecursoRepository;
import co.com.springbootconmongo.UseCase.UseCaseConsultarPorDisponibilidad;
import co.com.springbootconmongo.UseCase.UseCaseCrearRecurso;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RouterConsultarPorDisponibilidad.class, UseCaseConsultarPorDisponibilidad.class, RecursoMapper.class})
class RouterConsultarPorDisponibilidadTest {

    @MockBean
    private RecursoRepository repository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void consultarDisponibilidad() {
        Recurso recurso1 = new Recurso();
        recurso1.setId("1");
        recurso1.setNombre("Santiago");
        recurso1.setDisponible(true);
        recurso1.setTematica("Deporte");
        recurso1.setTipo("Libro");


        Mockito.when(repository.findById("1")).thenReturn(Mono.just(recurso1));

        webTestClient.get()
                .uri("/disponible/{id}", "1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(userResponse ->{
                    userResponse.equals("Esta disponible");
                });

        Mockito.verify(repository).findById("1");
    }
}