package ru.marchenko.easy_appointment.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@RequiredArgsConstructor
@Table(name = "appointments")
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name="date_time")
    private LocalDateTime dateTime;

    @Column(name="finish_time")
    private LocalTime finishTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private Entrepreneur entrepreneur;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @Column(name = "status", nullable = false)
    private String status;

    public Appointment(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setFinishTime(int interval){
       LocalTime start = dateTime.toLocalTime();
       this.finishTime = start.plusMinutes(interval);
    }
}
