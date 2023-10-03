package ru.spring.RestaurantWebApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.spring.RestaurantWebApp.models.WorkerRest;
import ru.spring.RestaurantWebApp.repositories.WorkerRepository;
import ru.spring.RestaurantWebApp.security.WorkerDetails;

import java.util.Optional;


@Service
public class WorkerDetailsService implements UserDetailsService {

    private final WorkerRepository workerRepository;

    @Autowired
    public WorkerDetailsService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<WorkerRest> person = workerRepository.findByUsername(s);

        if (person.isEmpty())
            throw new UsernameNotFoundException("User not found");

        return new WorkerDetails(person.get());
    }
}
