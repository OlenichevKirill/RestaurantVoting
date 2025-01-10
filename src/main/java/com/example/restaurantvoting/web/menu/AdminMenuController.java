package com.example.restaurantvoting.web.menu;

import com.example.restaurantvoting.model.Menu;
import com.example.restaurantvoting.service.MenuService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.example.restaurantvoting.util.validation.ValidationUtil.assureIdConsistent;
import static com.example.restaurantvoting.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class AdminMenuController {
    static final String REST_URL = "/api/admin/restaurants";

    private final MenuService menuService;

    @GetMapping("/{restaurantId}/menus")
    public List<Menu> getAllByRestaurantId(@PathVariable int restaurantId) {
        log.info("Get all menu for restaurantId {}", restaurantId);
        return menuService.getAllByRestaurantId(restaurantId);
    }

    @GetMapping("/{restaurantId}/menus/{menuId}")
    public Menu getByRestaurantIdAndMenuId(@PathVariable int restaurantId, @PathVariable int menuId) {
        log.info("Get menu for restaurantId {} and menuId {}", restaurantId, menuId);
        return menuService.getByRestaurantIdAndMenuId(restaurantId, menuId);
    }

    @PostMapping(value = "/{restaurantId}/menus", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> create(@PathVariable int restaurantId, @Valid @RequestBody Menu menu) {
        log.info("Create menu: {}", menu);
        checkNew(menu);
        Menu menuCreated = menuService.save(menu, restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{restaurantId}/menu/{menuId}")
                .buildAndExpand(restaurantId, menuCreated.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(menuCreated);
    }

    @PutMapping(value = "/{restaurantId}/menus/{menuId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable int restaurantId, @PathVariable int menuId, @Valid @RequestBody Menu menu) {
        log.info("Update menu {} with id {}", menu, menuId);
        assureIdConsistent(menu, menuId);
        menuService.save(menu, restaurantId);
    }
}
