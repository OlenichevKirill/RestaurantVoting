package com.example.restaurantvoting.web.menuItem;

import com.example.restaurantvoting.model.MenuItem;
import com.example.restaurantvoting.service.MenuItemService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static com.example.restaurantvoting.util.validation.ValidationUtil.assureIdConsistent;
import static com.example.restaurantvoting.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminMenuItemController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class AdminMenuItemController {

    static final String REST_URL = "/api/admin/menus";

    private final MenuItemService menuItemService;

    @PostMapping(value = "/{menuId}/menuItems", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MenuItem> create(@PathVariable int menuId, @Valid @RequestBody MenuItem menuItem) {
        log.info("Create menuItem: {}", menuItem);
        checkNew(menuItem);
        MenuItem dishCreated = menuItemService.save(menuItem, menuId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(MenuItemController.REST_URL + "/{menuId}/menuItems/{dishId}")
                .buildAndExpand(menuId, dishCreated.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(dishCreated);
    }

    @PutMapping(value = "/{menuId}/menuItems/{menuItemsId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable int menuId, @PathVariable int menuItemsId, @Valid @RequestBody MenuItem menuItem) {
        log.info("Update menuItem {} with id {}", menuId, menuItemsId);
        assureIdConsistent(menuItem, menuItemsId);
        menuItemService.save(menuItem, menuId);
    }

    @DeleteMapping("/{menuId}/menuItems/{menuItemsId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int menuId, @PathVariable int menuItemsId) {
        log.info("Delete menuItem with id {}", menuId);
        menuItemService.delete(menuId, menuItemsId);
    }
}
