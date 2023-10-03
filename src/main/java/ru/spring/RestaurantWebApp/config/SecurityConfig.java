package ru.spring.RestaurantWebApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.spring.RestaurantWebApp.services.WorkerDetailsService;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final WorkerDetailsService workerDetailsService;

    @Autowired
    public SecurityConfig(WorkerDetailsService workerDetailsService) {
        this.workerDetailsService = workerDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/auth/login", "/auth/registration", "/error",
                        "/restaurant", "/restaurant/book", "/restaurant/select",
                        "/restaurant/confirmation", "restaurant/cancel", "/restaurant/home").permitAll()
                .antMatchers("/office", "/office/admin", "/office/worker",
                      "/people", "people/edit", "people/index", "/people/index_worker",
                        "/people/new", "/people/show", "/table",
                        "/table/edit", "/table/index", "/table/index_worker",
                        "/table/new", "table/show").hasRole("ADMIN")
                .antMatchers("/office/worker", "/table/index_worker", "/table/index_worker").hasAnyRole("USER", "ADMIN")

                .and()
                .formLogin().loginPage("/auth/login")
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/office/admin", true)
                .failureUrl("/auth/login?error")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/auth/login");
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(workerDetailsService)
                .passwordEncoder(getPasswordEncoder());
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
