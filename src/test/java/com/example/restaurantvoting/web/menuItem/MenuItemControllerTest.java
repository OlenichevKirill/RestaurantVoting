package com.example.restaurantvoting.web.menuItem;

import com.example.restaurantvoting.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.example.restaurantvoting.web.menu.MenuTestData.MENU_ID_1;
import static com.example.restaurantvoting.web.menuItem.MenuItemController.REST_URL;
import static com.example.restaurantvoting.web.menuItem.MenuItemTestData.MENU_ITEMS;
import static com.example.restaurantvoting.web.menuItem.MenuItemTestData.MENU_ITEM_1;
import static com.example.restaurantvoting.web.menuItem.MenuItemTestData.MENU_ITEM_1_ID;
import static com.example.restaurantvoting.web.menuItem.MenuItemTestData.MENU_ITEM_MATCHER;
import static com.example.restaurantvoting.web.menuItem.MenuItemTestData.NOT_FOUND;
import static com.example.restaurantvoting.web.user.UserTestData.ADMIN_MAIL;
import static com.example.restaurantvoting.web.user.UserTestData.USER_MAIL;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MenuItemControllerTest extends AbstractControllerTest {

    private static final String REST_URL_SLASH = REST_URL + '/';

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getAllByMenuId() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + MENU_ID_1 + "/menuItems"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_ITEM_MATCHER.contentJson(MENU_ITEMS));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getByMenuIdAndMenuItemId() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + MENU_ID_1 + "/menuItems/" + MENU_ITEM_1_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_ITEM_MATCHER.contentJson(MENU_ITEM_1));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getByMenuIdAndMenuItemIdWithUser() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + MENU_ID_1 + "/menuItems/" + MENU_ITEM_1_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_ITEM_MATCHER.contentJson(MENU_ITEM_1));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getByMenuIdAndMenuItemIdNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + MENU_ID_1 + "/menuItems/" + NOT_FOUND))
                .andDo(print())
                .andExpect(status().isNotFound());
        ;
    }
}