package ru.marchenko.easy_appointment.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class EntrepreneurDto {

    @NotNull
    private Long id;

    @NotBlank(message = "Name field should not be blank")
    @Size(min = 3, message = "Значение должно содержать минимум 3 символа")
    private String name;

    @NotBlank(message = "Phone field should not be blank")
    private String phone;

    @NotBlank(message = "Tax number field should not be blank")
    private String taxNumber;
}
