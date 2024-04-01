package ru.marchenko.easy_appointment.domain.mappers;

import ru.marchenko.easy_appointment.domain.Customer;
import ru.marchenko.easy_appointment.domain.User;
import ru.marchenko.easy_appointment.domain.dto.CustomerDto;

public interface CustomerMapper {
    Customer toModel(CustomerDto customerDto, User user);
}
