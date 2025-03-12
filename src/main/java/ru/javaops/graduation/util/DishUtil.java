package ru.javaops.graduation.util;

import ru.javaops.graduation.model.Dish;
import ru.javaops.graduation.to.DishTo;

public class DishUtil {
    public static Dish createNewFromTo(DishTo dishTo) {
        return new Dish(null, dishTo.getName(), dishTo.getDate(), dishTo.getPrice());
    }

    public static Dish updateFromTo(Dish dish, DishTo dishTo) {
        dish.setName(dishTo.getName());
        dish.setPrice(dishTo.getPrice());
        dish.setDate(dishTo.getDate());
        return dish;
    }
}
