package co.com.springbootconmongo.UseCase;

import co.com.springbootconmongo.Collections.Recurso;
import co.com.springbootconmongo.DTOs.RecursoDTO;
import co.com.springbootconmongo.Mappers.RecursoMapper;
import co.com.springbootconmongo.Repositories.RecursoRepository;
import co.com.springbootconmongo.UseCase.Interfaces.ModificarRecurso;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

public class UseCaseModificarRecurso implements ModificarRecurso {

    private final RecursoRepository repository;
    private final RecursoMapper mapper;

    @Autowired
    public UseCaseModificarRecurso(RecursoRepository repository, RecursoMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Mono<RecursoDTO> modificar(RecursoDTO dto) {
        return repository.save(mapper.fromDTO().apply(dto))
                .map(recurso -> mapper.fromCollection().apply(recurso));
    }
}
