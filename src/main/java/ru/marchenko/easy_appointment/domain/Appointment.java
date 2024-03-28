package ru.marchenko.easy_appointment.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Data
@RequiredArgsConstructor
@Table(name = "appointments")
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private long id;

    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "start_time", nullable = false)
    private String startTime;

    @Column(name = "finish_time", nullable = false)
    private String finishTime;

    @ManyToOne
    private Entrepreneur entrepreneur;

    @ManyToOne
    private Customer customer;

    @Column(name = "status", nullable = false)
    private String status;

    public Appointment(String date, String startTime) {
        this.date = date;
        this.startTime = startTime;
    }

    public void setFinishTime(int interval) {
        LocalTime start = LocalTime.parse(startTime, DateTimeFormatter.ofPattern("HH:mm"));
        this.finishTime = start.plusMinutes(interval).toString();
    }
}
