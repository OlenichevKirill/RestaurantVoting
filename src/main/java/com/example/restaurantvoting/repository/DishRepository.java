package com.example.restaurantvoting.repository;

import com.example.restaurantvoting.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DishRepository extends JpaRepository<Dish, Long> {

    @Query("SELECT d FROM Dish d INNER JOIN d.menu m WHERE m.id = :menuId")
    List<Dish> getAllByMenuId(int menuId);

    @Query("SELECT d FROM Dish d INNER JOIN d.menu m WHERE m.id = :menuId AND d.id = :dishId")
    Optional<Dish> getByMenuIdAndDishId(int menuId, int dishId);
}
