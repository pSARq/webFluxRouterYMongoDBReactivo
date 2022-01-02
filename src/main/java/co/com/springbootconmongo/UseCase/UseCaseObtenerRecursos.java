package co.com.springbootconmongo.UseCase;

import co.com.springbootconmongo.DTOs.RecursoDTO;
import co.com.springbootconmongo.Mappers.RecursoMapper;
import co.com.springbootconmongo.Repositories.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

public class UseCaseObtenerRecursos implements Supplier<Flux<RecursoDTO>> {

    private final RecursoRepository repository;
    private final RecursoMapper mapper;

    @Autowired
    public UseCaseObtenerRecursos(RecursoRepository repository, RecursoMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Flux<RecursoDTO> get() {
        return repository.findAll()
                .map(mapper.fromCollection());
    }
}
