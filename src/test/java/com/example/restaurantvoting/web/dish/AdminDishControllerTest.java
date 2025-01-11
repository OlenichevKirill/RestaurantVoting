package com.example.restaurantvoting.web.dish;

import com.example.restaurantvoting.AbstractControllerTest;
import com.example.restaurantvoting.model.Dish;
import com.example.restaurantvoting.service.DishService;
import com.example.restaurantvoting.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.example.restaurantvoting.web.dish.AdminDishController.REST_URL;
import static com.example.restaurantvoting.web.dish.DishTestData.DISH_1;
import static com.example.restaurantvoting.web.dish.DishTestData.DISH_1_ID;
import static com.example.restaurantvoting.web.dish.DishTestData.DISH_MATCHER;
import static com.example.restaurantvoting.web.dish.DishTestData.getNew;
import static com.example.restaurantvoting.web.dish.DishTestData.getUpdated;
import static com.example.restaurantvoting.web.menu.MenuTestData.MENU_ID_1;
import static com.example.restaurantvoting.web.user.UserTestData.ADMIN_MAIL;
import static com.example.restaurantvoting.web.user.UserTestData.USER_MAIL;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AdminDishControllerTest extends AbstractControllerTest {

    private static final String REST_URL_SLASH = REST_URL + '/';

    @Autowired
    private DishService dishService;

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void create() throws Exception {
        Dish newDish = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL_SLASH + MENU_ID_1 + "/dishes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newDish)))
                .andDo(print())
                .andExpect(status().isCreated());

        Dish created = DISH_MATCHER.readFromJson(action);
        int newId = created.id();
        newDish.setId(newId);
        DISH_MATCHER.assertMatch(created, newDish);
        DISH_MATCHER.assertMatch(dishService.getByMenuIdAndDishId(MENU_ID_1, newId), newDish);
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void createWithUser() throws Exception {
        Dish newDish = getNew();
        perform(MockMvcRequestBuilders.post(REST_URL_SLASH + MENU_ID_1 + "/dishes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newDish)))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    void createUnAuth() throws Exception {
        Dish newDish = getNew();
        perform(MockMvcRequestBuilders.post(REST_URL_SLASH + MENU_ID_1 + "/dishes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newDish)))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void update() throws Exception {
        Dish updated = getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + MENU_ID_1 + "/dishes/" + DISH_1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());
        DISH_MATCHER.assertMatch(dishService.getByMenuIdAndDishId(MENU_ID_1, DISH_1_ID), updated);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createInvalid() throws Exception {
        Dish invalid = new Dish(null, null, null);
        perform(MockMvcRequestBuilders.post(REST_URL_SLASH + MENU_ID_1 + "/dishes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void updateInvalid() throws Exception {
        Dish invalid = new Dish(DISH_1);
        invalid.setPrice(null);
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + MENU_ID_1 + "/dishes/" + DISH_1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}