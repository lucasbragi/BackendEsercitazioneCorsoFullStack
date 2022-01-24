package it.Plansoft.esercitazionePlansoft.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationId implements Serializable {

    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "student_id")
    private Long studentId;

}
