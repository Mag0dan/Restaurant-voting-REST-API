package ru.javaops.graduation.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.javaops.graduation.DishTestData;
import ru.javaops.graduation.model.Dish;

import java.time.LocalDate;

import static ru.javaops.graduation.DishTestData.*;

class DishRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    void getAllByRestaurantId() {
        DISH_MATCHER.assertMatch(dishRepository.getAllByRestaurantId(1, LocalDate.now()), restaurant_1_Dishes);
    }

    @Test
    void saveDish() {
        Dish newDish = DishTestData.getNew();
        newDish.setRestaurant(restaurantRepository.getReferenceById(1));
        int newId = dishRepository.save(newDish).id();
        DISH_MATCHER.assertMatch(dishRepository.findById(newId).orElse(null), newDish);
    }

    @Test
    void saveDuplicateDish() {
        Dish newDish = dish1;
        dish1.setId(NEXT_DISH_ID);
        newDish.setRestaurant(restaurantRepository.getReferenceById(1));
        Assertions.assertThrows(DataAccessException.class, () -> dishRepository.save(newDish));
    }
}