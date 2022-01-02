package co.com.springbootconmongo.UseCase;

import co.com.springbootconmongo.DTOs.RecursoDTO;
import co.com.springbootconmongo.Mappers.RecursoMapper;
import co.com.springbootconmongo.Repositories.RecursoRepository;
import co.com.springbootconmongo.UseCase.Interfaces.DevolverRecurso;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

public class UseCaseDevolverRecurso implements DevolverRecurso {

    private final RecursoRepository repository;
    private final RecursoMapper mapper;

    @Autowired
    public UseCaseDevolverRecurso(RecursoRepository repository, RecursoMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Mono<String> devolverRecurso(RecursoDTO dto) {
        return repository.findById(dto.getId())
                .map(recurso -> {
                    if (!recurso.isDisponible()){
                        recurso.setFechaPrestamo(null);
                        recurso.setDisponible(true);
                        repository.save(recurso);
                        return "Recurso devuelto con éxito";
                    }
                    return "El recurso no ha sido prestado";
                });
    }
}
