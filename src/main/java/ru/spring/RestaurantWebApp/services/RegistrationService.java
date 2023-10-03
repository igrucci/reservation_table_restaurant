package ru.spring.RestaurantWebApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spring.RestaurantWebApp.models.WorkerRest;
import ru.spring.RestaurantWebApp.repositories.WorkerRepository;


@Service
public class RegistrationService {

    private final WorkerRepository workerRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(WorkerRepository workerRepository, PasswordEncoder passwordEncoder) {
        this.workerRepository = workerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(WorkerRest workerRest) {
        workerRest.setPassword(passwordEncoder.encode(workerRest.getPassword()));
        workerRest.setRole("ROLE_USER");
        workerRepository.save(workerRest);
    }
}
