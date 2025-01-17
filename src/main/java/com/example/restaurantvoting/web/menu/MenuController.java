package com.example.restaurantvoting.web.menu;

import com.example.restaurantvoting.model.Menu;
import com.example.restaurantvoting.service.MenuService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping(value = MenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class MenuController {

    static final String REST_URL = "/api/restaurants";

    private final MenuService menuService;

    @GetMapping("/{restaurantId}/menus")
    public ResponseEntity<Menu> getByRestaurantIdAndDateMenu(@PathVariable int restaurantId, @RequestParam LocalDate dateMenu) {
        log.info("Get menu by restaurant id {} and localDate {}", restaurantId, dateMenu);
        return ResponseEntity.of(menuService.getByRestaurantIdAndDateMenu(restaurantId, dateMenu));
    }
}
