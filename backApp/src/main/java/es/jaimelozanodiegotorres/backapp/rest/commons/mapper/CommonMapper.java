package es.jaimelozanodiegotorres.backapp.rest.commons.mapper;


import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * CommonMapper es una clase abstracta que define métodos para mapear
 * DTOs (Data Transfer Objects) a modelos de entidades para operaciones
 * de guardado y actualización.
 *
 * @param <M> el tipo del modelo de entidad.
 * @param <D> el tipo del DTO utilizado.
 */
public interface CommonMapper<M, D> {

    //GenericMapper INSTANCE = Mappers.getMapper( GenericMapper.class );

    /**
     * Convierte un DTO de guardado a un modelo de entidad.
     *
     * @param dto el DTO de guardado.
     * @return el modelo de entidad.
     */
    M dtoToModel(D dto);
    List<M> dtoToModel(List<D> dto);

    D modelToDto(M model);
    List<D> modelToDto(List<M> model);

    M updateModel(@MappingTarget M original, D dto);
}
