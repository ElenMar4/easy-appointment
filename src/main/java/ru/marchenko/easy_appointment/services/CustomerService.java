package ru.marchenko.easy_appointment.services;

import ru.marchenko.easy_appointment.domain.Customer;
import ru.marchenko.easy_appointment.domain.dto.CustomerDto;
import ru.marchenko.easy_appointment.exceptions.UserAlreadyExistsException;

public interface CustomerService {

    void create (CustomerDto customerDto) throws UserAlreadyExistsException;

    Customer getByUsername(String username);
}
