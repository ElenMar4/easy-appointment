package ru.marchenko.easy_appointment.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final UserDetailsService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/customer/**").hasRole("CUSTOMER")
                        .requestMatchers("/entrepreneur/**").hasRole("ENTREPRENEUR")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/registration").permitAll()
                        .anyRequest().authenticated())
                .userDetailsService(userService)
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/home", true) // Указываем путь после успешного входа
                        .failureUrl("/login?error=true") // Указываем путь в случае ошибки входа
                        .usernameParameter("username") // Параметр имени пользователя в форме входа
                        .passwordParameter("password") // Параметр пароля в форме входа
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .permitAll()
                )

                .httpBasic(Customizer.withDefaults())
                .build();
    }
}