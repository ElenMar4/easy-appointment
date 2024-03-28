package ru.marchenko.easy_appointment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.marchenko.easy_appointment.domain.Appointment;
import ru.marchenko.easy_appointment.domain.Customer;
import ru.marchenko.easy_appointment.domain.Entrepreneur;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findAllByDateAndEntrepreneurId(String date, long entrepreneurId);

    void deleteByDateAndEntrepreneur(String date, Entrepreneur entrepreneur);

    List<Appointment> findAllByCustomer(Customer customer);

    List<Appointment> findAllByEntrepreneurAndStatus(Entrepreneur entrepreneur, String status);
}
