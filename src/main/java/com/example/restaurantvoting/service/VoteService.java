package com.example.restaurantvoting.service;

import com.example.restaurantvoting.model.Vote;
import com.example.restaurantvoting.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class VoteService {

    private VoteRepository voteRepository;

    public Vote save(String restaurantId, int userId, LocalDateTime dateTime) {
        return new Vote();
    }
}
