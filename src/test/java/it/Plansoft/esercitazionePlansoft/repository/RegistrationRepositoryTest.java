package it.Plansoft.esercitazionePlansoft.repository;

import it.Plansoft.esercitazionePlansoft.model.*;
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
class RegistrationRepositoryTest {

    @Autowired
    RegistrationRepository repository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    CourseRepository courseRepository;

    @BeforeEach
    public void startup() {
        log.info("startup");
        loadDataBase();
    }

    private void loadDataBase() {

        Student student = this.studentRepository.save(new Student("Paola",
                "Varale",
                LocalDate.of(1981, 11, 23),
                "Milano",
                "RTYUIO4567RT",
                "33156789",
                "3456",
                "via casa di Paola",
                "456",
                LocalDate.of(2021, 11, 23)));

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

        Course course = this.courseRepository.save(new Course("Database",
                "Questo corso insegna database",
                professor,
                LocalDate.of(2022, 02, 10),
                LocalDate.of(2022, 06, 18),
                LocalDate.of(2021, 11, 22)
        ));

        RegistrationId id = new RegistrationId(course.getId(), student.getId());

        this.repository.save(new Registration(
                id,
                student,
                course,
                LocalDate.now()
        ));

    }

    @AfterEach
    public void shutdown() {
        log.info("shutdown");
        this.repository.deleteAll();
        this.courseRepository.deleteAll();
        this.studentRepository.deleteAll();
        this.professorRepository.deleteAll();
    }

    @Test
    void itShouldFindByStudentName() {

        if (this.repository.findByStudentName("Paola").isPresent()) {

            List<Registration> registrations = this.repository.findByStudentName("Paola").get();

            assertTrue(registrations.size() > 0);

            Registration registration = registrations.get(0);

            assertTrue(registration.getCourse().getName().equals("Database"));

        }

    }

    @Test
    void itShouldFindByCourseName() {

        if (this.repository.findByCourseName("Database").isPresent()) {

            List<Registration> registrations = this.repository.findByCourseName("Database").get();

            assertTrue(registrations.size() > 0);

            Registration registration = registrations.get(0);

            assertTrue(registration.getStudent().getName().equals("Paola"));

        }

    }

    @Test
    void itShouldFindByStudentIdAndCourseId() {

        if (this.repository.findByStudentIdAndCourseId(1L, 1L).isPresent()) {

            Registration registration = this.repository.findByStudentIdAndCourseId(1L, 1L).get();

            assertTrue(registration.getStudent().getName().equals("Paola"));

        }

    }
}