package it.Plansoft.esercitazionePlansoft.repository;

import it.Plansoft.esercitazionePlansoft.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    @Query("SELECT p FROM Professor p WHERE p.number = ?1")
    Optional<Professor> findProfessorByNumber(String number);

    @Query("SELECT p FROM Professor p WHERE p.name = ?1")
    Optional<List<Professor>> findProfessorByName(String name);

    @Query("SELECT p FROM Professor p WHERE p.surname = ?1")
    Optional<List<Professor>> findProfessorBySurname(String surname);

    @Query("SELECT p FROM Professor p WHERE p.name = ?1 AND p.surname = ?2")
    Optional<List<Professor>> findProfessorByNameAndSurname(String name, String surname);

    @Query("SELECT p FROM Professor p WHERE p.city = ?1")
    Optional<List<Professor>> findProfessorByCity(String city);

}
