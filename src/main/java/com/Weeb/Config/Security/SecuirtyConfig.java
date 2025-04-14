package com.Weeb.Config.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfigurationSource;


@Component
@EnableWebSecurity
public class SecuirtyConfig {
    private final CorsConfigurationSource configurationSource;

    public SecuirtyConfig(CorsConfigurationSource configurationSource) {
        this.configurationSource = configurationSource;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,TokenFilter jwtTokenFilter) throws Exception{
        return http
                .cors(t->t.configurationSource(configurationSource)).csrf(csrf->csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/ws**").permitAll()
                    .requestMatchers("/api/auth/register","/api/auth/login").permitAll()
                    .anyRequest().permitAll())  
                .addFilterBefore(jwtTokenFilter,UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
