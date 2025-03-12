package ru.javaops.graduation.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.graduation.model.Dish;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface DishRepository extends BaseRepository<Dish> {

    @Query("SELECT d FROM Dish d WHERE d.id = :id AND d.restaurant.id = :restaurantId")
    Optional<Dish> findByIdAndRestaurantId(int id, int restaurantId);

    @Query("SELECT d FROM Dish d WHERE d.id = :id AND d.restaurant.id = :restaurantId AND d.date = :date")
    Optional<Dish> findByIdAndRestaurantIdAndDate(int id, int restaurantId, LocalDate date);

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id = :restaurantId ORDER BY d.date DESC, d.id")
    List<Dish> findAllByRestaurantId(int restaurantId);

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id = :restaurantId AND d.date = :date ORDER BY d.date DESC, d.id")
    List<Dish> findAllByRestaurantIdAndDate(int restaurantId, LocalDate date);
}
