package it.Plansoft.esercitazionePlansoft.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * interfaccia per i servizi CRUD
 *
 * @param <DTO>
 * @param <ID>
 */
public interface ICrudService<DTO, ID> {

    List<DTO> findAll();

    Page<DTO> findAll(Pageable page);

    Optional<DTO> findById(ID id);

    DTO save(DTO model);

    List<DTO> saveAll(List<DTO> model);

    void delete(DTO model);

    void deleteById(ID id);

    DTO update(DTO model);

}
