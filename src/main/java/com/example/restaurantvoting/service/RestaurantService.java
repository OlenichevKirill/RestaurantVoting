package com.example.restaurantvoting.service;

import com.example.restaurantvoting.model.Restaurant;
import com.example.restaurantvoting.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.example.restaurantvoting.util.validation.ValidationUtil.checkNotFound;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class RestaurantService {

    private RestaurantRepository restaurantRepository;

    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    public Optional<Restaurant> get(int id) {
        return restaurantRepository.findById(id);
    }

    @Transactional
    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getAllByDateMenu(LocalDate dateMenu) {
        return restaurantRepository.getAllByDateMenu(dateMenu);
    }

    @Transactional
    public void delete(int id) {
        checkNotFound(restaurantRepository.delete(id) != 0, id);
    }
}
