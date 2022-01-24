package it.Plansoft.esercitazionePlansoft.controller;

import it.Plansoft.esercitazionePlansoft.dto.*;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@Slf4j
class StudentCourseRegistrationControllerTest {

    @InjectMocks
    StudentCourseRegistrationController controller;

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


    /*@Test
    void itShouldAddAndRemoveRegistration() {

        List<CourseRegistrationDto> courseRegistrationDtoList = new ArrayList<>(Arrays.asList(new CourseRegistrationDto(
                giveCourseMock(),
                LocalDate.now()
        )));

        StudentCourseRegistrationDto studentCourseRegistrationDto = new StudentCourseRegistrationDto(
                giveStudentMock(),
                courseRegistrationDtoList);

        when(this.service.addStudentCourse(studentCourseRegistrationDto.getStudentDto(), studentCourseRegistrationDto.getCourseRegistrationDto()))
                .thenReturn(studentCourseRegistrationDto);

        StudentCourseRegistrationDto addTest = this.controller.addRegistration(studentCourseRegistrationDto);

        assertEquals(1, addTest.getCourseRegistrationDto().size());

        when(this.service.removeStudentCourse(addTest.getStudentDto(), addTest.getCourseRegistrationDto())).thenReturn(new StudentCourseRegistrationDto(
                addTest.getStudentDto(),
                null));

        StudentCourseRegistrationDto removeTest = this.controller.removeRegistration(addTest);

        assertNull(removeTest.getCourseRegistrationDto());

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