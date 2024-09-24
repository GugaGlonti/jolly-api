package de.gugaglonti.jollyapi.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  @Bean
  SecurityFilterChain securityFilterChain(
      HttpSecurity http
  ) throws Exception {
    http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    http
        .cors(WebSecurityConfig::enable)
        .csrf(AbstractHttpConfigurer::disable)
        .formLogin(AbstractHttpConfigurer::disable)
        .sessionManagement(sessionManagementConfigurer -> sessionManagementConfigurer
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .securityMatcher("/**")
        .authorizeHttpRequests(registry -> registry
            .requestMatchers("/").permitAll()
            .requestMatchers("/auth/sign-in").permitAll()
            .requestMatchers("/auth/sign-up").permitAll()
            .anyRequest().authenticated()
        );

    return http.build();
  }

  private static void enable(CorsConfigurer<HttpSecurity> corsConfigurer) {
    corsConfigurer.configurationSource(corsConfigurationSource -> {
      CorsConfiguration corsConfiguration = new CorsConfiguration();
      corsConfiguration.addAllowedOrigin("*");
      corsConfiguration.addAllowedMethod("*");
      corsConfiguration.addAllowedHeader("*");
      return corsConfiguration;
    });
  }
}
