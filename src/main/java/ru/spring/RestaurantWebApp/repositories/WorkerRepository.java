package ru.spring.RestaurantWebApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.spring.RestaurantWebApp.models.WorkerRest;

import java.util.Optional;


@Repository
public interface WorkerRepository extends JpaRepository<WorkerRest, Integer> {
    Optional<WorkerRest> findByUsername(String username);
}
