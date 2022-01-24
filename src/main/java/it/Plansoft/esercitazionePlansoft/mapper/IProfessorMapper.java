package it.Plansoft.esercitazionePlansoft.mapper;

import it.Plansoft.esercitazionePlansoft.dto.ProfessorDto;
import it.Plansoft.esercitazionePlansoft.model.Professor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IProfessorMapper extends IMapper<ProfessorDto, Professor>{

    IProfessorMapper ISTANCE = Mappers.getMapper(IProfessorMapper.class);

}
