package it.Plansoft.esercitazionePlansoft.repository;

import it.Plansoft.esercitazionePlansoft.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    @Query("SELECT cr FROM CourseRegistration cr WHERE cr.student.name = ?1")
    Optional<List<Registration>> findByStudentName(String name);

    @Query("SELECT cr FROM CourseRegistration cr WHERE cr.course.name = ?1")
    Optional<List<Registration>> findByCourseName(String name);

    @Query("SELECT cr FROM CourseRegistration cr WHERE cr.student.id = ?1 AND cr.course.id = ?2")
    Optional<Registration> findByStudentIdAndCourseId(Long idStudent, Long idCourse);

}
