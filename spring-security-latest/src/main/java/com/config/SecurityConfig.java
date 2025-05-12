 package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.filter.JwtAuthFilter;

import jakarta.ws.rs.HttpMethod;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private JwtAuthFilter authFilter;

    //authentication
    @Bean
    UserDetailsService userDetailsService() {
        return new UserInfoUserDetailsService();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**").permitAll() // Public access for authentication routes
                
                // ADMIN access: Full control on user roles, travel packages, booking, payments, and reviews
                .requestMatchers(HttpMethod.GET, "/userRoles/**", "/travelPackage/**", "/booking/**", "/payment/**", "/review/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/userRoles/**").hasRole("ADMIN")

                // TRAVEL_AGENT access: Full CRUD on travel packages, booking (GET & PUT), payments (GET), and reviews (GET)
                .requestMatchers(HttpMethod.GET, "/travelPackage/**", "/booking/**", "/payment/**", "/review/**").hasRole("TRAVEL_AGENT")
                .requestMatchers(HttpMethod.POST, "/travelPackage/**").hasRole("TRAVEL_AGENT")
                .requestMatchers(HttpMethod.PUT, "/travelPackage/**", "/booking/**").hasRole("TRAVEL_AGENT")
                .requestMatchers(HttpMethod.DELETE, "/travelPackage/**").hasRole("TRAVEL_AGENT")

                // CUSTOMER access: GET access on travel packages, full CRUD on booking, payment (GET & POST), and review (GET & POST)
                .requestMatchers(HttpMethod.GET, "/travelPackage/**", "/booking/**", "/payment/**", "/review/**").hasRole("CUSTOMER")
                .requestMatchers(HttpMethod.POST, "/booking/**", "/payment/**", "/review/**").hasRole("CUSTOMER")
                .requestMatchers(HttpMethod.PUT, "/booking/**").hasRole("CUSTOMER")
                .requestMatchers(HttpMethod.DELETE, "/booking/**").hasRole("CUSTOMER")

                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }



    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
