package peaksoft.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public UserDetailsService userDetails() {
        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();

        UserDetails admin = userBuilder
                .username("Nurik")
                .password("nurik123")
                .roles("ADMIN")
                .build();

        UserDetails instructor = userBuilder
                .username("Saltanat")
                .password("saltanat123")
                .roles("INSTRUCTOR")
                .build();

        UserDetails student = userBuilder
                .username("Kurstan")
                .password("kurstan123")
                .roles("STUDENT")
                .build();

        return new InMemoryUserDetailsManager(admin, instructor, student);
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/api/users").hasAnyRole("ADMIN", "INSTRUCTOR", "STUDENT")
                .requestMatchers("/api/users/new").hasRole("ADMIN")
                .requestMatchers("/api/users/save").hasRole("ADMIN")
                .requestMatchers("/api/users/{email}/getUser").hasAnyRole("ADMIN", "INSTRUCTOR")
                .requestMatchers("/api/users/{id}/update").hasAnyRole("ADMIN", "INSTRUCTOR")
                .requestMatchers("/api/users/{id}/delete").hasAnyRole("ADMIN", "INSTRUCTOR")
                .and()
                .formLogin()
                .defaultSuccessUrl("/api/users")
                .permitAll();
        return http.build();
    }
}
