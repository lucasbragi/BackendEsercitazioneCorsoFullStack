package it.Plansoft.esercitazionePlansoft.controller;

import it.Plansoft.esercitazionePlansoft.controller.interfaces.IStudentController;
import it.Plansoft.esercitazionePlansoft.dto.StudentDto;
import it.Plansoft.esercitazionePlansoft.mapper.IStudentMapper;
import it.Plansoft.esercitazionePlansoft.model.Student;
import it.Plansoft.esercitazionePlansoft.repository.StudentRepository;
import it.Plansoft.esercitazionePlansoft.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/student")
public class StudentController extends BaseCrudController<StudentService,
        StudentRepository,
        IStudentMapper,
        StudentDto,
        Student,
        Long> implements IStudentController {

    public StudentController(StudentService service) {
        super(service);
    }

    @CrossOrigin
    @Override
    @GetMapping("/findStudentByNumber")
    public StudentDto findStudentByNumber(@RequestParam String number) {
        return this.service.findStudentByNumber(number);
    }

    @CrossOrigin
    @Override
    @GetMapping("/findStudentByName")
    public Set<StudentDto> findStudentByName(@RequestParam String name) {
        return this.service.findStudentByName(name);
    }

    @CrossOrigin
    @Override
    @GetMapping("/findStudentBySurname")
    public Set<StudentDto> findStudentBySurname(@RequestParam String surname) {
        return this.service.findStudentBySurname(surname);
    }

    @CrossOrigin
    @Override
    @GetMapping("/findStudentByNameAndSurname")
    public Set<StudentDto> findStudentByNameAndSurname(@RequestParam String name, @RequestParam String surname) {
        return this.service.findStudentByNameAndSurname(name, surname);
    }

    @CrossOrigin
    @Override
    @GetMapping("/findStudentByCity")
    public Set<StudentDto> findStudentByCity(@RequestParam String city) {
        return this.service.findStudentByCity(city);
    }

    @CrossOrigin
    @Override
    @PutMapping("/updateStudentById/{id}")
    public StudentDto updateById(@PathVariable Long id, @RequestBody StudentDto DTO) {
        Optional<StudentDto> m = service.findById(id);

        if (m != null && m.get() != null)
            return service.updateById(id, DTO);

        return DTO;
    }
}
