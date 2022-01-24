package it.Plansoft.esercitazionePlansoft.dto;

import it.Plansoft.esercitazionePlansoft.model.BaseId;
import it.Plansoft.esercitazionePlansoft.model.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorDto extends BaseId<Long> {

    private Long id;

    private String name;

    private String surname;

    private LocalDate birthdayDate;

    private String city;

    private String fiscalCode;

    private String number;

    private String cap;

    private String address;

    private String houseNumber;

    private LocalDate createdAt;

    private LocalDate updateAt;

    public ProfessorDto(String name, String surname, LocalDate birthdayDate, String city, String fiscalCode, String number, String cap, String address, String houseNumber, LocalDate createdAt) {
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

}
