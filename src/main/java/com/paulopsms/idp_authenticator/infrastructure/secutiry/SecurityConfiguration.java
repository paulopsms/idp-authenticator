package com.paulopsms.idp_authenticator.infrastructure.secutiry;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
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

    //    @Bean
//    public SecurityFilterChain securityFilters(HttpSecurity http) throws Exception {
//        return http.authorizeHttpRequests(request -> {
//                    request.requestMatchers("/css/**", "/js/**", "/assets/**", "/images/**").permitAll();
//                    request.anyRequest().authenticated();
//                })
//                .formLogin(form -> form.loginPage("/login")
//                        .defaultSuccessUrl("/welcome")
//                        .permitAll())
//                .logout(logout -> logout.logoutSuccessUrl("/login?logout")
//                        .permitAll())
//                .rememberMe(remember -> remember.key("uniqueAndSecret")
//                        .tokenValiditySeconds(60 * 60 * 24 * 7)
//                        .alwaysRemember(true))
//                .csrf(Customizer.withDefaults())
//                .build();
//    }
    @Bean
    public PasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }
}
