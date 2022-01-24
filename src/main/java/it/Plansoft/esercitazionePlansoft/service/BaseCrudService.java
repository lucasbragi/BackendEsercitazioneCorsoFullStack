package it.Plansoft.esercitazionePlansoft.service;

import it.Plansoft.esercitazionePlansoft.mapper.CycleAvoidingMappingContext;
import it.Plansoft.esercitazionePlansoft.mapper.IMapper;
import it.Plansoft.esercitazionePlansoft.service.interfaces.ICrudService;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public class BaseCrudService<
        REPOSITORY extends JpaRepository<MODEL, ID>,
        MAPPER extends IMapper<DTO, MODEL>,
        DTO,
        MODEL,
        ID> implements ICrudService<DTO, ID> {

    protected REPOSITORY repository;
    protected MAPPER mapper;


    public BaseCrudService(REPOSITORY repository, MAPPER mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<DTO> findAll() {
        // convert model to DTO
        return mapper.toDtos(repository.findAll(), new CycleAvoidingMappingContext());
    }

    @Override
    public Page<DTO> findAll(Pageable page) {
        Page<MODEL> modelPage = repository.findAll(page);
        // converting page MODEL --> page DTO
        return convertPagetoDtos(modelPage);
    }

    @Override
    public Optional<DTO> findById(ID id) {
        Optional<MODEL> byId = repository.findById(id);
        return Optional.of(mapper.toDto(byId.isPresent() ? byId.get() : null, new CycleAvoidingMappingContext()));
    }

    @Override
    public DTO save(DTO model) {
        return mapper.toDto(repository.save(mapper.dtoToModel(model)), new CycleAvoidingMappingContext());
    }

    @Override
    public List<DTO> saveAll(List<DTO> model) {
        List<DTO> mout = new ArrayList<>();
        for (DTO m : model) {
            mout.add(mapper.toDto(repository.save(mapper.dtoToModel(m)), new CycleAvoidingMappingContext()));
        }
        return mout;
    }

    @Override
    public void delete(DTO model) {
        repository.delete(mapper.dtoToModel(model));
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public DTO update(DTO model) {
        return mapper.toDto(repository.save(mapper.dtoToModel(model)), new CycleAvoidingMappingContext());
    }

    /**
     * convert pageable model to pageable dto.
     */
    private Page<DTO> convertPagetoDtos(Page<MODEL> models) {
        Page<DTO> p = new PageImpl(models.getContent(), models.getPageable(), models.getTotalPages());

        return p;
    }
}
