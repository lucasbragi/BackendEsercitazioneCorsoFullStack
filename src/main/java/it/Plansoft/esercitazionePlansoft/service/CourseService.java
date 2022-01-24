package it.Plansoft.esercitazionePlansoft.service;

import it.Plansoft.esercitazionePlansoft.dto.CourseDto;
import it.Plansoft.esercitazionePlansoft.mapper.CycleAvoidingMappingContext;
import it.Plansoft.esercitazionePlansoft.mapper.ICourseMapper;
import it.Plansoft.esercitazionePlansoft.mapper.IProfessorMapper;
import it.Plansoft.esercitazionePlansoft.model.Course;
import it.Plansoft.esercitazionePlansoft.model.Student;
import it.Plansoft.esercitazionePlansoft.repository.CourseRepository;
import it.Plansoft.esercitazionePlansoft.service.interfaces.ICourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class CourseService extends BaseCrudService<CourseRepository, ICourseMapper, CourseDto, Course, Long>
                            implements ICourseService {

    public CourseService(CourseRepository repository) {
        super(repository, ICourseMapper.ISTANCE);
    }

    @Override
    public Set<CourseDto> findByName(String name) {
        List<Course> courses = this.repository.findByName(name).isPresent() ? this.repository.findByName(name).get() : new ArrayList<>();

        return this.mapper.toSetDtos(courses, new CycleAvoidingMappingContext());
    }

    @Override
    public Set<CourseDto> findByProfessorName(String name) {
        List<Course> courses = this.repository.findByProfessorName(name).isPresent() ? this.repository.findByProfessorName(name).get() : new ArrayList<>();

        return this.mapper.toSetDtos(courses, new CycleAvoidingMappingContext());
    }

    @Override
    public CourseDto updateById(Long id, CourseDto model) {
        Optional<Course> modelById = this.repository.findById(id);

        if (modelById.isPresent()) {
            modelById.get().setName(model.getName());
            modelById.get().setDescription(model.getDescription());
            modelById.get().setProfessor(IProfessorMapper.ISTANCE.dtoToModel(model.getProfessor()));
            modelById.get().setStartDate(model.getStartDate());
            modelById.get().setEndDate(model.getEndDate());
            this.repository.save(modelById.get());
        }

        return  mapper.toDto(modelById.get(), new CycleAvoidingMappingContext());
    }

}
