package ru.marchenko.easy_appointment.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.marchenko.easy_appointment.domain.User;

public interface UserService extends UserDetailsService {
    boolean saveUser(User user);
    User findByUsername(String username);
}
