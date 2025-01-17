package com.example.restaurantvoting.web.menuItem;

import com.example.restaurantvoting.MatcherFactory;
import com.example.restaurantvoting.model.MenuItem;

import java.util.List;

public class MenuItemTestData {
    public static final MatcherFactory.Matcher<MenuItem> MENU_ITEM_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(MenuItem.class, "menu");

    public static final int MENU_ITEM_1_ID = 1;
    public static final int MENU_ITEM_2_ID = 2;
    public static final int MENU_ITEM_3_ID = 3;
    public static final int NOT_FOUND = 111;

    public static final MenuItem MENU_ITEM_1 = new MenuItem(MENU_ITEM_1_ID, "DISH1", 100);
    public static final MenuItem MENU_ITEM_2 = new MenuItem(MENU_ITEM_2_ID, "DISH2", 250);
    public static final MenuItem MENU_ITEM_3 = new MenuItem(MENU_ITEM_3_ID, "DISH3", 167);

    public static final List<MenuItem> MENU_ITEMS = List.of(MENU_ITEM_1, MENU_ITEM_2, MENU_ITEM_3);

    public static MenuItem getNew() {
        return new MenuItem(null, "NewDish", 100);
    }

    public static MenuItem getUpdated() {
        return new MenuItem(MENU_ITEM_1_ID, "updatedDish", 100);
    }
}
