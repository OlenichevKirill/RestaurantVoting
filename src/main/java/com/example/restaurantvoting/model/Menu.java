package com.example.restaurantvoting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "menu", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "date_menu"},
        name = "menu_unique_restaurant_datemenu_idx")})
@Getter
@Setter
@NoArgsConstructor
public class Menu extends AbstractNamedEntity {

    @Column(name = "date_menu", nullable = false)
    @NotNull
    private LocalDate dateMenu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonIgnore
    private Restaurant restaurant;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menu", cascade = CascadeType.REMOVE)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonIgnore
    private List<Dish> dishes;

    public Menu(Menu menu) {
        super(menu.id, menu.name);
        this.dateMenu = menu.dateMenu;
    }

    public Menu(Integer id, String name, LocalDate dateMenu) {
        super(id, name);
        this.dateMenu = dateMenu;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name=" + name +
                ", dateMenu='" + dateMenu +
                '}';
    }
}
