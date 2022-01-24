package it.Plansoft.esercitazionePlansoft.service;

import it.Plansoft.esercitazionePlansoft.dto.RegistrationDto;
import it.Plansoft.esercitazionePlansoft.model.*;
import it.Plansoft.esercitazionePlansoft.repository.RegistrationRepository;
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
class RegistrationServiceTest {

    @InjectMocks
    RegistrationService service;

    @Mock
    RegistrationRepository repository;

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
    void itShouldFindByStudentName() {

        when(this.repository.findByStudentName("Luca")).thenReturn(Optional.of(giveMock()));

        Set<RegistrationDto> registrations = this.service.findByStudentName("Luca");

        assertEquals(1, registrations.size());

    }

    @Test
    void itShouldFindByCourseName() {

        when(this.repository.findByCourseName("Computer Science")).thenReturn(Optional.of(giveMock()));

        Set<RegistrationDto> registrations = this.service.findByCourseName("Computer Science");

        assertEquals(1, registrations.size());

    }

    protected List<Registration> giveMock() {

        List<Registration> registrations = new ArrayList<>(Arrays.asList(new Registration(
                new RegistrationId(1L, 1L),
                new Student("Luca",
                        "Sbragi",
                        LocalDate.of(1998, 10, 18),
                        "Firenze",
                        "DFGHJ4567",
                        "64343534",
                        "5654",
                        "via di luca",
                        "22",
                        LocalDate.now()),
                new Course("Computer Science",
                        "Descrizione del corso",
                        new Professor("Professore", "Prova", LocalDate.of(1976, 12, 12), "Milano", "SGHJK3544", "345678", "3454", "via di prova", "22", LocalDate.now()),
                        LocalDate.of(2020, 11, 25),
                        LocalDate.of(2021, 03, 23),
                        LocalDate.now()),
                LocalDate.now()
        )));

        return registrations;

    }
}