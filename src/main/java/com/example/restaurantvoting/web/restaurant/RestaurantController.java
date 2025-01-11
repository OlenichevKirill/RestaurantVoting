package com.example.restaurantvoting.web.restaurant;

import com.example.restaurantvoting.model.Restaurant;
import com.example.restaurantvoting.service.RestaurantService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class RestaurantController {
    static final String REST_URL = "/api/restaurants";

    private final RestaurantService restaurantService;

    @GetMapping
    public List<Restaurant> getAllByDateMenu(@RequestParam() LocalDate dateMenu) {
        log.info("Get all restaurants by date {}", dateMenu);
        return restaurantService.getAllByDateMenu(dateMenu);
    }


    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        log.info("Get restaurant with id {}", id);
        return restaurantService.get(id);
    }
}
