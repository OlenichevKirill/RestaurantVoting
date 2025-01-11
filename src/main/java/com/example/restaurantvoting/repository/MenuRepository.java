package com.example.restaurantvoting.repository;

import com.example.restaurantvoting.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

    @Query("SELECT m FROM Menu m INNER JOIN m.restaurant r WHERE r.id = :restaurantId")
    List<Menu> getAllByRestaurantId(int restaurantId);

    @Query("SELECT m FROM Menu m INNER JOIN m.restaurant r WHERE r.id = :restaurantId AND m.id = :menuId")
    Optional<Menu> getByRestaurantIdAndMenuId(int restaurantId, int menuId);

    @Query("SELECT m FROM Menu m INNER JOIN m.restaurant r WHERE r.id = :restaurantId AND m.dateMenu = :dateMenu")
    Optional<Menu> getByRestaurantIdAndDateMenu(int restaurantId, LocalDate dateMenu);
}
