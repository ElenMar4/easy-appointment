package ru.marchenko.easy_appointment.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "entrepreneurs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entrepreneur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    @Column(name = "tax_number", nullable = false, unique = true)
    private String taxNumber;

    //    @OneToOne
//    @JoinColumn(name = "user_id")
//    private User user;

}
