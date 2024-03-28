package ru.marchenko.easy_appointment.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.marchenko.easy_appointment.domain.Customer;
import ru.marchenko.easy_appointment.repositories.CustomerRepository;
import ru.marchenko.easy_appointment.services.CustomerService;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private final CustomerRepository customerRepository;

    @Transactional(readOnly = true)
    @Override
    public Customer getById(long id) {
        return customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found"));
    }

    @Transactional
    @Override
    public Customer create() {
        return null;
    }

    @Transactional
    @Override
    public void delete(long id) {

    }
}
