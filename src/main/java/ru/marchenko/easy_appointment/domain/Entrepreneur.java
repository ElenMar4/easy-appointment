package ru.marchenko.easy_appointment.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "entrepreneurs")
@Data
public class Entrepreneur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entrepreneur_id")
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

//    @ManyToMany(targetEntity = Customer.class, fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinTable(name = "entrepreneur_customer", joinColumns = @JoinColumn(name = "entrepreneur_id"),
//            inverseJoinColumns = @JoinColumn(name = "customer_id"))
//    private List<Customer> customers;
}
