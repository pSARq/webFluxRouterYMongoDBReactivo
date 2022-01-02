package co.com.springbootconmongo.Repositories;

import co.com.springbootconmongo.Collections.Recurso;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.List;

public interface RecursoRepository extends ReactiveMongoRepository<Recurso, String> {
    List<Recurso> findByTematicaIn(String tematica);
    List<Recurso> findByTipoIn(String tipo);
}
