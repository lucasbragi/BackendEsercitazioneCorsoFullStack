package it.Plansoft.esercitazionePlansoft.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentCourseRegistrationDto {

    private StudentDto studentDto;
    private List<CourseRegistrationDto> courseRegistrationDto;

    public void addCourse(CourseDto courseDto, LocalDate createdAt) {
        if (courseRegistrationDto == null) {
            courseRegistrationDto = new ArrayList<>();
        }
        courseRegistrationDto.add(new CourseRegistrationDto(courseDto, createdAt));
    }

}
