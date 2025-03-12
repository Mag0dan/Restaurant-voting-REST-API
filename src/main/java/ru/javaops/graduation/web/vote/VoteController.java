package ru.javaops.graduation.web.vote;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.javaops.graduation.model.Vote;
import ru.javaops.graduation.repository.RestaurantRepository;
import ru.javaops.graduation.repository.VoteRepository;
import ru.javaops.graduation.util.VoteUtil;
import ru.javaops.graduation.web.AuthUser;
import ru.javaops.graduation.to.VoteTo;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class VoteController {
    static final String REST_URL = "/api/votes";

    private final VoteRepository voteRepository;
    private final RestaurantRepository restaurantRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Vote> createVote(@AuthenticationPrincipal AuthUser authUser,
                                           @RequestParam int restaurantId) {
        log.info("User {} votes for restaurant {}", authUser.id(), restaurantId);
        var restaurant = restaurantRepository.getReferenceById(restaurantId);
        var user = authUser.getUser();
        var today = LocalDate.now();

        var existingVote = voteRepository.findByUserIdAndDate(authUser.id(), today);

        if (existingVote.isPresent()) {
            VoteUtil.checkVoteTime(LocalTime.now());
            var vote = existingVote.get();
            vote.setRestaurant(restaurant);
            return ResponseEntity.ok(voteRepository.save(vote));
        }

        var vote = new Vote();
        vote.setUser(user);
        vote.setRestaurant(restaurant);
        vote.setDate(today);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(voteRepository.save(vote));
    }

    @GetMapping
    public ResponseEntity<VoteTo> getCurrentVote(@AuthenticationPrincipal AuthUser authUser) {
        log.info("get current vote for user {}", authUser.id());
        return ResponseEntity.of(
                voteRepository.findByUserIdAndDate(authUser.id(), LocalDate.now())
                        .map(VoteUtil::asTo));
    }
} 