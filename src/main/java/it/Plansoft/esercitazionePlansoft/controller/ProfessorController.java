package it.Plansoft.esercitazionePlansoft.controller;

import it.Plansoft.esercitazionePlansoft.controller.interfaces.IProfessorController;
import it.Plansoft.esercitazionePlansoft.dto.ProfessorDto;
import it.Plansoft.esercitazionePlansoft.mapper.IProfessorMapper;
import it.Plansoft.esercitazionePlansoft.model.Professor;
import it.Plansoft.esercitazionePlansoft.repository.ProfessorRepository;
import it.Plansoft.esercitazionePlansoft.service.ProfessorService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/professor")
public class ProfessorController extends BaseCrudController<ProfessorService,
        ProfessorRepository,
        IProfessorMapper,
        ProfessorDto, Professor, Long> implements IProfessorController {

    public ProfessorController(ProfessorService service) {
        super(service);
    }

    @CrossOrigin
    @Override
    @GetMapping("/findByTelephoneNumber")
    public ProfessorDto findProfessorByNumber(@RequestParam String number) {
        return this.service.findProfessorByNumber(number);
    }

    @CrossOrigin
    @Override
    @GetMapping("/findByName")
    public Set<ProfessorDto> findProfessorByName(@RequestParam String name) {
        return this.service.findProfessorByName(name);
    }

    @CrossOrigin
    @Override
    @GetMapping("/findBySurname")
    public Set<ProfessorDto> findProfessorBySurname(@RequestParam String surname) {
        return this.service.findProfessorBySurname(surname);
    }

    @CrossOrigin
    @Override
    @GetMapping("/findByNameAndSurname")
    public Set<ProfessorDto> findProfessorByNameAndSurname(@RequestParam String name, @RequestParam String surname) {
        return this.service.findProfessorByNameAndSurname(name, surname);
    }

    @CrossOrigin
    @Override
    @GetMapping("/findByCity")
    public Set<ProfessorDto> findProfessorByCity(@RequestParam String city) {
        return this.service.findProfessorByCity(city);
    }

    @CrossOrigin
    @Override
    @PutMapping("/updateProfessorById/{id}")
    public ProfessorDto updateById(@PathVariable Long id, @RequestBody ProfessorDto DTO) {
        Optional<ProfessorDto> m = service.findById(id);

        if (m != null && m.get() != null)
            return service.updateById(id, DTO);

        return DTO;
    }
}
