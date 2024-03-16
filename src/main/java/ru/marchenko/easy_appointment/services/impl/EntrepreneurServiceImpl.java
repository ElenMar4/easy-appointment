package ru.marchenko.easy_appointment.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.marchenko.easy_appointment.domain.dto.EntrepreneurCreateDto;
import ru.marchenko.easy_appointment.domain.dto.EntrepreneurDto;
import ru.marchenko.easy_appointment.domain.mappers.EntrepreneurMapper;
import ru.marchenko.easy_appointment.repositories.EntrepreneurRepository;
import ru.marchenko.easy_appointment.services.EntrepreneurService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EntrepreneurServiceImpl implements EntrepreneurService {

    private final EntrepreneurRepository repository;
    private final EntrepreneurMapper mapper;

    @Transactional
    @Override
    public List<EntrepreneurDto> getAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    @Transactional
    @Override
    public void create(EntrepreneurCreateDto entrepreneurCreateDto) {
        repository.save(mapper.toModel(entrepreneurCreateDto));
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    @Transactional
    @Override
    public EntrepreneurDto getById(Long id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow();
    }

    @Transactional
    @Override
    public void update(EntrepreneurDto dto) {
        repository.save(mapper.toModel(dto));
    }
}
