package com.example.restaurantvoting.service;

import com.example.restaurantvoting.model.Menu;
import com.example.restaurantvoting.repository.MenuRepository;
import com.example.restaurantvoting.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
@Transactional(readOnly = true)
public class MenuService {

    private MenuRepository menuRepository;
    private RestaurantRepository restaurantRepository;

    public List<Menu> getAllByRestaurantId(int restaurantId) {
        return menuRepository.getAllByRestaurantId(restaurantId);
    }

    @Transactional
    public Menu save(Menu menu, int restaurantId) {
        menu.setRestaurant(restaurantRepository.getReferenceById(restaurantId));
        return menuRepository.save(menu);
    }

    public Optional<Menu> getByRestaurantIdAndMenuId(int restaurantId, int menuId) {
        return menuRepository.getByRestaurantIdAndMenuId(restaurantId, menuId);
    }

    public Optional<Menu> getByRestaurantIdAndDateMenu(int restaurantId, LocalDate dateMenu) {
        return menuRepository.getByRestaurantIdAndDateMenu(restaurantId, dateMenu);
    }
}
