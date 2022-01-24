package it.Plansoft.esercitazionePlansoft.service;

import it.Plansoft.esercitazionePlansoft.dto.CourseDto;
import it.Plansoft.esercitazionePlansoft.dto.CourseRegistrationDto;
import it.Plansoft.esercitazionePlansoft.dto.StudentCourseRegistrationDto;
import it.Plansoft.esercitazionePlansoft.dto.StudentDto;
import it.Plansoft.esercitazionePlansoft.mapper.CycleAvoidingMappingContext;
import it.Plansoft.esercitazionePlansoft.mapper.ICourseMapper;
import it.Plansoft.esercitazionePlansoft.mapper.IStudentMapper;
import it.Plansoft.esercitazionePlansoft.model.Course;
import it.Plansoft.esercitazionePlansoft.model.Registration;
import it.Plansoft.esercitazionePlansoft.model.RegistrationId;
import it.Plansoft.esercitazionePlansoft.model.Student;
import it.Plansoft.esercitazionePlansoft.repository.RegistrationRepository;
import it.Plansoft.esercitazionePlansoft.repository.CourseRepository;
import it.Plansoft.esercitazionePlansoft.repository.StudentRepository;
import it.Plansoft.esercitazionePlansoft.service.interfaces.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class StudentService extends BaseCrudService<StudentRepository, IStudentMapper, StudentDto, Student, Long>
                            implements IStudentService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

    public StudentService(StudentRepository repository) {

        super(repository, IStudentMapper.ISTANCE);

    }

    @Override
    public StudentDto findStudentByNumber(String number) {
        Student student = this.repository.findStudentByNumber(number).isPresent() ? this.repository.findStudentByNumber(number).get() : new Student();

        return this.mapper.toDto(student, new CycleAvoidingMappingContext());
    }

    @Override
    public Set<StudentDto> findStudentByName(String name) {
        List<Student> students = this.repository.findStudentByName(name).isPresent() ? this.repository.findStudentByName(name).get() : new ArrayList<>();

        return this.mapper.toSetDtos(students, new CycleAvoidingMappingContext());
    }

    @Override
    public Set<StudentDto> findStudentBySurname(String surname) {
        List<Student> students = this.repository.findStudentBySurname(surname).isPresent() ? this.repository.findStudentBySurname(surname).get() : new ArrayList<>();

        return this.mapper.toSetDtos(students, new CycleAvoidingMappingContext());
    }

    @Override
    public Set<StudentDto> findStudentByNameAndSurname(String name, String surname) {
        List<Student> students = this.repository.findStudentByNameAndSurname(name, surname).isPresent() ? this.repository.findStudentByNameAndSurname(name, surname).get() : new ArrayList<>();

        return this.mapper.toSetDtos(students, new CycleAvoidingMappingContext());
    }

    @Override
    public Set<StudentDto> findStudentByCity(String city) {
        List<Student> students = this.repository.findStudentByCity(city).isPresent() ? this.repository.findStudentByCity(city).get() : new ArrayList<>();

        return this.mapper.toSetDtos(students, new CycleAvoidingMappingContext());
    }

    @Override
    public StudentCourseRegistrationDto addStudentCourse(StudentDto studentDto, List<CourseRegistrationDto> courseRegistrationDtoList) {
        Student student = this.mapper.dtoToModel(studentDto);
        StudentCourseRegistrationDto retDto = null;

        // check if student exists
        if (student.getId() != null) {
            student = this.repository.getById(student.getId());
        } else {
            student = this.repository.save(student);
        }

        if (courseRegistrationDtoList != null) {

            for (CourseRegistrationDto courseRegistrationDto : courseRegistrationDtoList) {

                Course course = ICourseMapper.ISTANCE.dtoToModel(courseRegistrationDto.getCourseDto());

                if (course.getId() != null) {
                    course = courseRepository.getById(course.getId());
                } else {
                    course = courseRepository.save(course);
                }

                // save the registration
                RegistrationId registrationId = new RegistrationId(student.getId(), course.getId());
                Registration registration = registrationRepository.save(new Registration(registrationId,
                        student,
                        course,
                        courseRegistrationDto.getCreatedAt() != null ? courseRegistrationDto.getCreatedAt() : LocalDate.now()));

                // add registration
                student.addRegistration(registration);

            }

            // save the student
            student = this.repository.save(student);

            retDto = readFromStudent(student);

        }

        return retDto;
    }

    @Override
    public StudentCourseRegistrationDto removeStudentCourse(Long studentId, Long courseId) {
        Student student = this.repository.getById(studentId);
        Course course = courseRepository.getById(courseId);
        StudentCourseRegistrationDto retDto = null;

        if (registrationRepository.findByStudentIdAndCourseId(studentId, courseId).isPresent()) {
            Registration regFound = registrationRepository.findByStudentIdAndCourseId(student.getId(), course.getId()).get();
            registrationRepository.delete(regFound);

            // delete registration
            student.removeRegistration(regFound);
        }

        // save the student
        student = this.repository.save(student);

        retDto = readFromStudent(student);

        return retDto;
    }

    @Override
    public StudentCourseRegistrationDto findByIdStudent(Long idStudent) {
        return this.repository.findById(idStudent).map( s -> readFromStudent(s)).get();
    }

    private StudentCourseRegistrationDto readFromStudent(Student student) {
        StudentCourseRegistrationDto dto = new StudentCourseRegistrationDto();
        StudentDto studentDto = this.mapper.toDto(student, new CycleAvoidingMappingContext());

        dto.setStudentDto(studentDto);

        // convert data to dto
        student.getRegistrations().stream().forEach( x -> {
            CourseDto courseDto = ICourseMapper.ISTANCE.toDto(x.getCourse(), new CycleAvoidingMappingContext());
            dto.addCourse(courseDto, x.getRegistrationDate());
        });

        return dto;
    }


    @Override
    public StudentDto updateById(Long id, StudentDto model) {
        Optional<Student> modelById = this.repository.findById(id);

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


}
