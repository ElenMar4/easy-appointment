package ru.marchenko.easy_appointment.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.marchenko.easy_appointment.domain.Appointment;
import ru.marchenko.easy_appointment.domain.AppointmentStatus;
import ru.marchenko.easy_appointment.domain.Customer;
import ru.marchenko.easy_appointment.domain.Entrepreneur;
import ru.marchenko.easy_appointment.domain.dto.*;
import ru.marchenko.easy_appointment.domain.mappers.AppointmentMapper;
import ru.marchenko.easy_appointment.repositories.AppointmentRepository;
import ru.marchenko.easy_appointment.repositories.CustomerRepository;
import ru.marchenko.easy_appointment.repositories.EntrepreneurRepository;
import ru.marchenko.easy_appointment.services.AppointmentService;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentMapper appointmentMapper;
    private final AppointmentRepository appointmentRepository;
    private final EntrepreneurRepository entrepreneurRepository;
    private final CustomerRepository customerRepository;


    @Transactional(readOnly = true)
    @Override
    public List<ShortAppointmentDto> getByDate(long entrepreneurId, String date) {
        List<Appointment> appointments = appointmentRepository.findAllByDateAndEntrepreneurId(date, entrepreneurId);
        return appointments.isEmpty() ? null : appointmentMapper.toDto(appointments);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ShortAppointmentDto> getAppointmentsForCurrentDay(LocalDate date, long entrepreneurId) {
        List<Appointment> appointments = appointmentRepository.findAllByDateAndEntrepreneurId(date.toString(), entrepreneurId);
        return appointmentMapper.toDto(appointments);
    }

    @Override
    @Transactional
    public void saveAllOpenAppointments(List<OpenAppointmentDto> openAppointmentDtoList) {
        List<Appointment> appointments = appointmentMapper.toModel(openAppointmentDtoList);
        appointmentRepository.saveAllAndFlush(appointments);
    }

    @Transactional(readOnly = true)
    @Override
    public AppointmentDto getById(long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
        AppointmentDto dto = appointmentMapper.toDto(appointment);
        return dto;
    }

    @Transactional
    @Override
    public void deleteAllByDate(String date, long entrepreneurId) {
        Entrepreneur entrepreneur = entrepreneurRepository.findById(entrepreneurId).orElseThrow();
        appointmentRepository.deleteByDateAndEntrepreneur(date, entrepreneur);
    }

    @Transactional(readOnly = true)
    @Override
    public List<AppointmentDtoByCustomer> getAllByCustomer(long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow();
        return appointmentMapper.toDtoByCustomer(appointmentRepository.findAllByCustomer(customer));
    }

    @Transactional(readOnly = true)
    @Override
    public List<ShortAppointmentDto> getAllByEntrepreneurAndStatus(long entrepreneurId) {
        Entrepreneur entrepreneur = entrepreneurRepository.findById(entrepreneurId).orElseThrow();
        List<Appointment> appointments = appointmentRepository.findAllByEntrepreneurAndStatus(entrepreneur, AppointmentStatus.OPENED.getStatus());
        return appointmentMapper.toDto(appointments);
    }

    @Transactional
    @Override
    public void save(long appointmentId, long customerId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
        Customer customer = customerRepository.findById(customerId).orElseThrow();
        appointment.setCustomer(customer);
        appointment.setStatus(AppointmentStatus.CLOSED.getStatus());
        appointmentRepository.save(appointment);
    }

    @Transactional
    @Override
    public void cancelAppointment(long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
        appointment.setCustomer(null);
        appointment.setStatus(AppointmentStatus.OPENED.getStatus());
        appointmentRepository.save(appointment);
    }
}
