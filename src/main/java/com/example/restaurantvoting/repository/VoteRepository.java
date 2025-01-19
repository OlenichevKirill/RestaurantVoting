package com.example.restaurantvoting.repository;

import com.example.restaurantvoting.model.Vote;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    @Query("SELECT v FROM Vote v WHERE v.user.id = :userId ORDER BY v.dateVote DESC")
    @EntityGraph(attributePaths = {"restaurant"})
    List<Vote> getAll(int userId);

    @Query("SELECT v FROM Vote v WHERE v.user.id = :userId and v.id = :idVote")
    @EntityGraph(attributePaths = {"restaurant"})
    Vote getVoteByIdVoteAndAuthUser(int userId, int idVote);

    @Query("SELECT v FROM Vote v WHERE v.user.id = :userId and v.dateVote = :voteTimeNow")
    @EntityGraph(attributePaths = {"restaurant"})
    Vote getVoteNow(int userId, LocalDate voteTimeNow);

    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.id=:idVote and v.user.id = :userId")
    int delete(int userId, int idVote);

    @Query("SELECT v FROM Vote v WHERE v.user.id = :userId and v.dateVote = :voteTimeNow and v.id = :idVote")
    @EntityGraph(attributePaths = {"restaurant"})
    Vote getVoteByIdVoteAndAuthUserAndDateNow(int userId, int idVote, LocalDate voteTimeNow);
}
