package co.com.springbootconmongo.UseCase;

import co.com.springbootconmongo.DTOs.RecursoDTO;
import co.com.springbootconmongo.Mappers.RecursoMapper;
import co.com.springbootconmongo.Repositories.RecursoRepository;
import co.com.springbootconmongo.UseCase.Interfaces.CrearRecurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UseCaseCrearRecurso implements CrearRecurso {

    private final RecursoRepository repository;
    private final RecursoMapper mapper;

    @Autowired
    public UseCaseCrearRecurso(RecursoRepository repository, RecursoMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Mono<RecursoDTO> apply(RecursoDTO dto) {
        return repository.save(mapper.fromDTO().apply(dto))
                .map(mapper.fromCollection());
    }
}
