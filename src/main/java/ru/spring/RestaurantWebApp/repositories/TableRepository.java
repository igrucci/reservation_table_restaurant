package ru.spring.RestaurantWebApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.spring.RestaurantWebApp.models.TableRest;

import java.util.List;

@Repository
public interface TableRepository extends JpaRepository <TableRest, Integer> {
    List<TableRest> findByAvailableAndCapacityGreaterThanEqualAndTime(boolean available, int capacity, String time);

    List<TableRest> findByAvailable(boolean available);
}
