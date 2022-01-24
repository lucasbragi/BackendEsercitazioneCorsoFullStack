package it.Plansoft.esercitazionePlansoft.repository;

import it.Plansoft.esercitazionePlansoft.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.number = ?1")
    Optional<Student> findStudentByNumber(String number);

    @Query("SELECT s FROM Student s WHERE s.name = ?1")
    Optional<List<Student>> findStudentByName(String name);

    @Query("SELECT s FROM Student s WHERE s.surname = ?1")
    Optional<List<Student>> findStudentBySurname(String surname);

    @Query("SELECT s FROM Student s WHERE s.name = ?1 AND s.surname = ?2")
    Optional<List<Student>> findStudentByNameAndSurname(String name, String surname);

    @Query("SELECT s FROM Student s WHERE s.city = ?1")
    Optional<List<Student>> findStudentByCity(String city);

}
