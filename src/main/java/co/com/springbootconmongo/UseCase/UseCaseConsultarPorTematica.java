package co.com.springbootconmongo.UseCase;

import co.com.springbootconmongo.DTOs.RecursoDTO;
import co.com.springbootconmongo.Mappers.RecursoMapper;
import co.com.springbootconmongo.Repositories.RecursoRepository;
import co.com.springbootconmongo.UseCase.Interfaces.ConsultarPorTematica;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;

import java.util.Collections;

public class UseCaseConsultarPorTematica implements ConsultarPorTematica {

    private final RecursoRepository repository;
    private final RecursoMapper mapper;

    @Autowired
    public UseCaseConsultarPorTematica(RecursoRepository repository, RecursoMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Flux<RecursoDTO> findByTematica(String tematica) {
        return (Flux<RecursoDTO>) repository.findByTematicaIn(tematica)
                .stream().map(recursos -> mapper.fromCollectionList().apply(Collections.singletonList(recursos)));
    }
}
