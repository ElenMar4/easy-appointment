package ru.marchenko.easy_appointment.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.marchenko.easy_appointment.domain.Customer;
import ru.marchenko.easy_appointment.domain.User;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Customer findByUser(User user);
}
