package com.example.restaurantvoting.service;

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
import java.util.List;

import static com.example.restaurantvoting.util.validation.ValidationUtil.checkNotFound;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class VoteService {

    private static final LocalTime END_TIME = LocalTime.of(11, 0);

    private final VoteRepository voteRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    public List<Vote> getAll(int userId) {
        return voteRepository.getAll(userId);
    }

    public Vote getVoteByIdVoteAndAuthUser(int userId, int idVote) {
        return voteRepository.getVoteByIdVoteAndAuthUser(userId, idVote);
    }

    public Vote getVoteNow(int userId) {
        LocalDate voteTimeNow = LocalDate.now();
        return voteRepository.getVoteNow(userId, voteTimeNow);
    }

    @Transactional
    public Vote save(int userId, int restaurantId) {
        LocalDate voteTimeNow = LocalDate.now();
        Vote voteNew = new Vote(null, voteTimeNow);
        voteNew.setRestaurant(restaurantRepository.getReferenceById(restaurantId));
        voteNew.setUser(userRepository.getReferenceById(userId));
        return voteRepository.save(voteNew);
    }

    @Transactional
    public void update(int userId, int restaurantId, int idVote) {
        LocalDateTime voteTimeNow = LocalDateTime.now();

        if (voteTimeNow.toLocalTime().isBefore(END_TIME)) {
            Vote vote = checkNotFound(
                    voteRepository.getVoteByIdVoteAndAuthUserAndDateNow(userId, idVote, voteTimeNow.toLocalDate()),
                    idVote);
            vote.setRestaurant(restaurantRepository.getReferenceById(restaurantId));
            voteRepository.save(vote);
        } else {
            throw new VoteException("The re-voting for the restaurant ended today");
        }
    }

    @Transactional
    public void delete(int userId, int idVote) {
        checkNotFound(voteRepository.delete(userId, idVote) != 0, idVote);
    }
}
