package it.Plansoft.esercitazionePlansoft.service.interfaces;

import it.Plansoft.esercitazionePlansoft.dto.CourseRegistrationDto;
import it.Plansoft.esercitazionePlansoft.dto.StudentCourseRegistrationDto;
import it.Plansoft.esercitazionePlansoft.dto.StudentDto;

import java.util.List;
import java.util.Set;

public interface IStudentService {


    StudentDto findStudentByNumber(String number);

    Set<StudentDto> findStudentByName(String name);

    Set<StudentDto> findStudentBySurname(String surname);

    Set<StudentDto> findStudentByNameAndSurname(String name, String surname);

    Set<StudentDto> findStudentByCity(String city);

    StudentCourseRegistrationDto addStudentCourse(StudentDto studentDto, List<CourseRegistrationDto> courseRegistrationDtoList);

    StudentCourseRegistrationDto removeStudentCourse(Long studentId, Long courseId);

    StudentCourseRegistrationDto findByIdStudent(Long idStudent);

    StudentDto updateById(Long id, StudentDto model);

}
