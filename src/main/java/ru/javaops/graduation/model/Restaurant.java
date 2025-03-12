package ru.javaops.graduation.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "restaurant")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant extends NamedEntity {
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
//    @OrderBy("date DESC")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonManagedReference
//    private List<Dish> dishes;

    public Restaurant(Integer id, String name) {
        super(id, name);
    }
}
