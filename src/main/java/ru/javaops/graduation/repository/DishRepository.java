package ru.javaops.graduation.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.graduation.model.Dish;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface DishRepository extends BaseRepository<Dish> {
    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=:restId AND d.date=:date")
    List<Dish> getAllByRestaurantId(@Param("restId") int restaurantId, @Param("date") LocalDate date);
}
