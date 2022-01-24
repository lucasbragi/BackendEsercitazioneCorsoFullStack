package it.Plansoft.esercitazionePlansoft.service;

import it.Plansoft.esercitazionePlansoft.dto.CourseDto;
import it.Plansoft.esercitazionePlansoft.model.Course;
import it.Plansoft.esercitazionePlansoft.model.Professor;
import it.Plansoft.esercitazionePlansoft.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@Slf4j
class CourseServiceTest {

    @InjectMocks
    CourseService service;

    @Mock
    CourseRepository repository;

    @Before
    public void startup() {
        log.info("startup");
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void shutdown() {
        log.info("shutdown");
    }

    @Test
    void itShouldFindByName() {

        when(this.repository.findByName("Computer Science")).thenReturn(Optional.of(giveMock()));

        Set<CourseDto> courses = this.service.findByName("Computer Science");

        assertEquals(1, courses.size());

    }

    @Test
    void itShouldFindByProfessorName() {

        when(this.repository.findByProfessorName("Professore")).thenReturn(Optional.of(giveMock()));

        Set<CourseDto> courses = this.service.findByProfessorName("Professore");

        assertEquals(1, courses.size());

    }

    protected List<Course> giveMock() {

        List<Course> courses = new ArrayList<>(Arrays.asList(new Course("Computer Science",
                "Descrizione del corso",
                new Professor("Professore", "Prova", LocalDate.of(1976, 12, 12), "Milano", "SGHJK3544", "345678", "3454", "via di prova", "22", LocalDate.now()),
                LocalDate.of(2020, 11, 25),
                LocalDate.of(2021, 03, 23),
                LocalDate.now()
                )));

        return courses;
    }
}