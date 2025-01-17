package com.example.restaurantvoting.web.menu;

import com.example.restaurantvoting.MatcherFactory;
import com.example.restaurantvoting.model.Menu;

import java.time.LocalDate;
import java.util.List;

public class MenuTestData {

    public static final MatcherFactory.Matcher<Menu> MENU_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(Menu.class, "restaurant", "dishes");

    public static final int MENU_ID_1 = 1;
    public static final int MENU_ID_2 = 2;
    public static final int MENU_ID_3 = 3;
    public static final int MENU_ID_4 = 4;
    public static final int NOT_FOUND = 100;

    public static final LocalDate NOT_FOUND_DATE_MENU = LocalDate.of(2999, 1, 1);

    public static final Menu MENU_1 = new Menu(MENU_ID_1, LocalDate.of(2077, 1, 1));
    public static final Menu MENU_2 = new Menu(MENU_ID_2, LocalDate.of(2025, 1, 10));
    public static final Menu MENU_3 = new Menu(MENU_ID_3, LocalDate.of(2023, 1, 1));

    public static final Menu MENU_4 = new Menu(MENU_ID_4, LocalDate.of(2077, 1, 1));

    public static final List<Menu> MENUS = List.of(MENU_1, MENU_2, MENU_3);

    public static Menu getNew() {
        return new Menu(null, LocalDate.of(2025, 2, 10));
    }

    public static Menu getUpdated() {
        return new Menu(MENU_ID_1, LocalDate.of(2077, 1, 1));
    }
}
