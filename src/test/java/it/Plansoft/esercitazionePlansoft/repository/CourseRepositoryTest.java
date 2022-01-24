package it.Plansoft.esercitazionePlansoft.repository;

import it.Plansoft.esercitazionePlansoft.model.Course;
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
class CourseRepositoryTest {

    @Autowired
    CourseRepository repository;

    @Autowired
    ProfessorRepository professorRepository;

    @BeforeEach
    public void startup() {
        log.info("startup");
        loadDataBase();
    }

    private void loadDataBase() {

        Professor professor = this.professorRepository.save(new Professor(
                "Paolo",
                "Maldini",
                LocalDate.of(1992, 11, 12),
                "Palermo",
                "rfwefwfvzs",
                "32525455425",
                "32542",
                "Via 3 ottobre",
                "23d",
                LocalDate.of(2021, 10, 22)
        ));

        this.repository.save(new Course("Database",
                "Questo corso insegna database",
                professor,
                LocalDate.of(2022, 02, 10),
                LocalDate.of(2022, 06, 18),
                LocalDate.of(2021, 11, 22)
        ));

    }

    @AfterEach
    public void shutdown() {
        log.info("shutdown");
        this.repository.deleteAll();
    }

    @Test
    void itShouldFindByName() {

        if (this.repository.findByName("Database").isPresent()) {

            List<Course> courses = this.repository.findByName("Database").get();

            assertTrue(courses.size() > 0);

            Course course = courses.get(0);

            assertTrue(course.getName().equals("Database"));

        }

    }

    @Test
    void itShouldFindByProfessorName() {

        if (this.repository.findByProfessorName("Paolo").isPresent()) {

            List<Course> courses = this.repository.findByProfessorName("Paolo").get();

            assertTrue(courses.size() > 0);

            Course course = courses.get(0);

            assertTrue(course.getProfessor().getSurname().equals("Maldini"));

        }

    }
}