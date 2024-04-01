package ru.marchenko.easy_appointment.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.marchenko.easy_appointment.domain.Entrepreneur;
import ru.marchenko.easy_appointment.domain.Role;
import ru.marchenko.easy_appointment.domain.User;
import ru.marchenko.easy_appointment.domain.dto.EntrepreneurCreateDto;
import ru.marchenko.easy_appointment.domain.dto.EntrepreneurDto;
import ru.marchenko.easy_appointment.domain.dto.EntrepreneurDtoByCustomer;
import ru.marchenko.easy_appointment.domain.mappers.EntrepreneurMapper;
import ru.marchenko.easy_appointment.exceptions.UserAlreadyExistsException;
import ru.marchenko.easy_appointment.repositories.EntrepreneurRepository;
import ru.marchenko.easy_appointment.repositories.UserRepository;
import ru.marchenko.easy_appointment.services.EntrepreneurService;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EntrepreneurServiceImpl implements EntrepreneurService {

    private final EntrepreneurRepository repository;
    private final EntrepreneurMapper mapper;
    private final UserServiceImpl userService;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public List<EntrepreneurDto> getAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    @Transactional
    @Override
    public void create(EntrepreneurCreateDto entrepreneurCreateDto) throws UserAlreadyExistsException {
        User user = new User(null, entrepreneurCreateDto.getUsername(),
                entrepreneurCreateDto.getPassword(),
                Collections.singleton(Role.ENTREPRENEUR));
        if (userService.saveUser(user)){
            user = userRepository.findByUsername(user.getUsername()).get();
            repository.save(mapper.toModel(entrepreneurCreateDto, user));
        } else {
            throw new UserAlreadyExistsException("Пользователь с именем" + entrepreneurCreateDto.getUsername() + " уже существует");
        }
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        long userId = repository.findById(id).get().getUser().getId();
        userRepository.deleteById(userId);
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public EntrepreneurDto getById(Long id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow();
    }

    @Transactional
    @Override
    public void update(EntrepreneurDto dto) {
        Entrepreneur entrepreneur = repository.findById(dto.getId()).orElseThrow();
        entrepreneur.setName(dto.getName());
        entrepreneur.setTaxNumber(dto.getTaxNumber());
        entrepreneur.setPhone(dto.getPhone());
        repository.save(entrepreneur);
    }

    @Transactional(readOnly = true)
    @Override
    public List<EntrepreneurDtoByCustomer> getAllByCustomer() {
        return mapper.toDto(repository.findAll());
    }

    @Override
    public Entrepreneur getByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        return repository.findByUser(user);
    }
}
