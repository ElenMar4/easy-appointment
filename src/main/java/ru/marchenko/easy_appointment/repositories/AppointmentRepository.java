package ru.marchenko.easy_appointment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.marchenko.easy_appointment.domain.Appointment;
import ru.marchenko.easy_appointment.domain.Customer;
import ru.marchenko.easy_appointment.domain.Entrepreneur;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("SELECT a FROM Appointment a WHERE a.entrepreneur.id = :entrepreneurId AND CAST(a.dateTime AS DATE) = :date")
    List<Appointment> findAllByDateAndEntrepreneurId(@Param("date") LocalDate date,
                                                     @Param("entrepreneurId") long entrepreneurId);

    void deleteByDateTimeAndEntrepreneur(LocalDateTime date, Entrepreneur entrepreneur);

    List<Appointment> findAllByCustomer(Customer customer);

    List<Appointment> findAllByEntrepreneurAndStatus(Entrepreneur entrepreneur, String status);

    @Query("SELECT a FROM Appointment a WHERE a.entrepreneur.id = :entrepreneurId AND a.status = :status AND a.dateTime BETWEEN :start AND :finish")
    List<Appointment> findAllByEntrepreneurAndStatusWhenDateTimeBetween(@Param("entrepreneurId") long entrepreneurId,
                                                                        @Param("status") String appointmentStatus,
                                                                        @Param("start") LocalDateTime start,
                                                                        @Param("finish") LocalDateTime finish);
}
