package com.example.restaurantvoting.service;

import com.example.restaurantvoting.model.Restaurant;
import com.example.restaurantvoting.model.Vote;
import com.example.restaurantvoting.repository.RestaurantRepository;
import com.example.restaurantvoting.repository.UserRepository;
import com.example.restaurantvoting.repository.VoteRepository;
import com.example.restaurantvoting.util.exception.VoteException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.example.restaurantvoting.util.validation.ValidationUtil.checkNotFound;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class VoteService {

    private final VoteRepository voteRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    @Transactional
    public void saveOrUpdate(int restaurantId, int userId, LocalDate dateMenu) {
        LocalDateTime now = LocalDateTime.now();
        LocalTime endTime = LocalTime.of(11, 0);
        LocalDateTime timeEndVoting = LocalDateTime.of(dateMenu, endTime);
        if (now.isBefore(timeEndVoting)) {
            Vote vote = voteRepository.getByUserIdAndDateMenu(userId, dateMenu).orElse(null);
            if (vote != null) {
                vote.setDate(dateMenu);
                Restaurant restaurant = checkNotFound(restaurantRepository.getReferenceById(restaurantId), restaurantId);
                vote.setRestaurant(restaurant);
                voteRepository.save(vote);
            } else {
                Vote voteNew = new Vote(null, dateMenu);
                Restaurant restaurant = checkNotFound(restaurantRepository.getReferenceById(restaurantId), restaurantId);
                voteNew.setRestaurant(restaurant);
                voteNew.setUser(userRepository.getReferenceById(userId));
                voteRepository.save(voteNew);
            }
        } else {
            throw new VoteException("Voting for the restaurant has ended for the menu on the date:" + dateMenu);
        }
    }

    public Integer getRestaurantVoteByAuthUserAndDateMenu(int userId, LocalDate dateMenu) {
        return voteRepository.getRestaurantVoteByAuthUserAndDateTime(userId, dateMenu).orElse(null);
    }
}
