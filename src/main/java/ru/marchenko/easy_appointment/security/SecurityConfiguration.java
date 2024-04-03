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
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final UserDetailsService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .authorizeHttpRequests(request -> request

                        .requestMatchers("/emails/text").permitAll()
                        .requestMatchers("/customer/**").hasRole("CUSTOMER")
                        .requestMatchers("/entrepreneur/**").hasRole("ENTREPRENEUR")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/registration").permitAll()

                        .anyRequest().permitAll())
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