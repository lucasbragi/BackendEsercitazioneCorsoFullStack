package it.Plansoft.esercitazionePlansoft.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfessorCourseDto {

    private ProfessorDto professorDto;
    private List<CourseDto> courses;

    public void addCourses(CourseDto courseDto) {
        if (courses == null) {
            courses = new ArrayList<>();
        }
        courses.add(courseDto);
    }

}
