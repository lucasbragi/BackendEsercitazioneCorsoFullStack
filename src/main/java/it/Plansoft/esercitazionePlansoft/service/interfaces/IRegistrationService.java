package it.Plansoft.esercitazionePlansoft.service.interfaces;

import it.Plansoft.esercitazionePlansoft.dto.RegistrationDto;

import java.util.Set;

public interface IRegistrationService {

    Set<RegistrationDto> findByStudentName(String name);

    Set<RegistrationDto> findByCourseName(String name);

}
