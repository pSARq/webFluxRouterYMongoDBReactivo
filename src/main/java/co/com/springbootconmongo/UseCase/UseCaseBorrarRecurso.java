package co.com.springbootconmongo.UseCase;

import co.com.springbootconmongo.Mappers.RecursoMapper;
import co.com.springbootconmongo.Repositories.RecursoRepository;
import co.com.springbootconmongo.UseCase.Interfaces.BorrarRecurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UseCaseBorrarRecurso implements BorrarRecurso {

    private final RecursoRepository repository;
    private final RecursoMapper mapper;

    @Autowired
    public UseCaseBorrarRecurso(RecursoRepository repository, RecursoMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return repository.deleteById(id);
    }
}
