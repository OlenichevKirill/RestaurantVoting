package com.example.restaurantvoting.web.menuItem;

import com.example.restaurantvoting.AbstractControllerTest;
import com.example.restaurantvoting.model.MenuItem;
import com.example.restaurantvoting.service.MenuItemService;
import com.example.restaurantvoting.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.example.restaurantvoting.web.menu.MenuTestData.MENU_ID_1;
import static com.example.restaurantvoting.web.menuItem.AdminMenuItemController.REST_URL;
import static com.example.restaurantvoting.web.menuItem.MenuItemTestData.MENU_ITEM_1;
import static com.example.restaurantvoting.web.menuItem.MenuItemTestData.MENU_ITEM_1_ID;
import static com.example.restaurantvoting.web.menuItem.MenuItemTestData.MENU_ITEM_MATCHER;
import static com.example.restaurantvoting.web.menuItem.MenuItemTestData.getNew;
import static com.example.restaurantvoting.web.menuItem.MenuItemTestData.getUpdated;
import static com.example.restaurantvoting.web.user.UserTestData.ADMIN_MAIL;
import static com.example.restaurantvoting.web.user.UserTestData.USER_MAIL;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AdminMenuItemControllerTest extends AbstractControllerTest {

    private static final String REST_URL_SLASH = REST_URL + '/';

    @Autowired
    private MenuItemService menuItemService;

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void create() throws Exception {
        MenuItem newDish = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL_SLASH + MENU_ID_1 + "/menuItems")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newDish)))
                .andDo(print())
                .andExpect(status().isCreated());

        MenuItem created = MENU_ITEM_MATCHER.readFromJson(action);
        int newId = created.id();
        newDish.setId(newId);
        MENU_ITEM_MATCHER.assertMatch(created, newDish);
        MENU_ITEM_MATCHER.assertMatch(menuItemService.getByMenuIdAndMenuItemId(MENU_ID_1, newId).orElse(null), newDish);
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void createWithUser() throws Exception {
        MenuItem newDish = getNew();
        perform(MockMvcRequestBuilders.post(REST_URL_SLASH + MENU_ID_1 + "/menuItems")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newDish)))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    void createUnAuth() throws Exception {
        MenuItem newDish = getNew();
        perform(MockMvcRequestBuilders.post(REST_URL_SLASH + MENU_ID_1 + "/menuItems")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newDish)))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void update() throws Exception {
        MenuItem updated = getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + MENU_ID_1 + "/menuItems/" + MENU_ITEM_1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());
        MENU_ITEM_MATCHER.assertMatch(menuItemService.getByMenuIdAndMenuItemId(MENU_ID_1, MENU_ITEM_1_ID).orElse(null), updated);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createInvalid() throws Exception {
        MenuItem invalid = new MenuItem(null, null, null);
        perform(MockMvcRequestBuilders.post(REST_URL_SLASH + MENU_ID_1 + "/menuItems")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void updateInvalid() throws Exception {
        MenuItem invalid = new MenuItem(MENU_ITEM_1);
        invalid.setPrice(null);
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + MENU_ID_1 + "/menuItems/" + MENU_ITEM_1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}