package ru.spring.RestaurantWebApp.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.spring.RestaurantWebApp.models.WorkerRest;

import java.util.Collection;
import java.util.Collections;


public class WorkerDetails implements UserDetails {
    private final WorkerRest workerRest;

    public WorkerDetails(WorkerRest workerRest) {
        this.workerRest = workerRest;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {


        return Collections.singletonList(new SimpleGrantedAuthority(workerRest.getRole()));
    }

    @Override
    public String getPassword() {
        return this.workerRest.getPassword();
    }

    @Override
    public String getUsername() {
        return this.workerRest.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // Нужно, чтобы получать данные аутентифицированного пользователя
    public WorkerRest getPerson() {
        return this.workerRest;
    }
}
