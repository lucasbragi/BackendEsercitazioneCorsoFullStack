package it.Plansoft.esercitazionePlansoft.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Course")
@Table(
        name = "corsi",
        uniqueConstraints = {
                @UniqueConstraint(name = "corso_docente_unique", columnNames = {"nome", "id_docente", "data_di_partenza", "data_di_conclusione"}),
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course extends BaseId<Long>{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(
                name = "nome",
                nullable = false,
                columnDefinition = "TEXT",
                length = 100
        )
        private String name;

        @Column(
                name = "descrizione",
                nullable = false,
                columnDefinition = "TEXT",
                length = 200
        )
        private String description;

        @ManyToOne()
        @JoinColumn(
                name = "id_docente",
                foreignKey = @ForeignKey(
                        name = "course_professor_id_fk"
                )
        )
        private Professor professor;

        @Column(
                name = "data_di_partenza",
                nullable = false,
                columnDefinition = "TIMESTAMP"
        )
        private LocalDate startDate;

        @Column(
                name = "data_di_conclusione",
                nullable = false,
                columnDefinition = "TIMESTAMP"
        )
        private LocalDate endDate;

        @Column(
                name = "created_at",
                nullable = false,
                columnDefinition = "TIMESTAMP"
        )
        private LocalDate createdAt;

        @Column(
                name = "updated_at",
                columnDefinition = "TIMESTAMP"
        )
        private LocalDate updateAt;

        @OneToMany(
                mappedBy = "course",
                orphanRemoval = true,
                cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
                fetch = FetchType.LAZY
        )
        private List<Registration> registrations = new ArrayList<>();

        public Course(String name, String description, Professor professor, LocalDate startDate, LocalDate endDate, LocalDate createdAt) {
                this.name = name;
                this.description = description;
                this.professor = professor;
                this.startDate = startDate;
                this.endDate = endDate;
                this.createdAt = createdAt;
                this.updateAt = LocalDate.now();
        }

        /**
         * Metodo per aggiungere una registrazione di uno studente ad un corso
         * @param reg
         */
        public void addRegistration(Registration reg) {
                if (!registrations.contains(reg)){
                        registrations.add(reg);
                }
        }

        /**
         * Metodo per rimuovere una registrazione di uno studente ad un corso
         * @param registration
         */
        public void removeRegistration(Registration registration) {
                if (registrations.contains(registration)) {
                        registrations.remove(registration);
                        System.out.println("Il corso è stato eliminato");
                }
        }

}
