package ru.javaops.graduation.util;

import ru.javaops.graduation.error.IllegalRequestDataException;
import ru.javaops.graduation.model.Vote;
import ru.javaops.graduation.to.VoteTo;

import java.time.LocalTime;

public class VoteUtil {
    public static final LocalTime VOTE_DEADLINE = LocalTime.of(11, 0);

    public static void checkVoteTime(LocalTime currentTime) {
        if (currentTime.isAfter(VOTE_DEADLINE)) {
            throw new IllegalRequestDataException("Vote can't be changed after " + VOTE_DEADLINE);
        }
    }

    public static VoteTo asTo(Vote vote) {
        return new VoteTo(vote.getRestaurant().getId());
    }
} 