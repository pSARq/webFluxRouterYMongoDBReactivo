package co.com.springbootconmongo.Routers;

import co.com.springbootconmongo.Collections.Recurso;
import co.com.springbootconmongo.DTOs.RecursoDTO;
import co.com.springbootconmongo.Mappers.RecursoMapper;
import co.com.springbootconmongo.Repositories.RecursoRepository;
import co.com.springbootconmongo.UseCase.UseCaseCrearRecurso;
import co.com.springbootconmongo.UseCase.UseCaseDevolverRecurso;
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
@ContextConfiguration(classes = {RouterDevolverRecurso.class, UseCaseDevolverRecurso.class, RecursoMapper.class})
class RouterDevolverRecursoTest {

    @MockBean
    private RecursoRepository repository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void devolverRecurso() {
        Recurso recurso1 = new Recurso();
        recurso1.setId("1");
        recurso1.setNombre("Santiago");
        recurso1.setDisponible(false);
        recurso1.setTematica("Deporte");
        recurso1.setTipo("Libro");
        recurso1.setFechaPrestamo("2021/12/28 20:24:51");

        Mockito.when(repository.findById("1")).thenReturn(Mono.just(recurso1));

        webTestClient.put()
                .uri("/devolver/{id}", "1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(userResponse ->{
                    userResponse.equals("Recurso devuelto con éxito");
                });
        Mockito.verify(repository).findById("1");
    }
}