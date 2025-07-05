package com.paulopsms.idp_authenticator.infrastructure.configuration.secutiry;

import com.paulopsms.idp_authenticator.domain.entities.user.UserRoles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

//    @Bean
//    public UserDetailsService registeredUserdata() {
//        UserDetails user1 = User.builder()
//                .username("paulo")
//                .password("{noop}paulo")
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(user1);
//    }

    @Bean
    public SecurityFilterChain securityFilters(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(request -> {
                    request.requestMatchers("/css/**", "/js/**", "/assets/**", "/images/**",
                            "/users/forgot-password").permitAll();
//                    request.requestMatchers("/users/**").hasRole(UserRoles.USER.toString());
                    request.anyRequest().authenticated();
                })
                .formLogin(form -> form.loginPage("/login")
                        .defaultSuccessUrl("/welcome")
                        .permitAll())
                .logout(logout -> logout.logoutSuccessUrl("/login?logout")
                        .permitAll())
                .rememberMe(remember -> remember.key("uniqueAndSecret")
                        .tokenValiditySeconds(60 * 60 * 24 * 7)
                        .alwaysRemember(true))
                .csrf(Customizer.withDefaults())
                .build();
    }

    @Bean
    public PasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }
}
