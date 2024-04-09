package ru.marchenko.easy_appointment.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class EntrepreneurDtoByCustomer {

    private Long id;
    private String name;
    private String phone;
}
