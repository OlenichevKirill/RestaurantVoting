package com.example.restaurantvoting.web.dish;

import com.example.restaurantvoting.MatcherFactory;
import com.example.restaurantvoting.model.Dish;

import java.util.List;

public class DishTestData {
    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(Dish.class, "menu");

    public static final int DISH_1_ID = 1;
    public static final int DISH_2_ID = 2;
    public static final int DISH_3_ID = 3;
    public static final int NOT_FOUND = 111;

    public static final Dish DISH_1 = new Dish(DISH_1_ID, "DISH1", 100.0);
    public static final Dish DISH_2 = new Dish(DISH_2_ID, "DISH2", 250.5);
    public static final Dish DISH_3 = new Dish(DISH_3_ID, "DISH3", 167.1);

    public static final List<Dish> DISHES = List.of(DISH_1, DISH_2, DISH_3);

    public static Dish getNew() {
        return new Dish(null, "NewDish", 100.5);
    }

    public static Dish getUpdated() {
        return new Dish(DISH_1_ID, "updatedDish", 100.5);
    }
}
