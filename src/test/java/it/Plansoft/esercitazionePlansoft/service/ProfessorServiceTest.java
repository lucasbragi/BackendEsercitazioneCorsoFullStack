package it.Plansoft.esercitazionePlansoft.service;

import it.Plansoft.esercitazionePlansoft.dto.CourseDto;
import it.Plansoft.esercitazionePlansoft.dto.ProfessorCourseDto;
import it.Plansoft.esercitazionePlansoft.dto.ProfessorDto;
import it.Plansoft.esercitazionePlansoft.model.Course;
import it.Plansoft.esercitazionePlansoft.model.Professor;
import it.Plansoft.esercitazionePlansoft.repository.CourseRepository;
import it.Plansoft.esercitazionePlansoft.repository.ProfessorRepository;
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
class ProfessorServiceTest {

    @Autowired
    ProfessorService service;

    @Autowired
    ProfessorRepository repository;

    @Autowired
    CourseRepository courseRepository;

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

        Professor professor = new Professor(
                "Professore",
                "Prova",
                LocalDate.of(1976, 12, 12),
                "Milano",
                "SGHJK3544",
                "345678",
                "3454",
                "via di prova",
                "22",
                LocalDate.now()
        );

        professor.setId(1L);

        this.repository.save(professor);

        Course course = new Course(
                "Database",
                "Questo corso insegna database",
                null,
                LocalDate.of(2022, 02, 10),
                LocalDate.of(2022, 06, 18),
                LocalDate.of(2021, 11, 22)
        );

        course.setId(1L);

        this.courseRepository.save(course);

    }

    @Test
    void itShouldFindProfessorByNumber() {

        ProfessorDto professorDto = this.service.findProfessorByNumber("345678");

        assertEquals("Professore", professorDto.getName());

    }

    @Test
    void itShouldFindProfessorByName() {

        Set<ProfessorDto> professorDtoList = this.service.findProfessorByName("Professore");

        assertEquals(1, professorDtoList.size());

    }

    @Test
    void itShouldFindProfessorBySurname() {

        Set<ProfessorDto> professorDtoList = this.service.findProfessorBySurname("Prova");

        assertEquals(1, professorDtoList.size());

    }

    @Test
    void itShouldFindProfessorByNameAndSurname() {

        Set<ProfessorDto> professorDtoList = this.service.findProfessorByNameAndSurname("Professore", "Prova");

        assertEquals(1, professorDtoList.size());

    }

    @Test
    void itShouldFindProfessorByCity() {

        Set<ProfessorDto> professorDtoList = this.service.findProfessorByCity("Milano");

        assertEquals(1, professorDtoList.size());

    }

    @Test
    void itShouldAddAndRemoveCourse() {

        // Mock

        ProfessorDto professorDto = giveProfessorMock();

        CourseDto courseDto = giveCourseMock();

        List<CourseDto> courseDtoList = new ArrayList<>(Arrays.asList(courseDto));

        // Test
        ProfessorCourseDto professorCourseDto = this.service.addCourse(professorDto, courseDtoList);

        List<CourseDto> courseDtoTest = professorCourseDto.getCourses();

        // Controllo che il professore abbia associato un corso
        assertNotNull(courseDtoTest);
        assertEquals(1, courseDtoTest.size());

        // Faccio l'operazione inversa
        // Rimuovo un corso dal professore, perciò devo ottenere una lista con valore null quando estraggo courses

        professorCourseDto = this.service.removeCourse(professorDto, courseDtoList);
        courseDtoTest = professorCourseDto.getCourses();

        assertNull(courseDtoTest);

    }

    protected ProfessorDto giveProfessorMock() {

        ProfessorDto professor = new ProfessorDto("Professore",
                        "Prova",
                        LocalDate.of(1976, 12, 12),
                        "Milano",
                        "SGHJK3544",
                        "345678",
                        "3454",
                        "via di prova",
                        "22",
                        LocalDate.now());

        professor.setId(1L);

        return professor;
    }

    protected CourseDto giveCourseMock() {

        CourseDto courseDto = new CourseDto(
                "Database",
                "Questo corso insegna database",
                null,
                LocalDate.of(2022, 02, 10),
                LocalDate.of(2022, 06, 18),
                LocalDate.of(2021, 11, 22)
        );

        courseDto.setId(1L);

        return courseDto;

    }


}