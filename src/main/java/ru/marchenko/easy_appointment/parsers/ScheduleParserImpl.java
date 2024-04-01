package ru.marchenko.easy_appointment.parsers;

import org.springframework.stereotype.Component;
import ru.marchenko.easy_appointment.domain.dto.ScheduleDto;
import ru.marchenko.easy_appointment.domain.dto.ShortAppointmentDto;
import ru.marchenko.easy_appointment.domain.dto.TimeSlotsDto;
import ru.marchenko.easy_appointment.domain.dto.OpenAppointmentDto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


@Component
public class ScheduleParserImpl implements ScheduleParser {

    @Override
    public List<String> buildWeek(LocalDate startDate) {
        List<String> week = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            LocalDate nextDate = startDate.plusDays(i);
            week.add(nextDate.format(DateTimeFormatter.ofPattern("dd-MM-uuuu")));
        }
        return week;
    }

    @Override
    public List<OpenAppointmentDto> generateCurrentSchedule(String date, List<String> selectedCheckBox, long entrepreneur_id) {
        List<OpenAppointmentDto> dtoList = new ArrayList<>();
        for (String check : selectedCheckBox) {
            dtoList.add(new OpenAppointmentDto(date, check, entrepreneur_id));
        }
        return dtoList;
    }

    @Override
    public TimeSlotsDto buildScheduleByDay() {
        return new TimeSlotsDto(List.of("09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00"));
    }

    @Override
    public ScheduleDto buildScheduleWithOpenedAppointment(List<ShortAppointmentDto> dtoList) {

        LocalTime currentTime = LocalTime.now();

        Map<String, List<ShortAppointmentDto>> map = dtoList.stream()
                .filter(dto -> {
                    LocalDate appointmentDate = LocalDate.parse(dto.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    LocalTime appointmentTime = LocalTime.parse(dto.getStartTime(), DateTimeFormatter.ofPattern("HH:mm"));
                    return appointmentDate.isAfter(LocalDate.now()) ||
                            (appointmentDate.equals(LocalDate.now()) && appointmentTime.isAfter(currentTime));
                })
                .collect(Collectors.groupingBy(ShortAppointmentDto::getDate))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        map.forEach((key, value) ->
                value.sort(Comparator.comparing(ShortAppointmentDto::getStartTime)));
        return new ScheduleDto(map);
    }
}
