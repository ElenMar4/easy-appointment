package ru.marchenko.easy_appointment.domain.mappers;

import ru.marchenko.easy_appointment.domain.Entrepreneur;
import ru.marchenko.easy_appointment.domain.dto.EntrepreneurCreateDto;
import ru.marchenko.easy_appointment.domain.dto.EntrepreneurDto;
import ru.marchenko.easy_appointment.domain.dto.EntrepreneurDtoByCustomer;

import java.util.List;


public interface EntrepreneurMapper {

    EntrepreneurDto toDto(Entrepreneur entrepreneur);

    Entrepreneur toModel(EntrepreneurCreateDto dto);

    Entrepreneur toModel(EntrepreneurDto dto);

    List<EntrepreneurDtoByCustomer> toDto(List<Entrepreneur> entrepreneurs);
}
