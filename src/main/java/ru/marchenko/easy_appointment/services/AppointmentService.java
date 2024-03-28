package ru.marchenko.easy_appointment.services;

import ru.marchenko.easy_appointment.domain.dto.*;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentService {
    void saveAllOpenAppointments(List<OpenAppointmentDto> openAppointmentDtoList);
    List<ShortAppointmentDto> getAppointmentsForCurrentDay(LocalDate date, long entrepreneurId);

    AppointmentDto getById(long appointmentId);

    List<ShortAppointmentDto> getByDate(long entrepreneurId, String date);

    void deleteAllByDate(String date, long entrepreneurId);

    List<AppointmentDtoByCustomer> getAllByCustomer(long customerId);

    List<ShortAppointmentDto> getAllByEntrepreneurAndStatus(long entrepreneurId);

    void save(long appointmentId, long customerId);

    void cancelAppointment(long appointmentId);
}