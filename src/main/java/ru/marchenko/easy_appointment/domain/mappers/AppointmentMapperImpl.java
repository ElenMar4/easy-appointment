package ru.marchenko.easy_appointment.domain.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.marchenko.easy_appointment.domain.Appointment;
import ru.marchenko.easy_appointment.domain.AppointmentStatus;
import ru.marchenko.easy_appointment.domain.Customer;
import ru.marchenko.easy_appointment.domain.dto.*;
import ru.marchenko.easy_appointment.repositories.EntrepreneurRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentMapperImpl implements AppointmentMapper{

    private final EntrepreneurRepository entrepreneurRepository;
    private final static int TIME_INTERVAL = 60;

    @Override
    public List<Appointment> toModel(List<OpenAppointmentDto> dtoList) {
        List<Appointment> appointments = new ArrayList<>();
        for(OpenAppointmentDto dto : dtoList){
            Appointment app = new Appointment(dto.getDate(), dto.getStartTime());
            app.setFinishTime(TIME_INTERVAL);
            app.setStatus(AppointmentStatus.OPENED.getStatus());
            app.setEntrepreneur(entrepreneurRepository.findById(dto.getEntrepreneur_id()).orElseThrow());
            appointments.add(app);
        }
        return appointments;
    }

    @Override
    public List<ShortAppointmentDto> toDto(List<Appointment> appointments) {
        List<ShortAppointmentDto> shortAppointments = new ArrayList<>();
        for (Appointment appointment : appointments){
           shortAppointments.add(
                   new ShortAppointmentDto(appointment.getId(), appointment.getDate(),
                           appointment.getStartTime(), appointment.getStatus()));
        }
        return shortAppointments;
    }

    @Override
    public AppointmentDto toDto(Appointment appointment) {
        AppointmentDto dto = new AppointmentDto();
        dto.setId(appointment.getId());
        dto.setDate(appointment.getDate());
        dto.setStatus(appointment.getStatus());
        dto.setStartTime(appointment.getStartTime());
        dto.setFinishTime(appointment.getFinishTime());
        Customer customer = appointment.getCustomer();
        if(customer == null){
            dto.setCustomerName(null);
            dto.setCustomerPhone(null);
        } else {
            dto.setCustomerName(appointment.getCustomer().getName());
            dto.setCustomerPhone(appointment.getCustomer().getPhone());
        }

        return dto;
    }

    @Override
    public List<AppointmentDtoByCustomer> toDtoByCustomer(List<Appointment> appointments) {
        List<AppointmentDtoByCustomer> dtoList = new ArrayList<>();
        for(Appointment app : appointments){
            dtoList.add(new AppointmentDtoByCustomer(
                    app.getId(), app.getDate(), app.getStartTime(), app.getFinishTime(),
                    app.getEntrepreneur().getName(), app.getEntrepreneur().getPhone()
            ));
        }
        return dtoList;
    }
}
