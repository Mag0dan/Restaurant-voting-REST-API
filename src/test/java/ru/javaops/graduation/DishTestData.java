package ru.javaops.graduation;

import ru.javaops.graduation.model.Dish;
import ru.javaops.graduation.web.MatcherFactory;

import java.util.List;


public class DishTestData {
    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Dish.class, "restaurant");
    public static final int NEXT_DISH_ID = 7;

    public static final Dish dish1 = new Dish(1, "Картофельное пюре", 350);
    public static final Dish dish2 = new Dish(2, "Котлета", 555);
    public static final Dish dish3 = new Dish(3, "Ризотто с трюфельным соусом", 99999);
    public static final Dish dish4 = new Dish(4, "Стейк Рибай 100гр", 999999);
    public static final Dish dish5 = new Dish(5, "Пицца", 5000);
    public static final Dish dish6 = new Dish(6, "Бигмак", 4500);

    public static final List<Dish> restaurant_1_Dishes = List.of(dish1, dish2);
    public static final List<Dish> restaurant_2_Dishes = List.of(dish3, dish4);
    public static final List<Dish> restaurant_3_Dishes = List.of(dish5);
    public static final List<Dish> restaurant_4_Dishes = List.of(dish6);

    public static Dish getNew() {
        return new Dish(null, "NewDish", 1234);
    }

    public static Dish getUpdated() {
        return new Dish(dish1.getId(), "Картофельное пюре c зеленью", 400);
    }
}
