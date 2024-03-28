package ru.marchenko.easy_appointment.services;

import ru.marchenko.easy_appointment.domain.Customer;

import java.util.List;

public interface CustomerService {

    Customer getById (long id);

    Customer create ();

    void delete(long id);
}
