package it.Plansoft.esercitazionePlansoft.controller;

import it.Plansoft.esercitazionePlansoft.controller.interfaces.ICrudController;
import it.Plansoft.esercitazionePlansoft.mapper.IMapper;
import it.Plansoft.esercitazionePlansoft.model.BaseId;
import it.Plansoft.esercitazionePlansoft.service.BaseCrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * classe implementazione crud
 *
 * @param <SERVICE>
 * @param <REPOSITORY>
 * @param <MODEL>
 * @param <ID>
 */
public class BaseCrudController<
        SERVICE extends BaseCrudService<REPOSITORY, MAPPER, DTO, MODEL, ID>,
        REPOSITORY extends JpaRepository<MODEL, ID>,
        MAPPER extends IMapper<DTO, MODEL>,
        DTO extends BaseId<ID>,
        MODEL extends BaseId<ID>,
        ID>
        implements ICrudController<DTO, ID> {

    protected SERVICE service;

    public BaseCrudController(SERVICE service) {
        this.service = service;
    }

    // ?page=0&size=2&sort=createdAt,desc
    @CrossOrigin
    @GetMapping("/paged")
    public ResponseEntity<Page<DTO>> findAll(Pageable pageable) {
        Page<DTO> page = service.findAll(pageable);
        return ResponseEntity.ok(page);
    }

    @CrossOrigin
    @Override
    @GetMapping("/findAll")
    public ResponseEntity<List<DTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @CrossOrigin
    @Override
    @GetMapping("/findById/{id}")
    public ResponseEntity<Optional<DTO>> findById(@PathVariable ID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @CrossOrigin
    @Override
    @PostMapping("/save")
    public ResponseEntity<DTO> save(@RequestBody DTO model) {
        return ResponseEntity.ok(service.save(model));
    }


    @CrossOrigin
    @Override
    @PostMapping("/saveAll")
    public ResponseEntity<List<DTO>> saveAll(@RequestBody List<DTO> model) {
        return ResponseEntity.ok(service.saveAll(model));
    }

    @CrossOrigin
    @Override
    @DeleteMapping("/delete")
    public ResponseEntity<Optional<DTO>> delete(@RequestBody DTO model) {
        Optional<DTO> m = service.findById(model.getId());

        if (m != null && m.get() != null)
            service.delete(model);

        return ResponseEntity.ok(m);
    }

    @CrossOrigin
    @Override
    @DeleteMapping ("/deleteById/{id}")
    public ResponseEntity<Optional<DTO>> deleteById(@PathVariable ID id) {
        Optional<DTO> m = service.findById(id);

        if (m != null && m.get() != null)
            service.deleteById(id);

        return ResponseEntity.ok(m);
    }

    @CrossOrigin
    @Override
    @PutMapping("/update")
    public ResponseEntity<DTO> update(@RequestBody DTO model) {
        return ResponseEntity.ok(service.update(model));
    }

}