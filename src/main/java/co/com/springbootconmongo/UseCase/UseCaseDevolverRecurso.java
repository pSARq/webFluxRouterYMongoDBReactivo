package co.com.springbootconmongo.UseCase;

import co.com.springbootconmongo.Collections.Recurso;
import co.com.springbootconmongo.Mappers.RecursoMapper;
import co.com.springbootconmongo.Repositories.RecursoRepository;
import co.com.springbootconmongo.UseCase.Interfaces.DevolverRecurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;


@Service
@Validated
public class UseCaseDevolverRecurso implements DevolverRecurso {

    private final RecursoRepository repository;
    private final RecursoMapper mapper;

    @Autowired
    public UseCaseDevolverRecurso(RecursoRepository repository, RecursoMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Mono<String> devolverRecurso(String id) {
        Mono<Recurso> elemento = repository.findById(id);
         return elemento.flatMap( elemento1 -> {
             if (elemento1.isDisponible() == false){
                 elemento1.setFechaPrestamo(null);
                 elemento1.setDisponible(true);
                 repository.save(elemento1);
                 return Mono.just("Recurso devuelto con éxito");
             }
             return Mono.just("El recurso no ha sido prestado");
         }) ;
    }

}
