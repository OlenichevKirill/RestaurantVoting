package com.example.restaurantvoting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dish")
@Getter
@Setter
@NoArgsConstructor
public class Dish extends AbstractNamedEntity {

    @Column(name = "price", nullable = false)
    @NotNull
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    @JsonIgnore
    private Menu menu;

    public Dish(Dish d) {
        this(d.getId(), d.getName(), d.getPrice());
    }

    public Dish(Integer id, String name, Double price) {
        super(id, name);
        this.price = price;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name=" + name +
                ", price='" + price +
                '}';
    }
}
