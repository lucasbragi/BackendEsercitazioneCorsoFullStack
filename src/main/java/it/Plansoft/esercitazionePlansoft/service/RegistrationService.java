package it.Plansoft.esercitazionePlansoft.service;

import it.Plansoft.esercitazionePlansoft.dto.RegistrationDto;
import it.Plansoft.esercitazionePlansoft.mapper.CycleAvoidingMappingContext;
import it.Plansoft.esercitazionePlansoft.mapper.IRegistrationMapper;
import it.Plansoft.esercitazionePlansoft.model.Registration;
import it.Plansoft.esercitazionePlansoft.repository.RegistrationRepository;
import it.Plansoft.esercitazionePlansoft.service.interfaces.IRegistrationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class RegistrationService extends BaseCrudService<RegistrationRepository, IRegistrationMapper, RegistrationDto, Registration, Long>
                                        implements IRegistrationService {

    public RegistrationService(RegistrationRepository repository) {
        super(repository, it.Plansoft.esercitazionePlansoft.mapper.IRegistrationMapper.ISTANCE);
    }

    @Override
    public Set<RegistrationDto> findByStudentName(String name) {
        List<Registration> courseRegistrations = this.repository.findByStudentName(name).isPresent() ? this.repository.findByStudentName(name).get() : new ArrayList<>();

        return this.mapper.toSetDtos(courseRegistrations, new CycleAvoidingMappingContext());
    }

    @Override
    public Set<RegistrationDto> findByCourseName(String name) {
        List<Registration> courseRegistrations = this.repository.findByCourseName(name).isPresent() ? this.repository.findByCourseName(name).get() : new ArrayList<>();

        return this.mapper.toSetDtos(courseRegistrations, new CycleAvoidingMappingContext());
    }

}
