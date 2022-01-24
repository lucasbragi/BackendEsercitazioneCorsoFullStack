package it.Plansoft.esercitazionePlansoft.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Student")
@Table(
    name = "studenti",
        uniqueConstraints = {
            @UniqueConstraint(name = "cod_fiscale_stud_unique", columnNames = "cod_fiscale"),
                @UniqueConstraint(name = "number_stud_unique", columnNames = "telefono")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student extends BaseId<Long>{

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
            name = "cod_fiscale",
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
            mappedBy = "student",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private List<Registration> registrations = new ArrayList<>();

    public Student(String name, String surname, LocalDate birthdayDate, String city, String fiscalCode, String number, String cap, String address, String houseNumber, LocalDate createdAt) {
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
        }
    }

}
