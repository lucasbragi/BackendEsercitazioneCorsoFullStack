package it.Plansoft.esercitazionePlansoft.service;

import it.Plansoft.esercitazionePlansoft.dto.*;
import it.Plansoft.esercitazionePlansoft.model.*;
import it.Plansoft.esercitazionePlansoft.repository.CourseRepository;
import it.Plansoft.esercitazionePlansoft.repository.ProfessorRepository;
import it.Plansoft.esercitazionePlansoft.repository.RegistrationRepository;
import it.Plansoft.esercitazionePlansoft.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j

class StudentServiceTest {

    @Autowired
    StudentService service;

    @Autowired
    StudentRepository repository;

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    RegistrationRepository registrationRepository;

    @BeforeEach
    public void startup() {
        log.info("startup");
        loadDatabase();
    }

    @AfterEach
    public void shutdown() {
        log.info("shutdown");
    }

    protected void loadDatabase() {

        Student student = new Student("Luca",
                "Sbragi",
                LocalDate.of(1998, 10, 18),
                "Firenze",
                "DFGHJ4567",
                "64343534",
                "5654",
                "via di luca",
                "22",
                LocalDate.now());

        student.setId(1L);

        this.repository.save(student);

        Professor professor = new Professor(
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
        );

        this.professorRepository.save(professor);

        Course course = new Course("Database",
                "Questo corso insegna database",
                professor,
                LocalDate.of(2022, 02, 10),
                LocalDate.of(2022, 06, 18),
                LocalDate.of(2021, 11, 22)
        );

        this.courseRepository.save(course);

    }

    @Test
    void itShouldFindStudentByNumber() {

        StudentDto student = this.service.findStudentByNumber("64343534");

        assertTrue(student.getNumber().equals("64343534"));

    }

    @Test
    void itShouldFindStudentByName() {

        Set<StudentDto> studentDtoSet = this.service.findStudentByName("Luca");

        assertEquals(1, studentDtoSet.size());

    }

    @Test
    void itShouldFindStudentBySurname() {

        Set<StudentDto> studentDtoSet = this.service.findStudentBySurname("Sbragi");

        assertEquals(1, studentDtoSet.size());

    }

    @Test
    void itShouldFindStudentByNameAndSurname() {

        Set<StudentDto> studentDtoSet = this.service.findStudentByNameAndSurname("Luca", "Sbragi");

        assertEquals(1, studentDtoSet.size());

    }

    @Test
    void itShouldFindStudentByCity() {

        Set<StudentDto> studentDtoSet = this.service.findStudentByCity("Firenze");

        assertEquals(1, studentDtoSet.size());

    }

    /*@Test
    void itShouldAddAndRemoveCourse() {

        // Mock
        StudentDto studentDto = giveStudentMock();

        CourseDto courseDto = giveCourseMock();

        List<CourseRegistrationDto> courseRegistrationDtoList = new ArrayList<>(Arrays.asList(new CourseRegistrationDto(
                courseDto,
                LocalDate.now()
        )));

        // Test
        StudentCourseRegistrationDto studentCourseRegistrationDto = this.service.addStudentCourse(studentDto, courseRegistrationDtoList);

        List<CourseRegistrationDto> courses = studentCourseRegistrationDto.getCourseRegistrationDto();

        // Controllo che dentro courses ci sia un solo elemento
        // Se dentro courses c'è un elemento, vuol dire che è stato aggiunto un corso al libretto dello studente
        assertNotNull(courses);
        assertEquals(1, courses.size());

        // Faccio l'operazione inversa
        // Rimuovo l'unico corso associato allo studente.
        // Quando estraggo courses, questa volta devo avere una lista nulla
        studentCourseRegistrationDto = this.service.removeStudentCourse(studentDto, courses);

        courses = studentCourseRegistrationDto.getCourseRegistrationDto();

        assertNull(courses);

    }*/

    protected StudentDto giveStudentMock() {

        StudentDto studentDto = new StudentDto("Luca",
                "Sbragi",
                LocalDate.of(1998, 10, 18),
                "Firenze",
                "DFGHJ4567",
                "64343534",
                "5654",
                "via di luca",
                "22",
                LocalDate.now());

        studentDto.setId(1L);

        return studentDto;
    }

    protected CourseDto giveCourseMock() {

        CourseDto courseDto = new CourseDto(
                "Database",
                "Questo corso insegna database",
                new ProfessorDto(
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
                ),
                LocalDate.of(2022, 02, 10),
                LocalDate.of(2022, 06, 18),
                LocalDate.of(2021, 11, 22)
        );

        courseDto.setId(1L);

        return courseDto;

    }

}