package com.example.restaurantvoting.service;

import com.example.restaurantvoting.model.Dish;
import com.example.restaurantvoting.model.Menu;
import com.example.restaurantvoting.repository.DishRepository;
import com.example.restaurantvoting.repository.MenuRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.restaurantvoting.util.validation.ValidationUtil.checkNotFound;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class DishService {

    private DishRepository dishRepository;
    private MenuRepository menuRepository;

    public List<Dish> getAllByMenuId(int menuId) {
        return dishRepository.getAllByMenuId(menuId);
    }

    public Dish getByMenuIdAndDishId(int menuId, int dishId) {
        return checkNotFound(dishRepository.getByMenuIdAndDishId(menuId, dishId).orElse(null),
                "menuId=" + menuId + ", dishId=" + dishId);
    }

    @Transactional
    public Dish save(Dish dish, int menuId) {
        Menu menu = checkNotFound(menuRepository.getReferenceById(menuId), menuId);
        dish.setMenu(menu);
        return dishRepository.save(dish);
    }
}
