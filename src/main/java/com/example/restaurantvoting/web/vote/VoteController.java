package com.example.restaurantvoting.web.vote;

import com.example.restaurantvoting.app.AuthUser;
import com.example.restaurantvoting.model.Vote;
import com.example.restaurantvoting.service.VoteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class VoteController {

    static final String REST_URL = "/api/restaurant";

    private final VoteService voteService;

    @GetMapping("/vote")
    public Integer getRestaurantVoteByAuthUserAndDateTime(@AuthenticationPrincipal AuthUser authUser,
                                                          @RequestParam LocalDate dateTime) {
        return voteService.getRestaurantVoteByAuthUserAndDateTime(authUser.id(), dateTime);
    }

    @PutMapping(value = "/{restaurantId}/votes", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addVote(@PathVariable int restaurantId,
                     @AuthenticationPrincipal AuthUser authUser,
                     @RequestParam LocalDate dateTime) {
        log.info("Saving vote for restaurant {}", restaurantId);
        voteService.saveOrUpdate(restaurantId, authUser.id(), dateTime);
    }
}
