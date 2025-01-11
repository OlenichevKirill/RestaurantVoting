package com.example.restaurantvoting.repository;

import com.example.restaurantvoting.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Query("SELECT r FROM Restaurant r INNER JOIN r.menus m WHERE m.dateMenu = :dateMenu")
    List<Restaurant> getAllByDateMenu(LocalDate dateMenu);
}
