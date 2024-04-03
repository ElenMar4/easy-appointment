package ru.marchenko.easy_appointment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.marchenko.easy_appointment.domain.Appointment;
import ru.marchenko.easy_appointment.domain.Notification;
import ru.marchenko.easy_appointment.domain.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query("SELECT n FROM Notification n WHERE n.status = :status AND n.date_time < :currentDateTime")
    List<Notification> getAllByStatusAndDateTimeLessThan(@Param("status") Status status,
                                                         @Param("currentDateTime") LocalDateTime currentDateTime);

    Notification findByAppointment(Appointment appointment);

    void deleteByAppointment(Appointment appointment);
}
