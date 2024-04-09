package ru.marchenko.easy_appointment.domain.mappers;

import org.springframework.stereotype.Component;
import ru.marchenko.easy_appointment.domain.Customer;
import ru.marchenko.easy_appointment.domain.User;
import ru.marchenko.easy_appointment.domain.dto.CustomerDto;

@Component
public class CustomerMapperImpl implements CustomerMapper{
    @Override
    public Customer toModel(CustomerDto customerDto, User user) {
        return new Customer(null, customerDto.getName(), customerDto.getPhone(), user);
    }
}
