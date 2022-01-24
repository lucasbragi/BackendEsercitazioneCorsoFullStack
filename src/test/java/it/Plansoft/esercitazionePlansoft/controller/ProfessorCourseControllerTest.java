package it.Plansoft.esercitazionePlansoft.controller;

import it.Plansoft.esercitazionePlansoft.dto.CourseDto;
import it.Plansoft.esercitazionePlansoft.dto.ProfessorCourseDto;
import it.Plansoft.esercitazionePlansoft.dto.ProfessorDto;
import it.Plansoft.esercitazionePlansoft.service.ProfessorService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@Slf4j
class ProfessorCourseControllerTest {

    @InjectMocks
    ProfessorCourseController controller;

    @Mock
    ProfessorService service;

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
    void itShouldAddAndRemoveCourse() {

        ProfessorCourseDto professorCourseDto = new ProfessorCourseDto(giveProfessorMock(),
                new ArrayList<>(Arrays.asList(giveCourseMock())));

        when(this.service.addCourse(professorCourseDto.getProfessorDto(), professorCourseDto.getCourses()))
                .thenReturn(professorCourseDto);

        ProfessorCourseDto addTest = this.controller.addCourse(professorCourseDto);

        assertEquals(1, addTest.getCourses().size());

        when(this.service.removeCourse(addTest.getProfessorDto(), addTest.getCourses()))
                .thenReturn(new ProfessorCourseDto(
                        addTest.getProfessorDto(),
                        null));

        ProfessorCourseDto removeTest = this.controller.removeCourse(addTest);

        assertNull(removeTest.getCourses());

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