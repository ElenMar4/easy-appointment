package ru.marchenko.easy_appointment.domain.mappers;

import org.springframework.stereotype.Component;
import ru.marchenko.easy_appointment.domain.Appointment;
import ru.marchenko.easy_appointment.domain.dto.*;

import java.util.List;

@Component
public interface AppointmentMapper {

    List<Appointment> toModel(List<OpenAppointmentDto> dtoList);

    List<ShortAppointmentDto> toDto(List<Appointment> appointments);

    AppointmentDto toDto(Appointment appointment);

    List<AppointmentDto> toDtoList (List<Appointment> appointments);

    List<AppointmentDtoByCustomer> toDtoByCustomer(List<Appointment> appointments);
}

