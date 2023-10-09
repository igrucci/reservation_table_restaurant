package ru.spring.RestaurantWebApp.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


@Entity
@Table(name = "tables")
public class TableRest {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "capacity")
    private int capacity;


    @Column(name = "available")

    private boolean available;

    @Column(name = "time")
    @NotEmpty(message = "Email should not be empty")
    @Pattern(regexp="^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]-(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$", message="Некорректный формат времени")
    private String time;

    public TableRest() {

    }
    public TableRest(int id, int capacity, boolean available, String time) {
        this.id = id;
        this.capacity = capacity;
        this.available = available;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "TableRest{" +
                "id=" + id +
                ", capacity=" + capacity +
                ", available=" + available +
                ", time='" + time + '\'' +
                '}';
    }
}