package ru.spring.RestaurantWebApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spring.RestaurantWebApp.models.TableRest;
import ru.spring.RestaurantWebApp.repositories.TableRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TableService {
    private final TableRepository tableRepository;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TableService(TableRepository tableRepository, JdbcTemplate jdbcTemplate) {
        this.tableRepository = tableRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<TableRest> findAll(){
        return tableRepository.findAll();
    }

    public TableRest findOne(int id){
        Optional<TableRest> foundedTable = tableRepository.findById(id);
        return foundedTable.orElse(null);
    }

    @Transactional
    public void save(TableRest table){
        tableRepository.save(table);
    }
    @Transactional
    public void update(int id, TableRest updatedTable){
        updatedTable.setId(id);
        tableRepository.save(updatedTable);
    }
    @Transactional
    public void delete(int id){
        tableRepository.deleteById(id);
    }


    public List<TableRest> getAvailableTables(int capacity, String time) {
        return tableRepository.findByAvailableAndCapacityGreaterThanEqualAndTime(true, capacity, time);
    }

    public List<TableRest> getTrueTables() {
        return tableRepository.findByAvailable(true);
    }


@Transactional
    public void updateTableAvailability(int tableId, boolean isAvailable) {
        String sql = "UPDATE tables SET available = ? WHERE id = ?";
        jdbcTemplate.update(sql, isAvailable, tableId);
    }
}
