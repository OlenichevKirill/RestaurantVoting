package com.example.restaurantvoting.web.vote;

import com.example.restaurantvoting.AbstractControllerTest;
import com.example.restaurantvoting.model.Vote;
import com.example.restaurantvoting.service.VoteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

import static com.example.restaurantvoting.web.restaurant.RestaurantTestData.NOT_FOUND;
import static com.example.restaurantvoting.web.restaurant.RestaurantTestData.RESTAURANT_1;
import static com.example.restaurantvoting.web.restaurant.RestaurantTestData.RESTAURANT_1_ID;
import static com.example.restaurantvoting.web.restaurant.RestaurantTestData.RESTAURANT_MATCHER;
import static com.example.restaurantvoting.web.user.UserTestData.ADMIN_ID;
import static com.example.restaurantvoting.web.user.UserTestData.ADMIN_MAIL;
import static com.example.restaurantvoting.web.vote.VoteTestData.VOTES;
import static com.example.restaurantvoting.web.vote.VoteTestData.VOTE_1;
import static com.example.restaurantvoting.web.vote.VoteTestData.VOTE_1_ID;
import static com.example.restaurantvoting.web.vote.VoteTestData.VOTE_MATCHER;
import static com.example.restaurantvoting.web.vote.VoteTestData.VOTE_WIRH_RESTAURANT_MATCHER;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class VoteControllerTest extends AbstractControllerTest {

    private static final String REST_URL_SLASH = VoteController.REST_URL + '/';

    @Autowired
    private VoteService voteService;

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getAllVotes() throws Exception {
        perform(MockMvcRequestBuilders.get(VoteController.REST_URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(VOTES));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void get() throws Exception {
        ResultActions action = perform(MockMvcRequestBuilders.get(REST_URL_SLASH + VOTE_1_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(VOTE_1));

        Vote vote = VOTE_WIRH_RESTAURANT_MATCHER.readFromJson(action);
        RESTAURANT_MATCHER.assertMatch(vote.getRestaurant(), RESTAURANT_1);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + VoteTestData.NOT_FOUND))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL_SLASH + VOTE_1_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        Assertions.assertFalse(voteService.getVoteByIdVoteAndAuthUser(ADMIN_ID, VOTE_1_ID) != null);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void deleteNotFound() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL_SLASH + NOT_FOUND))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void save() throws Exception {
        ResultActions action = perform(MockMvcRequestBuilders.post(VoteController.REST_URL)
                .param("restaurantId", String.valueOf(RESTAURANT_1_ID)))
                .andDo(print())
                .andExpect(status().isOk());

        Vote voteCreated = VOTE_MATCHER.readFromJson(action);
        int newId = voteCreated.getId();
        Vote voteExpected = new Vote(newId, LocalDate.now());

        VOTE_MATCHER.assertMatch(voteCreated, voteExpected);
        VOTE_MATCHER.assertMatch(voteService.getVoteByIdVoteAndAuthUser(ADMIN_ID, newId), voteCreated);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getNow() throws Exception {
        ResultActions action = perform(MockMvcRequestBuilders.post(VoteController.REST_URL)
                .param("restaurantId", String.valueOf(RESTAURANT_1_ID)))
                .andDo(print())
                .andExpect(status().isOk());

        Vote voteCreated = VOTE_MATCHER.readFromJson(action);
        Vote voteExpected = new Vote(voteCreated.getId(), LocalDate.now());
        voteExpected.setRestaurant(RESTAURANT_1);

        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + "today"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_WIRH_RESTAURANT_MATCHER.contentJson(voteExpected));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getNowNull() throws Exception {
        ResultActions action = perform(MockMvcRequestBuilders.get(REST_URL_SLASH + "today"))
                .andDo(print())
                .andExpect(status().isOk());
        String vote = action.andReturn().getResponse().getContentAsString();
        Assertions.assertFalse(StringUtils.hasText(vote));
    }
}