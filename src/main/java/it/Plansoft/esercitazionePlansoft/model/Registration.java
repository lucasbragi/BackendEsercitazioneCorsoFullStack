package it.Plansoft.esercitazionePlansoft.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "CourseRegistration")
@Table(
        name = "corsiseguiti",
        uniqueConstraints = {
                @UniqueConstraint(name = "unico_corso", columnNames = {"id_studente", "id_corso"})
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Registration {

    @EmbeddedId
    private RegistrationId id;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(
            name = "id_studente",
            foreignKey = @ForeignKey(
                    name = "course_registration_student_id_fk"
            )
    )
    private Student student;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(
            name = "id_corso",
            foreignKey = @ForeignKey(
                    name = "course_registration_course_id_fk"
            )
    )
    private Course course;

    @Column(
            name = "data_di_iscrizione",
            nullable = false,
            columnDefinition = "TIMESTAMP"
    )
    private LocalDate registrationDate;

    public Registration(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.registrationDate = LocalDate.now();
    }

}
