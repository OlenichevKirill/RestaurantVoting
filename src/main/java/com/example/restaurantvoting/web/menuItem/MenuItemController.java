package com.example.restaurantvoting.web.menuItem;

import com.example.restaurantvoting.model.MenuItem;
import com.example.restaurantvoting.service.MenuItemService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = MenuItemController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class MenuItemController {

    static final String REST_URL = "/api/menus";

    private final MenuItemService menuItemService;

    @GetMapping("/{menuId}/menuItems")
    public List<MenuItem> getAllByMenuId(@PathVariable int menuId) {
        log.info("Get all menuItems for menuId {}", menuId);
        return menuItemService.getAllByMenuId(menuId);
    }

    @GetMapping("/{menuId}/menuItems/{menuItemId}")
    public ResponseEntity<MenuItem> getByMenuIdAndDishId(@PathVariable int menuId, @PathVariable int menuItemId) {
        log.info("Get menuItem for menuId {} and menuItemId {}", menuId, menuItemId);
        return ResponseEntity.of(menuItemService.getByMenuIdAndMenuItemId(menuId, menuItemId));
    }
}
