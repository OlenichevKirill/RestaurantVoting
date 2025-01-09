package com.example.restaurantvoting.web.vote;

import com.example.restaurantvoting.app.AuthUser;
import com.example.restaurantvoting.model.Vote;
import com.example.restaurantvoting.service.VoteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class VoteController {

    static final String REST_URL = "/api/restaurant";

    private final VoteService voteService;

//    @GetMapping("/{restaurantId}/")
//    public Vote getVote() {
//
//    }

    @PostMapping(value = "/{restaurantId}/votes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Vote save(@PathVariable String restaurantId,
                     @AuthenticationPrincipal AuthUser authUser,
                     @RequestParam LocalDateTime dateTime) {
        log.info("Saving vote for restaurant {}", restaurantId);
        voteService.save(restaurantId, authUser.id(), dateTime);
        return new Vote();
    }
}
