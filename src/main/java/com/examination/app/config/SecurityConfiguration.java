package com.examination.app.config;

import com.examination.app.service.UserDetailsService;
import com.examination.app.util.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// This annotation indicates that this class provides configuration for Spring Security.
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    // Autowired service that provides user details for authentication.
    @Autowired
    private UserDetailsService userService;

    // Autowired filter responsible for JWT authentication.
    @Autowired
    private JwtFilter jwtFilter;

    // Configure the authentication manager to use the userDetailsService.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    // Expose the AuthenticationManager as a Bean.
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // Configure HTTP security, defining which endpoints are accessible without authentication and which require it.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                // Allow access to authentication-related APIs without authentication.
                .antMatchers("/api/auth/**").permitAll()
                // Allow access to other APIs without authentication.
                .antMatchers("/api/**").permitAll()
                // Allow access to Swagger UI and API documentation without authentication.
                .antMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                // Require authentication for any other requests.
                .anyRequest().authenticated()
                // Set session management to STATELESS to ensure no session is used.
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
        // Add JWT filter before the default UsernamePasswordAuthenticationFilter.
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
