package ru.javaops.graduation.web.dish;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.graduation.error.NotFoundException;
import ru.javaops.graduation.model.Dish;
import ru.javaops.graduation.repository.DishRepository;
import ru.javaops.graduation.repository.RestaurantRepository;
import ru.javaops.graduation.to.DishTo;
import ru.javaops.graduation.util.DishUtil;
import ru.javaops.graduation.util.validation.ValidationUtil;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = AdminDishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminDishController {
    static final String REST_URL = "/api/admin/restaurants/{restaurantId}/dishes";

    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;

    @GetMapping("/{id}")
    public Dish get(@PathVariable int restaurantId, @PathVariable int id) {
        log.info("get dish {} for restaurant {}", id, restaurantId);
        return dishRepository.findByIdAndRestaurantId(id, restaurantId)
                .orElseThrow(() -> new NotFoundException("Dish not found"));
    }

    @GetMapping
    public List<Dish> getAll(@PathVariable int restaurantId, @RequestParam @Nullable LocalDate date) {
        log.info("getAll for restaurant {}", restaurantId);
        if (date == null) {
            return dishRepository.findAllByRestaurantId(restaurantId);
        }
        return dishRepository.findAllByRestaurantIdAndDate(restaurantId, date);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int restaurantId, @PathVariable int id) {
        log.info("delete dish {} for restaurant {}", id, restaurantId);
        dishRepository.deleteExisted(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createDish(@PathVariable int restaurantId,
                                           @Valid @RequestBody DishTo dishTo) {
        log.info("create dish {} for restaurant {}", dishTo, restaurantId);
        ValidationUtil.checkNew(dishTo);
        var restaurant = restaurantRepository.getExisted(restaurantId);
        Dish created = DishUtil.createNewFromTo(dishTo);
        created.setRestaurant(restaurant);
        created = dishRepository.save(created);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(restaurantId, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable int restaurantId,
                       @PathVariable int id,
                       @Valid @RequestBody DishTo dishTo) {
        log.info("update dish {} for restaurant {}", id, restaurantId);
        ValidationUtil.assureIdConsistent(dishTo, id);
        Dish dish = dishRepository.findByIdAndRestaurantId(id, restaurantId)
                .orElseThrow(() -> new NotFoundException("Dish not found"));
        dishRepository.save(DishUtil.updateFromTo(dish, dishTo));
    }
} 