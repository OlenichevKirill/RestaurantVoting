package com.example.restaurantvoting.repository;

import com.example.restaurantvoting.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    @Query("SELECT mi FROM MenuItem mi INNER JOIN mi.menu m WHERE m.id = :menuId")
    List<MenuItem> getAllByMenuId(int menuId);

    @Query("SELECT mi FROM MenuItem mi INNER JOIN mi.menu m WHERE m.id = :menuId AND mi.id = :menuItemId")
    Optional<MenuItem> getByMenuIdAndMenuItemId(int menuId, int menuItemId);
}
