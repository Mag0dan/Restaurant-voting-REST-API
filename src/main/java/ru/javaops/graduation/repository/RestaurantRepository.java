package ru.javaops.graduation.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.graduation.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {
    @Query("SELECT r from Restaurant r LEFT JOIN FETCH r.dishes d WHERE r.id=?1 and d.date=?2")
    Restaurant getWithDishes(int id, LocalDate date);

    @Query("SELECT r from Restaurant r LEFT JOIN FETCH r.dishes d WHERE d.date=?1")
    List<Restaurant> getAllWithDishes(LocalDate date);
}
