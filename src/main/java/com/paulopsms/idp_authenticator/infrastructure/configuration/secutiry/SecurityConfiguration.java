package com.paulopsms.idp_authenticator.infrastructure.configuration.secutiry;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.paulopsms.idp_authenticator.domain.entities.user.UserRole.FRESH_USER;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
public class SecurityConfiguration {

    private final JwtFilter jwtFilter;

    public SecurityConfiguration(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

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
//                    request.requestMatchers("/users/forgot-password").permitAll();
//                    request.requestMatchers(HttpMethod.POST, "/users").permitAll();
//                    request.requestMatchers(HttpMethod.POST, "/users/password-recovery").permitAll();
////                    request.requestMatchers("/users/**").hasRole(UserRoles.USER.toString());
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

    /// /                .csrf(Customizer.withDefaults())
//                .csrf(AbstractHttpConfigurer::disable)
//                .build();
//    }

    @Bean
    public SecurityFilterChain securityRestFilters(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(req -> {
                    req.requestMatchers("/login").permitAll();
                    req.requestMatchers(POST, "/users").permitAll();
                    req.requestMatchers("/refresh-token").permitAll();
                    req.requestMatchers(POST, "/users/password-recovery").permitAll();
                    req.requestMatchers(POST, "/users/verify-account").hasRole(FRESH_USER.name());
                    req.requestMatchers(GET, "/users").hasRole(FRESH_USER.name());
//                    req.requestMatchers("/api/**").permitAll();
                    req.anyRequest().authenticated();
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        String hierarchy = "ADMIN > VERIFIED_USER\n" +
                "VERIFIED_USER > FRESH_USER";
        return RoleHierarchyImpl.fromHierarchy(hierarchy);
    }
}
