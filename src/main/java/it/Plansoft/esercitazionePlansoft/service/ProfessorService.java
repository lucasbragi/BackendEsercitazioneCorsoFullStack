package it.Plansoft.esercitazionePlansoft.service;

import it.Plansoft.esercitazionePlansoft.dto.CourseDto;
import it.Plansoft.esercitazionePlansoft.dto.ProfessorCourseDto;
import it.Plansoft.esercitazionePlansoft.dto.ProfessorDto;
import it.Plansoft.esercitazionePlansoft.mapper.CycleAvoidingMappingContext;
import it.Plansoft.esercitazionePlansoft.mapper.ICourseMapper;
import it.Plansoft.esercitazionePlansoft.mapper.IProfessorMapper;
import it.Plansoft.esercitazionePlansoft.model.Course;
import it.Plansoft.esercitazionePlansoft.model.Professor;
import it.Plansoft.esercitazionePlansoft.model.Student;
import it.Plansoft.esercitazionePlansoft.repository.CourseRepository;
import it.Plansoft.esercitazionePlansoft.repository.ProfessorRepository;
import it.Plansoft.esercitazionePlansoft.service.interfaces.IProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class ProfessorService extends BaseCrudService<ProfessorRepository, IProfessorMapper, ProfessorDto, Professor, Long>
                                implements IProfessorService {

    @Autowired
    private CourseRepository courseRepository;

    public ProfessorService(ProfessorRepository repository) {
        super(repository, IProfessorMapper.ISTANCE);
    }

    @Override
    public ProfessorDto findProfessorByNumber(String number) {
        Professor professor = this.repository.findProfessorByNumber(number).isPresent() ? this.repository.findProfessorByNumber(number).get() : new Professor();

        return this.mapper.toDto(professor, new CycleAvoidingMappingContext());
    }

    @Override
    public Set<ProfessorDto> findProfessorByName(String name) {
        List<Professor> professors = this.repository.findProfessorByName(name).isPresent() ? this.repository.findProfessorByName(name).get() : new ArrayList<>();

        return this.mapper.toSetDtos(professors, new CycleAvoidingMappingContext());
    }

    @Override
    public Set<ProfessorDto> findProfessorBySurname(String surname) {
        List<Professor> professors = this.repository.findProfessorBySurname(surname).isPresent() ? this.repository.findProfessorBySurname(surname).get() : new ArrayList<>();

        return this.mapper.toSetDtos(professors, new CycleAvoidingMappingContext());
    }

    @Override
    public Set<ProfessorDto> findProfessorByNameAndSurname(String name, String surname) {
        List<Professor> professors = this.repository.findProfessorByNameAndSurname(name, surname).isPresent() ? this.repository.findProfessorByNameAndSurname(name, surname).get() : new ArrayList<>();

        return this.mapper.toSetDtos(professors, new CycleAvoidingMappingContext());
    }

    @Override
    public Set<ProfessorDto> findProfessorByCity(String city) {
        List<Professor> professors = this.repository.findProfessorByCity(city).isPresent() ? this.repository.findProfessorByCity(city).get() : new ArrayList<>();

        return this.mapper.toSetDtos(professors, new CycleAvoidingMappingContext());
    }

    @Override
    public ProfessorCourseDto addCourse(ProfessorDto professorDto, List<CourseDto> courseDtoList) {
        Professor professor = this.mapper.dtoToModel(professorDto);
        ProfessorCourseDto retDto = null;

        if(professor.getId() != null) {
            professor = this.repository.getById(professor.getId());
        } else {
            professor = this.repository.save(professor);
        }

        if (courseDtoList != null) {

            for (CourseDto courseDto : courseDtoList) {

                Course course = ICourseMapper.ISTANCE.dtoToModel(courseDto);

                if (course.getId() != null) {
                    course = courseRepository.getById(course.getId());
                } else {
                    course = courseRepository.save(course);
                }

                professor.addCourses(course);

                // caso speciale: se un professore viene rimosso dal database ed il corso viene assegnato ad un altro professore
                if (course.getProfessor() == null) {
                    course.setProfessor(professor);
                    courseRepository.save(course);
                }

            }

            this.repository.save(professor);


            retDto = readFromProfessor(professor);

        }

        return retDto;
    }

    @Override
    public ProfessorCourseDto removeCourse(ProfessorDto professorDto, List<CourseDto> courseDtoList) {
        Professor professor = this.mapper.dtoToModel(professorDto);
        ProfessorCourseDto retDto = null;

        if(professor.getId() != null) {
            professor = this.repository.getById(professor.getId());
        } else {
            professor = this.repository.save(professor);
        }

        if (courseDtoList != null) {

            for (CourseDto courseDto : courseDtoList) {

                Course course = ICourseMapper.ISTANCE.dtoToModel(courseDto);

                if (course.getId() != null) {
                    course = courseRepository.getById(course.getId());
                    professor.remove(course);
                    course.setProfessor(null);
                    courseRepository.save(course);
                }

            }

            this.repository.save(professor);


            retDto = readFromProfessor(professor);

        }

        return retDto;
    }

    @Override
    public ProfessorCourseDto findByIdProfessor(Long idProfessor) {
        return this.repository.findById(idProfessor)
                .map( s -> {
                    return readFromProfessor(s);
                }).get();
    }

    @Override
    public ProfessorDto updateById(Long id, ProfessorDto model) {
        Optional<Professor> modelById = this.repository.findById(id);

        if (modelById.isPresent()) {
            modelById.get().setName(model.getName());
            modelById.get().setSurname(model.getSurname());
            modelById.get().setBirthdayDate(model.getBirthdayDate());
            modelById.get().setCity(model.getCity());
            modelById.get().setFiscalCode(model.getFiscalCode());
            modelById.get().setNumber(model.getNumber());
            modelById.get().setCap(model.getCap());
            modelById.get().setAddress(model.getAddress());
            modelById.get().setHouseNumber(model.getHouseNumber());
            this.repository.save(modelById.get());
        }

        return  mapper.toDto(modelById.get(), new CycleAvoidingMappingContext());
    }

    private ProfessorCourseDto readFromProfessor(Professor professor) {
        ProfessorCourseDto retDto = new ProfessorCourseDto();
        ProfessorDto professorDto = this.mapper.toDto(professor, new CycleAvoidingMappingContext());

        retDto.setProfessorDto(professorDto);

        professor.getCourses().stream().forEach( x -> {
            CourseDto courseDto = ICourseMapper.ISTANCE.toDto(x, new CycleAvoidingMappingContext());
            retDto.addCourses(courseDto);
        });

        return retDto;
    }


}
