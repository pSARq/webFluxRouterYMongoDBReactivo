package co.com.springbootconmongo.Mappers;

import co.com.springbootconmongo.DTOs.RecursoDTO;
import co.com.springbootconmongo.Collections.Recurso;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class RecursoMapper {

    public Function<RecursoDTO, Recurso> fromDTO(){
        return dto -> {
            Recurso recurso = new Recurso();
            recurso.setId(dto.getId());
            recurso.setNombre(dto.getNombre());
            recurso.setTipo(dto.getTipo());
            recurso.setTematica(dto.getTematica());
            recurso.setDisponible(dto.isDisponible());
            recurso.setFechaPrestamo(recurso.getFechaPrestamo());
            return recurso;
        };
    }

    public Function<Recurso, RecursoDTO> fromCollection(){
        return recurso -> {
            RecursoDTO recursoDTO = new RecursoDTO();
            recursoDTO.setId(recurso.getId());
            recursoDTO.setNombre(recurso.getNombre());
            recursoDTO.setTipo(recurso.getTipo());
            recursoDTO.setTematica(recurso.getTematica());
            recursoDTO.setDisponible(recurso.isDisponible());
            recursoDTO.setFechaPrestamo(recurso.getFechaPrestamo());
            return recursoDTO;
        };
    }


    public Function<List<Recurso>, List<RecursoDTO>> fromCollectionList(){
        return listCollection -> {
            if (listCollection == null){
                return null;
            }

            List<RecursoDTO> list = new ArrayList<>(listCollection.size());
            Iterator listTracks = listCollection.iterator();

            while (listTracks.hasNext()){
                Recurso recurso = (Recurso) listTracks.next();
                list.add(fromCollection().apply(recurso));
            }
            return list;
        };
    }
}
