package it.Plansoft.esercitazionePlansoft.controller;

import it.Plansoft.esercitazionePlansoft.dto.StudentDto;
import it.Plansoft.esercitazionePlansoft.service.StudentService;
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
class StudentControllerTest {

    @InjectMocks
    StudentController controller;

    @Mock
    StudentService service;

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
    void itShouldFindStudentByNumber() {

        when(this.service.findStudentByNumber("64343534")).thenReturn(giveMock().stream().findFirst().get());

        StudentDto studentDtoSet = this.controller.findStudentByNumber("64343534");

        assertTrue(studentDtoSet.getNumber().equals("64343534"));

    }

    @Test
    void itShouldFindStudentByName() {

        when(this.service.findStudentByName("Luca")).thenReturn(giveMock());

        Set<StudentDto> studentDtoSet = this.controller.findStudentByName("Luca");

        assertEquals(1, studentDtoSet.size());

    }

    @Test
    void itShouldFindStudentBySurname() {

        when(this.service.findStudentBySurname("Sbragi")).thenReturn(giveMock());

        Set<StudentDto> studentDtoSet = this.controller.findStudentBySurname("Sbragi");

        assertEquals(1, studentDtoSet.size());

    }

    @Test
    void itShouldFindStudentByNameAndSurname() {

        when(this.service.findStudentByNameAndSurname("Luca", "Sbragi")).thenReturn(giveMock());

        Set<StudentDto> studentDtoSet = this.controller.findStudentByNameAndSurname("Luca", "Sbragi");

        assertEquals(1, studentDtoSet.size());

    }

    @Test
    void itShouldFindStudentByCity() {

        when(this.service.findStudentByCity("Firenze")).thenReturn(giveMock());

        Set<StudentDto> studentDtoSet = this.controller.findStudentByCity("Firenze");

        assertEquals(1, studentDtoSet.size());

    }

    protected Set<StudentDto> giveMock() {

        Set<StudentDto> studentDtoSet = new HashSet<>(Arrays.asList(new StudentDto("Luca",
                "Sbragi",
                LocalDate.of(1998, 10, 18),
                "Firenze",
                "DFGHJ4567",
                "64343534",
                "5654",
                "via di luca",
                "22",
                LocalDate.now())));

        return studentDtoSet;

    }

}