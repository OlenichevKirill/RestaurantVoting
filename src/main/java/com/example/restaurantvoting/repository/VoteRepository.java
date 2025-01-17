package com.example.restaurantvoting.repository;

import com.example.restaurantvoting.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    @Query("SELECT v FROM Vote v WHERE v.user.id = :userId AND v.dateVote = :dateMenu")
    Optional<Vote> getByUserIdAndDateMenu(int userId, LocalDate dateMenu);

    @Query("SELECT v.restaurant.id FROM Vote v WHERE v.user.id = :userId AND v.dateVote = :dateMenu")
    Optional<Integer> getRestaurantVoteByAuthUserAndDateTime(int userId, LocalDate dateMenu);
}
