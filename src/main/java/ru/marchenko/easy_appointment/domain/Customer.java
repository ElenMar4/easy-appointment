package ru.marchenko.easy_appointment.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Table(name = "customers")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

//    @OneToOne
//    @JoinColumn(name = "user_id")
//    private User user;

    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

}
