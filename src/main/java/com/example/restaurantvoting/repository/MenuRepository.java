package com.example.restaurantvoting.repository;

import com.example.restaurantvoting.model.Menu;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    @Query("SELECT m FROM Menu m INNER JOIN m.restaurant r WHERE r.id = :restaurantId ORDER BY m.dateMenu DESC")
    List<Menu> getAllByRestaurantId(int restaurantId);

    @Query("SELECT m FROM Menu m INNER JOIN m.restaurant r WHERE r.id = :restaurantId AND m.id = :menuId")
    @EntityGraph(attributePaths = {"menuItems"})
    Optional<Menu> getByRestaurantIdAndMenuId(int restaurantId, int menuId);

    @Query("SELECT m FROM Menu m INNER JOIN m.restaurant r WHERE r.id = :restaurantId AND m.dateMenu = :dateMenu")
    @EntityGraph(attributePaths = {"menuItems"})
    Optional<Menu> getByRestaurantIdAndDateMenu(int restaurantId, LocalDate dateMenu);

    @Transactional
    @Modifying
    @Query("DELETE FROM Menu m WHERE m.id=:menuId and m.restaurant.id =:restaurantId")
    int delete(int restaurantId, int menuId);
}
