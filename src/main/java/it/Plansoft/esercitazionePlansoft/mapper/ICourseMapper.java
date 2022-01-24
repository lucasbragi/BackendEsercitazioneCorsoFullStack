package it.Plansoft.esercitazionePlansoft.mapper;

import it.Plansoft.esercitazionePlansoft.dto.CourseDto;
import it.Plansoft.esercitazionePlansoft.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ICourseMapper extends IMapper<CourseDto, Course> {

    ICourseMapper ISTANCE = Mappers.getMapper(ICourseMapper.class);

}
