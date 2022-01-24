package it.Plansoft.esercitazionePlansoft.dto;

import it.Plansoft.esercitazionePlansoft.model.BaseId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto extends BaseId<Long> {

    private Long id;

    private String name;

    private String description;

    private ProfessorDto professor;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDate createdAt;

    private LocalDate updateAt;

    public CourseDto(String name, String description, ProfessorDto professor, LocalDate startDate, LocalDate endDate, LocalDate createdAt) {
        this.name = name;
        this.description = description;
        this.professor = professor;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdAt = createdAt;
        this.updateAt = LocalDate.now();
    }

}
