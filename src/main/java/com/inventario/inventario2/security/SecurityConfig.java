package com.inventario.inventario2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .authorizeHttpRequests(auth -> auth
        .requestMatchers("/productos/nuevo", "/productos/guardar", "/productos/eliminar/**").hasRole("ADMIN")
        .requestMatchers("/categorias/**").hasRole("ADMIN")
        .requestMatchers("/productos/**").hasAnyRole("ADMIN", "USER")
        .anyRequest().authenticated()
      )
      .formLogin(form -> form
        .defaultSuccessUrl("/productos", true)   // ← CAMBIO CLAVE
        .permitAll()
      )
      .logout(withDefaults());
    return http.build();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    UserDetails admin = User.withUsername("admin")
      .password(passwordEncoder().encode("admin123"))
      .roles("ADMIN")
      .build();
    UserDetails user = User.withUsername("user")
      .password(passwordEncoder().encode("user123"))
      .roles("USER")
      .build();
    return new InMemoryUserDetailsManager(admin, user);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
