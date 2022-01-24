package it.Plansoft.esercitazionePlansoft.repository;

import it.Plansoft.esercitazionePlansoft.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT c FROM Course c WHERE c.name = ?1")
    Optional<List<Course>> findByName(String name);

    @Query("SELECT c FROM Course c WHERE c.professor.name = ?1")
    Optional<List<Course>> findByProfessorName(String name);

}
