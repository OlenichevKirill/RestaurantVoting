package com.example.restaurantvoting.web.dish;

import com.example.restaurantvoting.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.example.restaurantvoting.web.dish.DishController.REST_URL;
import static com.example.restaurantvoting.web.dish.DishTestData.DISHES;
import static com.example.restaurantvoting.web.dish.DishTestData.DISH_1;
import static com.example.restaurantvoting.web.dish.DishTestData.DISH_1_ID;
import static com.example.restaurantvoting.web.dish.DishTestData.DISH_MATCHER;
import static com.example.restaurantvoting.web.dish.DishTestData.NOT_FOUND;
import static com.example.restaurantvoting.web.menu.MenuTestData.MENU_ID_1;
import static com.example.restaurantvoting.web.user.UserTestData.ADMIN_MAIL;
import static com.example.restaurantvoting.web.user.UserTestData.USER_MAIL;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DishControllerTest extends AbstractControllerTest {

    private static final String REST_URL_SLASH = REST_URL + '/';

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getAllByMenuId() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + MENU_ID_1 + "/dishes"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(DISHES));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getByMenuIdAndDishId() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + MENU_ID_1 + "/dishes/" + DISH_1_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(DISH_1));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getByMenuIdAndDishIdWithUser() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + MENU_ID_1 + "/dishes/" + DISH_1_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(DISH_1));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getByMenuIdAndDishIdNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + MENU_ID_1 + "/dishes/" + NOT_FOUND))
                .andDo(print())
                .andExpect(status().isNotFound());
        ;
    }
}