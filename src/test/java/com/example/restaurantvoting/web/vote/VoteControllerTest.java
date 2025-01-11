package com.example.restaurantvoting.web.vote;

import com.example.restaurantvoting.AbstractControllerTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.example.restaurantvoting.web.menu.MenuTestData.MENU_1;
import static com.example.restaurantvoting.web.menu.MenuTestData.MENU_3;
import static com.example.restaurantvoting.web.menu.MenuTestData.MENU_4;
import static com.example.restaurantvoting.web.restaurant.RestaurantTestData.RESTAURANT_1_ID;
import static com.example.restaurantvoting.web.restaurant.RestaurantTestData.RESTAURANT_2_ID;
import static com.example.restaurantvoting.web.user.UserTestData.ADMIN_MAIL;
import static com.example.restaurantvoting.web.user.UserTestData.GUEST_MAIL;
import static com.example.restaurantvoting.web.user.UserTestData.USER_MAIL;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class VoteControllerTest extends AbstractControllerTest {

    private static final String REST_URL_SLASH = VoteController.REST_URL + '/';

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getRestaurantVoteByAuthUserAndDateTime() throws Exception {
        ResultActions action = perform(MockMvcRequestBuilders.get(REST_URL_SLASH + "votes")
                .param("dateMenu", MENU_1.getDateMenu().toString()))
                .andDo(print())
                .andExpect(status().isOk());
        Integer id = Integer.valueOf(action.andReturn().getResponse().getContentAsString());
        Assertions.assertThat(id).isEqualTo(RESTAURANT_1_ID);
    }


    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void addVoteUpdate() throws Exception {
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + RESTAURANT_2_ID + "/votes")
                .param("dateMenu", MENU_4.getDateMenu().toString()))
                .andDo(print())
                .andExpect(status().isNoContent());

        ResultActions action = perform(MockMvcRequestBuilders.get(REST_URL_SLASH + "votes")
                .param("dateMenu", MENU_4.getDateMenu().toString()))
                .andDo(print())
                .andExpect(status().isOk());

        Integer id = Integer.valueOf(action.andReturn().getResponse().getContentAsString());
        Assertions.assertThat(id).isEqualTo(RESTAURANT_2_ID);
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void addVoteCreate() throws Exception {
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + RESTAURANT_1_ID + "/votes")
                .param("dateMenu", MENU_1.getDateMenu().toString()))
                .andDo(print())
                .andExpect(status().isNoContent());

        ResultActions action = perform(MockMvcRequestBuilders.get(REST_URL_SLASH + "votes")
                .param("dateMenu", MENU_1.getDateMenu().toString()))
                .andDo(print())
                .andExpect(status().isOk());

        Integer id = Integer.valueOf(action.andReturn().getResponse().getContentAsString());
        Assertions.assertThat(id).isEqualTo(RESTAURANT_1_ID);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void addVoteEndOfVoting() throws Exception {
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + RESTAURANT_1_ID + "/votes")
                .param("dateMenu", MENU_3.getDateMenu().toString()))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(value = GUEST_MAIL)
    void addVoteWithGuest() throws Exception {
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + RESTAURANT_1_ID + "/votes")
                .param("dateMenu", MENU_1.getDateMenu().toString()))
                .andDo(print())
                .andExpect(status().isForbidden());
    }
}