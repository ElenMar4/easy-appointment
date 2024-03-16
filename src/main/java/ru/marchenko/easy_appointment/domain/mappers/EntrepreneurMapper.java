package ru.marchenko.easy_appointment.domain.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.marchenko.easy_appointment.domain.Entrepreneur;
import ru.marchenko.easy_appointment.domain.dto.EntrepreneurCreateDto;
import ru.marchenko.easy_appointment.domain.dto.EntrepreneurDto;

@Mapper(componentModel = "spring")
public interface EntrepreneurMapper {
    EntrepreneurDto toDto(Entrepreneur entrepreneur);

    @Mappings({
            @Mapping(target = "id", expression = "java(null)"),
            @Mapping(target = "name", source = "dto.name"),
            @Mapping(target = "phone", source = "dto.phone"),
            @Mapping(target = "taxNumber", source = "dto.taxNumber")
    })
    Entrepreneur toModel(EntrepreneurCreateDto dto);

    Entrepreneur toModel(EntrepreneurDto dto);
}
