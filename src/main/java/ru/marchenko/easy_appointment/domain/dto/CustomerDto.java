package ru.marchenko.easy_appointment.domain.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class CustomerDto {

    private String name;
    private String phone;
    private String username;
    private String password;
}
