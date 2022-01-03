package co.com.springbootconmongo.UseCase;

import co.com.springbootconmongo.Collections.Recurso;
import co.com.springbootconmongo.Mappers.RecursoMapper;
import co.com.springbootconmongo.Repositories.RecursoRepository;
import co.com.springbootconmongo.UseCase.Interfaces.PrestarRecurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Service
@Validated
public class UseCasePrestarRecurso implements PrestarRecurso {

    private final RecursoRepository repository;
    private final RecursoMapper mapper;

    @Autowired
    public UseCasePrestarRecurso(RecursoRepository repository, RecursoMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    public Mono<String> prestar(String id){
        Mono<Recurso> recurso = repository.findById(id);

        return recurso.flatMap(elemento ->{
                    if (elemento.isDisponible()){
                        elemento.setFechaPrestamo(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                        elemento.setDisponible(false);
                        repository.save(elemento);
                        return Mono.just("Prestamo realizado con éxito");
                    }
                    return Mono.just("El recurso no se encuentra disponible en este momento");
                });


    }


}
