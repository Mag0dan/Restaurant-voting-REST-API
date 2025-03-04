package ru.javaops.graduation;

import ru.javaops.graduation.model.Restaurant;
import ru.javaops.graduation.web.MatcherFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "dishes");
    public static MatcherFactory.Matcher<Restaurant> RESTAURANT_WITH_DISHES_MATCHER =
            MatcherFactory.usingAssertions(Restaurant.class,
//     No need use ignoringAllOverriddenEquals, Restaurant https://assertj.github.io/doc/#breaking-changes
                    (a, e) -> assertThat(a).usingRecursiveComparison().ignoringFields("dishes.restaurant").isEqualTo(e),
                    (a, e) -> {
                        throw new UnsupportedOperationException();
                    });

    public static final Restaurant restaurant1 = new Restaurant(1, "Столовая №1");
    public static final Restaurant restaurant2 = new Restaurant(2, "Ресторан на Арбате");
    public static final Restaurant restaurant3 = new Restaurant(3, "Кафе у дома");
    public static final Restaurant restaurant4 = new Restaurant(4, "Макдональдс");

    public static Restaurant getNew() {
        return new Restaurant(null, "SteakTonight");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(restaurant1.getId(), "Столовая №2");
    }
}
