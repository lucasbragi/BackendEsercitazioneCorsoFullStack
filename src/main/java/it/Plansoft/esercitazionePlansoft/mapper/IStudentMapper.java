package it.Plansoft.esercitazionePlansoft.mapper;

import it.Plansoft.esercitazionePlansoft.dto.StudentDto;
import it.Plansoft.esercitazionePlansoft.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IStudentMapper extends IMapper<StudentDto, Student>{

    IStudentMapper ISTANCE = Mappers.getMapper(IStudentMapper.class);

}
