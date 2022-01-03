package co.com.springbootconmongo.Routers;

import co.com.springbootconmongo.Collections.Recurso;
import co.com.springbootconmongo.DTOs.RecursoDTO;
import co.com.springbootconmongo.Mappers.RecursoMapper;
import co.com.springbootconmongo.Repositories.RecursoRepository;
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
@ContextConfiguration(classes = {RouterCrearRecurso.class, UseCaseCrearRecurso.class, RecursoMapper.class})
class RouterModificarRecursoTest {

    @MockBean
    private RecursoRepository repository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void modificarRecurso() {
        Recurso recursoAGuardar = new Recurso();
        recursoAGuardar.setId("1");
        recursoAGuardar.setNombre("Santiago");
        recursoAGuardar.setDisponible(true);
        recursoAGuardar.setTematica("Deporte");
        recursoAGuardar.setTipo("Libro");

        RecursoDTO recursoADevolver = new RecursoDTO(
                recursoAGuardar.getId(),
                recursoAGuardar.getNombre(),
                recursoAGuardar.getTipo(),
                recursoAGuardar.getTematica(),
                recursoAGuardar.isDisponible(),
                recursoAGuardar.getFechaPrestamo());

        Mono<Recurso> recursoMono = Mono.just(recursoAGuardar);

        Mockito.when(repository.save(any())).thenReturn(recursoMono);

        webTestClient.post()
                .uri("/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(recursoADevolver), RecursoDTO.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(RecursoDTO.class)
                .value(userResponse ->{
                    Assertions.assertEquals(recursoADevolver.getId(), userResponse.getId());
                    Assertions.assertEquals(recursoADevolver.getNombre(), userResponse.getNombre());
                    Assertions.assertEquals(recursoADevolver.getTipo(), userResponse.getTipo());
                    Assertions.assertEquals(recursoADevolver.getTematica(), userResponse.getTematica());
                    Assertions.assertEquals(recursoADevolver.isDisponible(), userResponse.isDisponible());
                    Assertions.assertEquals(recursoADevolver.getFechaPrestamo(), userResponse.getFechaPrestamo());

                });
        Mockito.verify(repository).save(any());
    }
}