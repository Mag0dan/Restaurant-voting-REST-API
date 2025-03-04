package ru.javaops.graduation.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javaops.graduation.DishTestData;
import ru.javaops.graduation.RestaurantTestData;
import ru.javaops.graduation.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

import static ru.javaops.graduation.DishTestData.*;
import static ru.javaops.graduation.RestaurantTestData.*;

class RestaurantRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    void getWithDishes() {
        Restaurant withDishes = restaurantRepository.getWithDishes(1, LocalDate.now());
        restaurant1.setDishes(restaurant_1_Dishes);
        RESTAURANT_WITH_DISHES_MATCHER.assertMatch(withDishes, RestaurantTestData.restaurant1);
        DISH_MATCHER.assertMatch(withDishes.getDishes(), DishTestData.restaurant_1_Dishes);
    }

    @Test
    void getAllWithDishes() {
        List<Restaurant> withDishes = restaurantRepository.getAllWithDishes(LocalDate.now());
        restaurant1.setDishes(DishTestData.restaurant_1_Dishes);
        restaurant2.setDishes(DishTestData.restaurant_2_Dishes);
        restaurant3.setDishes(DishTestData.restaurant_3_Dishes);
        restaurant4.setDishes(DishTestData.restaurant_4_Dishes);
        RESTAURANT_WITH_DISHES_MATCHER.assertMatch(withDishes.get(0), restaurant1);
        RESTAURANT_WITH_DISHES_MATCHER.assertMatch(withDishes.get(1), restaurant2);
        RESTAURANT_WITH_DISHES_MATCHER.assertMatch(withDishes.get(2), restaurant3);
        RESTAURANT_WITH_DISHES_MATCHER.assertMatch(withDishes.get(3), restaurant4);
    }


}