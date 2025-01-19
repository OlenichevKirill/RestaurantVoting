package com.example.restaurantvoting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "menu", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "date_menu"},
        name = "menu_unique_restaurant_date_menu_idx")})
@Getter
@Setter
@NoArgsConstructor
public class Menu extends AbstractBaseEntity {

    @Column(name = "date_menu", nullable = false)
    @NotNull
    private LocalDate dateMenu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menu")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<MenuItem> menuItems;

    public Menu(Menu menu) {
        super(menu.id);
        this.dateMenu = menu.dateMenu;
    }

    public Menu(Integer id, LocalDate dateMenu) {
        super(id);
        this.dateMenu = dateMenu;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", dateMenu='" + dateMenu +
                '}';
    }
}
