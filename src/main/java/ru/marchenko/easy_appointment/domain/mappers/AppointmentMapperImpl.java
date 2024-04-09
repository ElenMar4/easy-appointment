package ru.marchenko.easy_appointment.domain.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.marchenko.easy_appointment.domain.Appointment;
import ru.marchenko.easy_appointment.domain.AppointmentStatus;
import ru.marchenko.easy_appointment.domain.Customer;
import ru.marchenko.easy_appointment.domain.dto.*;
import ru.marchenko.easy_appointment.repositories.EntrepreneurRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
            String dateTimeStr = dto.getDate() + " " + dto.getStartTime();
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm"));
            Appointment app = new Appointment(dateTime);
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
            String date = appointment.getDateTime().format(DateTimeFormatter.ofPattern("dd-MM-uuuu"));
            String time = appointment.getDateTime().format(DateTimeFormatter.ofPattern("HH:mm"));
           shortAppointments.add(
                   new ShortAppointmentDto(appointment.getId(), date,
                          time, appointment.getStatus()));
        }
        return shortAppointments;
    }

    @Override
    public AppointmentDto toDto(Appointment appointment) {
        String date = appointment.getDateTime().format(DateTimeFormatter.ofPattern("dd-MM-uuuu"));
        String time = appointment.getDateTime().format(DateTimeFormatter.ofPattern("HH:mm"));
        String finishTime = appointment.getFinishTime().format(DateTimeFormatter.ofPattern("HH:mm"));
        AppointmentDto dto = new AppointmentDto();
        dto.setId(appointment.getId());
        dto.setDate(date);
        dto.setStatus(appointment.getStatus());
        dto.setStartTime(time);
        dto.setFinishTime(finishTime);
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
    public List<AppointmentDto> toDtoList(List<Appointment> appointments) {
        List<AppointmentDto> dtoList = new ArrayList<>();
        for (Appointment app : appointments){
            String date = app.getDateTime().format(DateTimeFormatter.ofPattern("dd-MM-uuuu"));
            String time = app.getDateTime().format(DateTimeFormatter.ofPattern("HH:mm"));
            String finishTime = app.getFinishTime().format(DateTimeFormatter.ofPattern("HH:mm"));
            AppointmentDto dto = new AppointmentDto();
            dto.setId(app.getId());
            dto.setDate(date);
            dto.setStatus(app.getStatus());
            dto.setStartTime(time);
            dto.setFinishTime(finishTime);
            Customer customer = app.getCustomer();
            if(customer == null){
                dto.setCustomerName(null);
                dto.setCustomerPhone(null);
            } else {
                dto.setCustomerName(app.getCustomer().getName());
                dto.setCustomerPhone(app.getCustomer().getPhone());
            }
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public List<AppointmentDtoByCustomer> toDtoByCustomer(List<Appointment> appointments) {
        List<AppointmentDtoByCustomer> dtoList = new ArrayList<>();
        for(Appointment app : appointments){
            String date = app.getDateTime().format(DateTimeFormatter.ofPattern("dd-MM-uuuu"));
            String time = app.getDateTime().format(DateTimeFormatter.ofPattern("HH:mm"));
            String finishTime = app.getFinishTime().format(DateTimeFormatter.ofPattern("HH:mm"));
            dtoList.add(new AppointmentDtoByCustomer(
                    app.getId(), date, time, finishTime,
                    app.getEntrepreneur().getName(), app.getEntrepreneur().getPhone()
            ));
        }
        return dtoList;
    }
}
