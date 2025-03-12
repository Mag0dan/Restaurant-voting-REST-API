package ru.javaops.graduation.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class DishTo extends NamedTo {

    @NotBlank
    private final LocalDate date;

    @NotBlank
    @PositiveOrZero
    private final long price;

    public DishTo(Integer id, String name, LocalDate date, long price) {
        super(id, name);
        this.date = date;
        this.price = price;
    }
}