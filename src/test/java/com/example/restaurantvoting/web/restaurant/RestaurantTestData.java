package com.example.restaurantvoting.web.restaurant;

import com.example.restaurantvoting.MatcherFactory;
import com.example.restaurantvoting.model.Restaurant;

import java.util.List;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "menus");

    public static final int RESTAURANT_1_ID = 1;
    public static final int RESTAURANT_2_ID = 2;
    public static final int RESTAURANT_3_ID = 3;
    public static final int NOT_FOUND = 100;

    public static final Restaurant RESTAURANT_1 = new Restaurant(RESTAURANT_1_ID, "RESTAURANT1");
    public static final Restaurant RESTAURANT_2 = new Restaurant(RESTAURANT_2_ID, "RESTAURANT2");
    public static final Restaurant RESTAURANT_3 = new Restaurant(RESTAURANT_3_ID, "RESTAURANT3");

    public static final List<Restaurant> RESTAURANTS = List.of(RESTAURANT_1, RESTAURANT_2, RESTAURANT_3);
    public static final List<Restaurant> RESTAURANTS_BY_DATE = List.of(RESTAURANT_1, RESTAURANT_2);

    public static Restaurant getNew() {
        return new Restaurant(null, "NewRestaurant");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT_1_ID, "updatedRestaurant");
    }
}
