package com.example.restaurantvoting.web.vote;

import com.example.restaurantvoting.MatcherFactory;
import com.example.restaurantvoting.model.Vote;

import java.time.LocalDate;
import java.util.List;

public class VoteTestData {

    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(Vote.class, "restaurant", "user");

    public static final MatcherFactory.Matcher<Vote> VOTE_WIRH_RESTAURANT_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(Vote.class, "user");

    public static final int VOTE_1_ID = 1;
    public static final int VOTE_2_ID = 2;
    public static final int NOT_FOUND = 200;

    public static final Vote VOTE_1 = new Vote(VOTE_1_ID, LocalDate.of(2077, 1, 1));
    public static final Vote VOTE_2 = new Vote(VOTE_2_ID, LocalDate.of(2023, 1, 1));

    public static final List<Vote> VOTES = List.of(VOTE_1, VOTE_2);
}
