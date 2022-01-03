package co.com.springbootconmongo.Routers;

import co.com.springbootconmongo.Collections.Recurso;
import co.com.springbootconmongo.DTOs.RecursoDTO;
import co.com.springbootconmongo.Mappers.RecursoMapper;
import co.com.springbootconmongo.Repositories.RecursoRepository;
import co.com.springbootconmongo.UseCase.UseCaseCrearRecurso;
import co.com.springbootconmongo.UseCase.UseCaseObtenerRecursos;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RouterObtenerRecursos.class, UseCaseObtenerRecursos.class, RecursoMapper.class})
class RouterObtenerRecursosTest {

    @MockBean
    private RecursoRepository repository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void obtenerRecursos() {
        Recurso recurso1 = new Recurso();
        recurso1.setId("1");
        recurso1.setNombre("Santiago");
        recurso1.setDisponible(true);
        recurso1.setTematica("Deporte");
        recurso1.setTipo("Libro");

        Recurso recurso2 = new Recurso();
        recurso2.setId("2");
        recurso2.setNombre("Santiago");
        recurso2.setDisponible(true);
        recurso2.setTematica("Deporte");
        recurso2.setTipo("Libro");


        Mockito.when(repository.findAll()).thenReturn(Flux.just(recurso1, recurso2));

        webTestClient.get()
                .uri("/obtener")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(RecursoDTO.class)
                .value(userResponse ->{
                    assertEquals(recurso1.getId(), userResponse.get(0).getId());
                    assertEquals(recurso1.getNombre(), userResponse.get(0).getNombre());
                    assertEquals(recurso1.getTipo(), userResponse.get(0).getTipo());
                    assertEquals(recurso1.getTematica(), userResponse.get(0).getTematica());
                    assertEquals(recurso1.isDisponible(), userResponse.get(0).isDisponible());
                    assertEquals(recurso1.getFechaPrestamo(), userResponse.get(0).getFechaPrestamo());
                    assertEquals(recurso2.getId(), userResponse.get(1).getId());
                    assertEquals(recurso2.getNombre(), userResponse.get(1).getNombre());
                    assertEquals(recurso2.getTipo(), userResponse.get(1).getTipo());
                    assertEquals(recurso2.getTematica(), userResponse.get(1).getTematica());
                    assertEquals(recurso2.isDisponible(), userResponse.get(1).isDisponible());
                    assertEquals(recurso2.getFechaPrestamo(), userResponse.get(1).getFechaPrestamo());

                });
        Mockito.verify(repository).findAll();
    }
}