package it.Plansoft.esercitazionePlansoft.repository;

import it.Plansoft.esercitazionePlansoft.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class StudentRepositoryTest {

    @Autowired
    StudentRepository repository;

    @BeforeEach
    public void startup() {
        log.info("startup");
        loadDataBase();
    }

    private void loadDataBase() {

        this.repository.save(new Student("Paola",
                "Varale",
                LocalDate.of(1981, 11, 23),
                "Milano",
                "RTYUIO4567RT",
                "33156789",
                "3456",
                "via casa di Paola",
                "456",
                LocalDate.of(2021, 11, 23)));

    }

    @AfterEach
    public void shutdown() {
        log.info("shutdown");
        this.repository.deleteAll();
    }

    @Test
    void itShouldFindStudentByNumber() {

        if (this.repository.findStudentByNumber("33156789").isPresent()) {

            Student student = this.repository.findStudentByNumber("33156789").get();

            assertTrue(student.getSurname().equals("Varale"));

        }

    }

    @Test
    void itShouldFindStudentByName() {

        if (this.repository.findStudentByName("Paola").isPresent()) {

            List<Student> students = this.repository.findStudentByName("Paola").get();

            assertTrue(students.size() > 0);

            Student student = students.get(0);

            assertTrue(student.getName().equals("Paola"));

        }

    }

    @Test
    void itShouldFindStudentBySurname() {

        if (this.repository.findStudentBySurname("Varale").isPresent()) {

            List<Student> students = this.repository.findStudentBySurname("Varale").get();

            assertTrue(students.size() > 0);

            Student student = students.get(0);

            assertTrue(student.getName().equals("Paola"));

        }

    }

    @Test
    void itShouldFindStudentByNameAndSurname() {

        if (this.repository.findStudentByNameAndSurname("Paola", "Varale").isPresent()) {

            List<Student> students = this.repository.findStudentByNameAndSurname("Paola", "Varale").get();

            assertTrue(students.size() > 0);

            Student student = students.get(0);

            assertTrue(student.getName().equals("Paola") && student.getSurname().equals("Varale"));

        }

    }

    @Test
    void itShouldFindStudentByCity() {

        if (this.repository.findStudentByCity("Milano").isPresent()) {

            List<Student> students = this.repository.findStudentByCity("Milano").get();

            assertTrue(students.size() > 0);

            Student student = students.get(0);

            assertTrue(student.getName().equals("Paola") && student.getCity().equals("Milano"));

        }

    }
}