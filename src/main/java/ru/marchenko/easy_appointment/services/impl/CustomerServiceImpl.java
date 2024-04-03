package ru.marchenko.easy_appointment.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.marchenko.easy_appointment.domain.Customer;
import ru.marchenko.easy_appointment.domain.Role;
import ru.marchenko.easy_appointment.domain.User;
import ru.marchenko.easy_appointment.domain.dto.CustomerDto;
import ru.marchenko.easy_appointment.domain.mappers.CustomerMapper;
import ru.marchenko.easy_appointment.exceptions.UserAlreadyExistsException;
import ru.marchenko.easy_appointment.repositories.CustomerRepository;
import ru.marchenko.easy_appointment.repositories.UserRepository;
import ru.marchenko.easy_appointment.services.CustomerService;
import ru.marchenko.easy_appointment.services.UserService;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final CustomerMapper customerMapper;
    private final UserService userService;

    @Transactional
    @Override
    public void create(CustomerDto customerDto) throws UserAlreadyExistsException {
        User user = new User(null, customerDto.getUsername(),
                customerDto.getPassword(),
                Collections.singleton(Role.CUSTOMER));
        if(userService.saveUser(user)){
            Customer customer = customerMapper.toModel(customerDto, user);
            customerRepository.save(customer);
        } else {
            throw new UserAlreadyExistsException("Пользователь с именем" + customerDto.getUsername() + " уже существует");
        }
    }

    @Transactional
    @Override
    public Customer getByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        return customerRepository.findByUser(user);
    }
}
