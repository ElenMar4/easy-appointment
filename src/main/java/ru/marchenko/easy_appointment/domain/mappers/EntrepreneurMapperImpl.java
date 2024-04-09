package ru.marchenko.easy_appointment.domain.mappers;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.marchenko.easy_appointment.domain.Entrepreneur;
import ru.marchenko.easy_appointment.domain.Role;
import ru.marchenko.easy_appointment.domain.User;
import ru.marchenko.easy_appointment.domain.dto.EntrepreneurCreateDto;
import ru.marchenko.easy_appointment.domain.dto.EntrepreneurDto;
import ru.marchenko.easy_appointment.domain.dto.EntrepreneurDtoByCustomer;

import java.util.ArrayList;
import java.util.List;

@Component
public class EntrepreneurMapperImpl implements EntrepreneurMapper{

    @Override
    public EntrepreneurDto toDto(Entrepreneur entrepreneur) {
        return new EntrepreneurDto(entrepreneur.getId(), entrepreneur.getName(),
                entrepreneur.getPhone(), entrepreneur.getTaxNumber());
    }

    @Override
    public Entrepreneur toModel(EntrepreneurCreateDto dto, User user) {
        return new Entrepreneur(null, dto.getName(), dto.getPhone(), dto.getTaxNumber(), user);
    }

//    @Override
//    public Entrepreneur toModel(EntrepreneurDto dto) {
//        return new Entrepreneur(dto.getId(), dto.getName(), dto.getPhone(), dto.getTaxNumber());
//    }

    @Override
    public List<EntrepreneurDtoByCustomer> toDto(List<Entrepreneur> entrepreneurs) {
        List<EntrepreneurDtoByCustomer> dtoList = new ArrayList<>();
        for(Entrepreneur entrepreneur : entrepreneurs){
            dtoList.add(new EntrepreneurDtoByCustomer(entrepreneur.getId(), entrepreneur.getName(), entrepreneur.getPhone()));
        }
        return dtoList;
    }
}
