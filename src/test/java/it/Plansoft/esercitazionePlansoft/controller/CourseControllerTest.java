package it.Plansoft.esercitazionePlansoft.controller;

import it.Plansoft.esercitazionePlansoft.dto.CourseDto;
import it.Plansoft.esercitazionePlansoft.dto.ProfessorDto;
import it.Plansoft.esercitazionePlansoft.service.CourseService;
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
class CourseControllerTest {

    @InjectMocks
    CourseController controller;

    @Mock
    CourseService service;

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

        when(this.service.findByName("Computer Science")).thenReturn(giveMoch());

        Set<CourseDto> courseDtoSet = this.controller.findByName("Computer Science");

        assertTrue(courseDtoSet.size() > 0);

    }

    @Test
    void itShouldFindByProfessorName() {

        when(this.service.findByProfessorName("Professore")).thenReturn(giveMoch());

        Set<CourseDto> courseDtoSet = this.controller.findByProfessorName("Professore");

        assertTrue(courseDtoSet.size() > 0);

    }

    protected Set<CourseDto> giveMoch() {

        Set<CourseDto> courseDtoSet = new HashSet<>(Arrays.asList(new CourseDto(
                "Computer Science",
                "Descrizione del corso",
                new ProfessorDto("Professore",
                        "Prova",
                        LocalDate.of(1976, 12, 12),
                        "Milano", "SGHJK3544",
                        "345678",
                        "3454",
                        "via di prova",
                        "22",
                        LocalDate.now()),
                LocalDate.of(2020, 11, 25),
                LocalDate.of(2021, 03, 23),
                LocalDate.now()

        )));

        return courseDtoSet;

    }

}