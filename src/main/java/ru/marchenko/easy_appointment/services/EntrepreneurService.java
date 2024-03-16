package ru.marchenko.easy_appointment.services;

import ru.marchenko.easy_appointment.domain.dto.EntrepreneurCreateDto;
import ru.marchenko.easy_appointment.domain.dto.EntrepreneurDto;

import java.util.List;

public interface EntrepreneurService {

    List<EntrepreneurDto> getAll();

    void create(EntrepreneurCreateDto entrepreneurCreateDto);

    void deleteById(Long id);

    EntrepreneurDto getById(Long id);

    void update(EntrepreneurDto dto);
}
