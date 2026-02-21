package com.garden.digital.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Pagine pubbliche
                        .requestMatchers("/", "/index.html", "/static/**", "/api/notes", "/api/notes/search").permitAll()
                        // Pagina privata e azioni di scrittura/cancellazione
                        .requestMatchers("/admin.html", "/api/notes/**").authenticated()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/admin.html", true) // <--- QUESTA è la riga magica
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .permitAll()
                );

        return http.build();
    }

    // Creiamo un utente di prova (admin / password123)
    // Rimuovi o commenta tutto il metodo userDetailsService() che avevamo fatto prima.
// Spring leggerà automaticamente da application.properties se non trova questo Bean.

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/index.html", "/static/**", "/api/notes", "/api/notes/search").permitAll()
                        .requestMatchers("/admin.html", "/api/notes/**").authenticated()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/admin.html", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .permitAll()
                );
        return http.build();
    }
}