package com.example.restaurantvoting.web.menu;

import com.example.restaurantvoting.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.example.restaurantvoting.web.menu.MenuController.REST_URL;
import static com.example.restaurantvoting.web.menu.MenuTestData.MENU_1;
import static com.example.restaurantvoting.web.menu.MenuTestData.MENU_MATCHER;
import static com.example.restaurantvoting.web.menu.MenuTestData.NOT_FOUND_DATE_MENU;
import static com.example.restaurantvoting.web.restaurant.RestaurantTestData.RESTAURANT_1_ID;
import static com.example.restaurantvoting.web.user.UserTestData.ADMIN_MAIL;
import static com.example.restaurantvoting.web.user.UserTestData.USER_MAIL;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MenuControllerTest extends AbstractControllerTest {

    private static final String REST_URL_SLASH = REST_URL + '/';

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getByRestaurantIdAndLocalDate() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + RESTAURANT_1_ID + "/menu")
                .param("localDate", MENU_1.getDateMenu().toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_MATCHER.contentJson(MENU_1));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getByRestaurantIdAndLocalDateWithUser() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + RESTAURANT_1_ID + "/menu")
                .param("localDate", MENU_1.getDateMenu().toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_MATCHER.contentJson(MENU_1));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getByRestaurantIdAndLocalDateNotFoundByDate() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + RESTAURANT_1_ID + "/menu")
                .param("localDate", NOT_FOUND_DATE_MENU.toString()))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}