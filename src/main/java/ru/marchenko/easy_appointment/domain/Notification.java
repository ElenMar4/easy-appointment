package ru.marchenko.easy_appointment.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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

    @OneToOne(fetch = FetchType.LAZY)
    private Appointment appointment;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
}