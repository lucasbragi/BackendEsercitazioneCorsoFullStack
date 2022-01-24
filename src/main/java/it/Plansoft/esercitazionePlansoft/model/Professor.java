package it.Plansoft.esercitazionePlansoft.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "Professor")
@Table(
        name = "docenti",
        uniqueConstraints = {
                @UniqueConstraint(name = "cod_fiscale_doc_unique", columnNames = "codice_fiscale"),
                @UniqueConstraint(name = "number_doc_unique", columnNames = "telefono")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Professor extends BaseId<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(
            name = "nome",
            nullable = false,
            columnDefinition = "TEXT",
            length = 50
    )
    private String name;

    @Column(
            name = "cognome",
            nullable = false,
            columnDefinition = "TEXT",
            length = 60
    )
    private String surname;

    @Column(
            name = "data_di_nascita",
            nullable = false,
            columnDefinition = "TIMESTAMP"
    )
    private LocalDate birthdayDate;

    @Column(
            name = "comune",
            nullable = false,
            columnDefinition = "TEXT",
            length = 50
    )
    private String city;

    @Column(
            name = "codice_fiscale",
            nullable = false,
            columnDefinition = "TEXT",
            length = 30
    )
    private String fiscalCode;

    @Column(
            name = "telefono",
            nullable = false,
            columnDefinition = "TEXT",
            length = 30
    )
    private String number;

    @Column(
            name = "cap",
            nullable = false,
            columnDefinition = "TEXT",
            length = 6
    )
    private String cap;

    @Column(
            name = "via",
            nullable = false,
            columnDefinition = "TEXT",
            length = 80
    )
    private String address;

    @Column(
            name = "numero_civico",
            nullable = false,
            columnDefinition = "TEXT",
            length =10
    )
    private String houseNumber;

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
            mappedBy = "professor",
            cascade = {CascadeType.PERSIST}
    )
    @JsonIgnore
    private List<Course> courses;


    public Professor(String name, String surname, LocalDate birthdayDate, String city, String fiscalCode, String number, String cap, String address, String houseNumber, LocalDate createdAt) {
        this.name = name;
        this.surname = surname;
        this.birthdayDate = birthdayDate;
        this.city = city;
        this.fiscalCode = fiscalCode;
        this.number = number;
        this.cap = cap;
        this.address = address;
        this.houseNumber = houseNumber;
        this.createdAt = createdAt;
        this.updateAt = LocalDate.now();
    }

    public void addCourses(Course course) {
        if (!courses.contains(course)) {
            courses.add(course);
        }
    }

    public void remove(Course course) {
        if (courses.contains(course)) {
            courses.remove(course);
        }
    }

}
