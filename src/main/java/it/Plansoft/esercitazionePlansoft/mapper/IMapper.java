package it.Plansoft.esercitazionePlansoft.mapper;

import org.mapstruct.Context;

import java.util.List;
import java.util.Set;

public interface IMapper<DTO, MODEL> {

    DTO toDto(MODEL model, @Context CycleAvoidingMappingContext context);
    MODEL dtoToModel(DTO dto);
    List<DTO> toDtos(List<MODEL> models, @Context CycleAvoidingMappingContext context);
    List<MODEL> toModels(List<DTO> dtos);
    // set
    Set<DTO> toSetDtos(Set<MODEL> model, @Context CycleAvoidingMappingContext context);
    // set
    Set<DTO> toSetDtos(List<MODEL> model, @Context CycleAvoidingMappingContext context);
}