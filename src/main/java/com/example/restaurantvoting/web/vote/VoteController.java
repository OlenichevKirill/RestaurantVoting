package com.example.restaurantvoting.web.vote;

import com.example.restaurantvoting.app.AuthUser;
import com.example.restaurantvoting.model.Vote;
import com.example.restaurantvoting.service.VoteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class VoteController {

    static final String REST_URL = "/api/votes";

    private final VoteService voteService;

    @GetMapping
    public List<Vote> getAll(@AuthenticationPrincipal AuthUser authUser) {
        log.info("Get all votes for authUser: {}", authUser);
        return voteService.getAll(authUser.id());
    }

    @GetMapping("/{idVote}")
    public ResponseEntity<Vote> getVoteByIdVoteAndAuthUser(@PathVariable int idVote, @AuthenticationPrincipal AuthUser authUser) {
        log.info("Get vote by idVote: {}", idVote);
        return ResponseEntity.ofNullable(voteService.getVoteByIdVoteAndAuthUser(authUser.id(), idVote));
    }

    @GetMapping("/today")
    public Vote getVoteNow(@AuthenticationPrincipal AuthUser authUser) {
        log.info("Get vote now for authUser: {}", authUser);
        return voteService.getVoteNow(authUser.id());
    }

    @PostMapping(/*consumes = MediaType.APPLICATION_JSON_VALUE*/)
    public Vote createVoteNow(@AuthenticationPrincipal AuthUser authUser, @RequestParam int restaurantId) {
        log.info("Create vote now for restaurantId: {}", restaurantId);
        return voteService.save(authUser.id(), restaurantId);
    }

    @PutMapping(value = "/{idVote}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateVoteNow(@AuthenticationPrincipal AuthUser authUser,
                              @RequestParam int restaurantId,
                              @PathVariable int idVote) {
        log.info("Update vote now for idVote: {}", idVote);
        voteService.update(authUser.id(), restaurantId, idVote);
    }

    @DeleteMapping("/{idVote}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal AuthUser authUser, @PathVariable int idVote) {
        log.info("Delete vote for idVote: {}", idVote);
        voteService.delete(authUser.id(), idVote);
    }
}
