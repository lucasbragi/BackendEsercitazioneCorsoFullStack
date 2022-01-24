package it.Plansoft.esercitazionePlansoft.controller;

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
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@Slf4j
class ProfessorControllerTest {

    @InjectMocks
    ProfessorController controller;

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
    void itShouldFindProfessorByNumber() {

        when(this.service.findProfessorByNumber("345678")).thenReturn(giveMock().stream().findFirst().get());

        ProfessorDto professorDto = this.controller.findProfessorByNumber("345678");

        assertTrue(professorDto.getNumber().equals("345678"));

    }

    @Test
    void itShouldFindProfessorByName() {

        when(this.service.findProfessorByName("Professore")).thenReturn(giveMock());

        Set<ProfessorDto> professorDtoSet = this.controller.findProfessorByName("Professore");

        assertEquals(1, professorDtoSet.size());

    }

    @Test
    void itShouldFindProfessorBySurname() {

        when(this.service.findProfessorBySurname("Prova")).thenReturn(giveMock());

        Set<ProfessorDto> professorDtoSet = this.controller.findProfessorBySurname("Prova");

        assertEquals(1, professorDtoSet.size());

    }

    @Test
    void itShouldFindProfessorByNameAndSurname() {

        when(this.service.findProfessorByNameAndSurname("Professore","Prova")).thenReturn(giveMock());

        Set<ProfessorDto> professorDtoSet = this.controller.findProfessorByNameAndSurname("Professore","Prova");

        assertEquals(1, professorDtoSet.size());

    }

    @Test
    void itShouldFindProfessorByCity() {

        when(this.service.findProfessorByCity("Milano")).thenReturn(giveMock());

        Set<ProfessorDto> professorDtoSet = this.controller.findProfessorByCity("Milano");

        assertEquals(1, professorDtoSet.size());

    }

    protected Set<ProfessorDto> giveMock() {

        Set<ProfessorDto> professorDtoSet = new HashSet<>(Arrays.asList(
                new ProfessorDto("Professore",
                        "Prova",
                        LocalDate.of(1976, 12, 12),
                        "Milano", "SGHJK3544",
                        "345678",
                        "3454",
                        "via di prova",
                        "22",
                        LocalDate.now())
        ));

        return professorDtoSet;

    }

}