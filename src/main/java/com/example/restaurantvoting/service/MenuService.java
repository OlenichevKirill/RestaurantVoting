package com.example.restaurantvoting.service;

import com.example.restaurantvoting.model.Menu;
import com.example.restaurantvoting.model.Restaurant;
import com.example.restaurantvoting.repository.MenuRepository;
import com.example.restaurantvoting.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static com.example.restaurantvoting.util.validation.ValidationUtil.checkNotFound;

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
        Restaurant restaurant = checkNotFound(restaurantRepository.getReferenceById(restaurantId), restaurantId);
        menu.setRestaurant(restaurant);
        return menuRepository.save(menu);
    }

    public Menu getByRestaurantIdAndMenuId(int restaurantId, int menuId) {
        return checkNotFound(menuRepository.getByRestaurantIdAndMenuId(restaurantId, menuId).orElse(null),
                "restaurantId=" + restaurantId + ", menuId=" + menuId);
    }

    public Menu getByRestaurantIdAndLocalDate(int restaurantId, LocalDate localDate) {
        Menu menu = menuRepository.getByRestaurantIdAndLocalDate(restaurantId, localDate).orElse(null);
        return checkNotFound(menu, "restaurantId=" + restaurantId + ", localDate=" + localDate);
    }
}
