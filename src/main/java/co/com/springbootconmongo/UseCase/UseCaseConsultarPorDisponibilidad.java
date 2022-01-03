package co.com.springbootconmongo.UseCase;

import co.com.springbootconmongo.Mappers.RecursoMapper;
import co.com.springbootconmongo.Repositories.RecursoRepository;
import co.com.springbootconmongo.UseCase.Interfaces.ConsultarDisponibilidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UseCaseConsultarPorDisponibilidad implements ConsultarDisponibilidad {

    private final RecursoRepository repository;
    private final RecursoMapper mapper;

    @Autowired
    public UseCaseConsultarPorDisponibilidad(RecursoRepository repository, RecursoMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Mono<String> findById(String id) {
        return repository.findById(id)
                .map(recurso -> recurso.isDisponible() ? "Esta disponible" : recurso.getFechaPrestamo());
    }
}
