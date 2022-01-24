package it.Plansoft.esercitazionePlansoft.service.interfaces;

import it.Plansoft.esercitazionePlansoft.dto.CourseDto;
import it.Plansoft.esercitazionePlansoft.dto.ProfessorCourseDto;
import it.Plansoft.esercitazionePlansoft.dto.ProfessorDto;

import java.util.List;
import java.util.Set;

public interface IProfessorService {

    ProfessorDto findProfessorByNumber(String number);

    Set<ProfessorDto> findProfessorByName(String name);

    Set<ProfessorDto> findProfessorBySurname(String surname);

    Set<ProfessorDto> findProfessorByNameAndSurname(String name, String surname);

    Set<ProfessorDto> findProfessorByCity(String city);

    ProfessorCourseDto addCourse(ProfessorDto professorDto, List<CourseDto> courseDtoList);

    ProfessorCourseDto removeCourse(ProfessorDto professorDto, List<CourseDto> courseDtoList);

    ProfessorCourseDto findByIdProfessor(Long idProfessor);

    ProfessorDto updateById(Long id, ProfessorDto model);

}
