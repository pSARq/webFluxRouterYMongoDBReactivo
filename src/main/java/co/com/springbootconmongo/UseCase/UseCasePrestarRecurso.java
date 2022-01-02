package co.com.springbootconmongo.UseCase;

import co.com.springbootconmongo.Collections.Recurso;
import co.com.springbootconmongo.DTOs.RecursoDTO;
import co.com.springbootconmongo.Mappers.RecursoMapper;
import co.com.springbootconmongo.Repositories.RecursoRepository;
import co.com.springbootconmongo.UseCase.Interfaces.PrestarRecurso;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UseCasePrestarRecurso implements PrestarRecurso {

    private final RecursoRepository repository;
    private final RecursoMapper mapper;

    @Autowired
    public UseCasePrestarRecurso(RecursoRepository repository, RecursoMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Mono<String> prestar(RecursoDTO dto) {
        return repository.findById(dto.getId())
                .map(recurso -> {
                    if (recurso.isDisponible()){
                        recurso.setFechaPrestamo(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                        recurso.setDisponible(false);
                        repository.save(recurso);
                        return "Prestamo realizado con éxito";
                    }
                    return "El recurso no se encuentra disponible en este momento";
                });
    }
}
