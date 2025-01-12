package com.example.restaurantvoting.service;

import com.example.restaurantvoting.model.Restaurant;
import com.example.restaurantvoting.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static com.example.restaurantvoting.util.validation.ValidationUtil.checkNotFound;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class RestaurantService {

    private RestaurantRepository restaurantRepository;

    @Cacheable("restaurants")
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    @Cacheable(value = "restaurants", key = "#id")
    public Restaurant get(int id) {
        return checkNotFound(restaurantRepository.findById(id).orElse(null), id);
    }

    @Transactional
    @CacheEvict(value = "restaurants", allEntries = true)
    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getAllByDateMenu(LocalDate dateMenu) {
        return restaurantRepository.getAllByDateMenu(dateMenu);
    }
}
