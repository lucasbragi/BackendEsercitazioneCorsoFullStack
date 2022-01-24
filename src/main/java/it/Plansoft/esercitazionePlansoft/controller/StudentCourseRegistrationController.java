package it.Plansoft.esercitazionePlansoft.controller;

import it.Plansoft.esercitazionePlansoft.controller.interfaces.IStudentCourseRegistrationController;
import it.Plansoft.esercitazionePlansoft.dto.StudentCourseRegistrationDto;
import it.Plansoft.esercitazionePlansoft.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courseRegistration")
public class StudentCourseRegistrationController implements IStudentCourseRegistrationController {

    private StudentService service;

    public StudentCourseRegistrationController(StudentService service) {
        this.service = service;
    }

    @CrossOrigin
    @Override
    @PostMapping("/addRegistrationCourse")
    public StudentCourseRegistrationDto addRegistration(@RequestBody StudentCourseRegistrationDto studentCourseRegistrationDto) {
        return this.service.addStudentCourse(studentCourseRegistrationDto.getStudentDto(), studentCourseRegistrationDto.getCourseRegistrationDto());
    }

    @CrossOrigin
    @Override
    @GetMapping("/getRegistrationByStudentId/{id}")
    public StudentCourseRegistrationDto getRegistration(@PathVariable Long id) {
        return this.service.findByIdStudent(id);
    }

    @CrossOrigin
    @Override
    @DeleteMapping("/removeRegistrationCourse/{studentId}/{courseId}")
    public StudentCourseRegistrationDto removeRegistration(@PathVariable Long studentId, @PathVariable Long courseId) {
        return this.service.removeStudentCourse(studentId, courseId);
    }
}
