package it.Plansoft.esercitazionePlansoft.service.interfaces;

import it.Plansoft.esercitazionePlansoft.dto.CourseDto;

import java.util.Set;

public interface ICourseService {

    Set<CourseDto> findByName(String name);

    Set<CourseDto> findByProfessorName(String name);

    CourseDto updateById(Long id, CourseDto model);

}
