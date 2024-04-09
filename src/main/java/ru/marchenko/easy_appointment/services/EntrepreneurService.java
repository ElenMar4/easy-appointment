package ru.marchenko.easy_appointment.services;

import ru.marchenko.easy_appointment.domain.Entrepreneur;
import ru.marchenko.easy_appointment.domain.dto.EntrepreneurCreateDto;
import ru.marchenko.easy_appointment.domain.dto.EntrepreneurDto;
import ru.marchenko.easy_appointment.domain.dto.EntrepreneurDtoByCustomer;
import ru.marchenko.easy_appointment.exceptions.UserAlreadyExistsException;

import java.util.List;

public interface EntrepreneurService {

    List<EntrepreneurDto> getAll();

    void create(EntrepreneurCreateDto entrepreneurCreateDto) throws UserAlreadyExistsException;

    void deleteById(Long id);

    EntrepreneurDto getById(Long id);

    void update(EntrepreneurDto dto);

    List<EntrepreneurDtoByCustomer> getAllByCustomer();

    Entrepreneur getByUsername(String username);
}
