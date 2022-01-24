package it.Plansoft.esercitazionePlansoft.controller;

import it.Plansoft.esercitazionePlansoft.controller.interfaces.ICourseController;
import it.Plansoft.esercitazionePlansoft.dto.CourseDto;
import it.Plansoft.esercitazionePlansoft.dto.StudentDto;
import it.Plansoft.esercitazionePlansoft.mapper.ICourseMapper;
import it.Plansoft.esercitazionePlansoft.model.Course;
import it.Plansoft.esercitazionePlansoft.repository.CourseRepository;
import it.Plansoft.esercitazionePlansoft.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/course")
public class CourseController extends BaseCrudController<CourseService,
        CourseRepository,
        ICourseMapper,
        CourseDto, Course, Long> implements ICourseController {

    public CourseController(CourseService service) {
        super(service);
    }

    @CrossOrigin
    @Override
    @GetMapping("/findByName")
    public Set<CourseDto> findByName(@RequestParam String name) {
        return this.service.findByName(name);
    }

    @CrossOrigin
    @Override
    @GetMapping("/findByProfessorName")
    public Set<CourseDto> findByProfessorName(@RequestParam String name) {
        return this.service.findByProfessorName(name);
    }

    @CrossOrigin
    @Override
    @PutMapping("/updateCourseById/{id}")
    public CourseDto updateById(@PathVariable Long id, @RequestBody CourseDto DTO) {
        Optional<CourseDto> m = service.findById(id);

        if (m != null && m.get() != null)
            return service.updateById(id, DTO);

        return DTO;
    }
}
