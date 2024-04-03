package ru.marchenko.easy_appointment.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date_time")
    private LocalDateTime date_time;

//    @Column(name = "time")
//    private LocalTime time;

    @OneToOne
    private Appointment appointment;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
}