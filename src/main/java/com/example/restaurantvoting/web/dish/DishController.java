package com.example.restaurantvoting.web.dish;

import com.example.restaurantvoting.model.Dish;
import com.example.restaurantvoting.service.DishService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = DishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class DishController {

    static final String REST_URL = "/api/menus";

    private final DishService dishService;

    @GetMapping("/{menuId}/dishes")
    public List<Dish> getAllByMenuId(@PathVariable int menuId) {
        log.info("Get all dish for menuId {}", menuId);
        return dishService.getAllByMenuId(menuId);
    }

    @GetMapping("/{menuId}/dishes/{dishId}")
    public Dish getByMenuIdAndDishId(@PathVariable int menuId, @PathVariable int dishId) {
        log.info("Get dish for menuId {} and dishId {}", menuId, dishId);
        return dishService.getByMenuIdAndDishId(menuId, dishId);
    }
}
