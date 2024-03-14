package ru.marchenko.easy_appointment.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.marchenko.easy_appointment.domain.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
