package ru.javaops.graduation.web.dish;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.javaops.graduation.model.Dish;
import ru.javaops.graduation.repository.DishRepository;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = DishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class DishController {
    static final String REST_URL = "/api/restaurants/{restaurantId}/dishes";

    private final DishRepository dishRepository;

    @GetMapping
    public List<Dish> getTodayDishes(@PathVariable int restaurantId) {
        log.info("get today's dishes for restaurant {}", restaurantId);
        return dishRepository.findAllByRestaurantIdAndDate(restaurantId, LocalDate.now());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dish> getTodayDishById(@PathVariable int restaurantId, @PathVariable int id) {
        log.info("get today's dish {} for restaurant {}", id, restaurantId);
        return ResponseEntity.of(dishRepository.findByIdAndRestaurantIdAndDate(id, restaurantId, LocalDate.now()));
    }
} 