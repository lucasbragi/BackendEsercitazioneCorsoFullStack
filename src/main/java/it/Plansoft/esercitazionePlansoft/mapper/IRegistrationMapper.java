package it.Plansoft.esercitazionePlansoft.mapper;

import it.Plansoft.esercitazionePlansoft.dto.RegistrationDto;
import it.Plansoft.esercitazionePlansoft.model.Registration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IRegistrationMapper extends IMapper<RegistrationDto, Registration>{

    IRegistrationMapper ISTANCE = Mappers.getMapper(IRegistrationMapper.class);

}
