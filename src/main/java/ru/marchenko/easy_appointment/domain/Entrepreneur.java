package ru.marchenko.easy_appointment.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "entrepreneurs")
@Data
public class Entrepreneur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entrepreneur_id")
    private Long id;

    private String appellation;

    private String email;

    private String phone;

    @ManyToMany
    private List<Customer> customerList;

}
