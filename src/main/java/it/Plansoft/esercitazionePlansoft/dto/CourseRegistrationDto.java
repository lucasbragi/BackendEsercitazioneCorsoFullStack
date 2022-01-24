package it.Plansoft.esercitazionePlansoft.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseRegistrationDto {

    private CourseDto courseDto;
    private LocalDate createdAt;

}
