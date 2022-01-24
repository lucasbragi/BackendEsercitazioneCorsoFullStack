package it.Plansoft.esercitazionePlansoft.controller;

import it.Plansoft.esercitazionePlansoft.controller.interfaces.IProfessorCourseController;
import it.Plansoft.esercitazionePlansoft.dto.ProfessorCourseDto;
import it.Plansoft.esercitazionePlansoft.service.ProfessorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courseProfessorRegistration")
public class ProfessorCourseController implements IProfessorCourseController {

    private ProfessorService service;

    public ProfessorCourseController(ProfessorService service) {
        this.service = service;
    }

    @CrossOrigin
    @Override
    @PostMapping("/addCourseToProfessor")
    public ProfessorCourseDto addCourse(@RequestBody ProfessorCourseDto professorCourseDto) {
        return this.service.addCourse(professorCourseDto.getProfessorDto(), professorCourseDto.getCourses());
    }

    @CrossOrigin
    @Override
    @DeleteMapping("/removeCourse")
    public ProfessorCourseDto removeCourse(@RequestBody ProfessorCourseDto professorCourseDto) {
        return this.service.removeCourse(professorCourseDto.getProfessorDto(), professorCourseDto.getCourses());
    }

    @CrossOrigin
    @Override
    @GetMapping("/getCoursesByProfessorId/{id}")
    public ProfessorCourseDto getCourses(@PathVariable Long id) {
        return this.service.findByIdProfessor(id);
    }
}
