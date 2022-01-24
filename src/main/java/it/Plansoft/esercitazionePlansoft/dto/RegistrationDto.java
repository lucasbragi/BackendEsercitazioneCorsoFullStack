package it.Plansoft.esercitazionePlansoft.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDto {

    private RegistrationIdDto id;

    private StudentDto student;

    private CourseDto course;

    private LocalDate registrationDate;

    public RegistrationDto(StudentDto student, CourseDto course) {
        this.student = student;
        this.course = course;
        this.registrationDate = LocalDate.now();
    }

}
