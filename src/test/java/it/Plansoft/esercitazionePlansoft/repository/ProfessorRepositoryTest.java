package it.Plansoft.esercitazionePlansoft.repository;

import it.Plansoft.esercitazionePlansoft.model.Professor;
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
class ProfessorRepositoryTest {

    @Autowired
    ProfessorRepository repository;

    @BeforeEach
    public void startup() {
        log.info("startup");
        loadDataBase();
    }

    private void loadDataBase() {

        this.repository.save(new Professor("Giacomo",
                "Leopardi",
                LocalDate.of(1992, 11, 12),
                "Milano",
                "gwerg",
                "32525425",
                "32542",
                "Via 3 ottobre",
                "23d",
                LocalDate.of(2021, 10, 22)));

    }

    @AfterEach
    public void shutdown() {
        log.info("shutdown");
        this.repository.deleteAll();
    }

    @Test
    void itShouldFindProfessorByNumber() {

        if (this.repository.findProfessorByNumber("32525425").isPresent()) {

            Professor professor = this.repository.findProfessorByNumber("32525425").get();

            assertTrue(professor.getSurname().equals("Leopardi"));

        }

    }

    @Test
    void itShouldFindProfessorByName() {

        if (this.repository.findProfessorByName("Giacomo").isPresent()) {

            List<Professor> professors = this.repository.findProfessorByName("Giacomo").get();

            assertTrue(professors.size() > 0);

            Professor professor = professors.get(0);

            assertTrue(professor.getSurname().equals("Leopardi"));

        }

    }

    @Test
    void itShouldFindProfessorBySurname() {

        if (this.repository.findProfessorBySurname("Leopardi").isPresent()) {

            List<Professor> professors = this.repository.findProfessorBySurname("Leopardi").get();

            assertTrue(professors.size() > 0);

            Professor professor = professors.get(0);

            assertTrue(professor.getName().equals("Giacomo"));

        }

    }

    @Test
    void itShouldFindProfessorByNameAndSurname() {

        if (this.repository.findProfessorByNameAndSurname("Giacomo", "Leopardi").isPresent()) {

            List<Professor> professors = this.repository.findProfessorByNameAndSurname("Giacomo", "Leopardi").get();

            assertTrue(professors.size() > 0);

            Professor professor = professors.get(0);

            assertTrue(professor.getName().equals("Giacomo") && professor.getSurname().equals("Leopardi"));

        }

    }

    @Test
    void itShouldFindProfessorByCity() {

        if (this.repository.findProfessorByCity("Milano").isPresent()) {

            List<Professor> professors = this.repository.findProfessorByCity("Milano").get();

            assertTrue(professors.size() > 0);

            Professor professor = professors.get(0);

            assertTrue(professor.getName().equals("Giacomo"));

        }

    }
}