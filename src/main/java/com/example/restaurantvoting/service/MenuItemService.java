package com.example.restaurantvoting.service;

import com.example.restaurantvoting.model.MenuItem;
import com.example.restaurantvoting.repository.MenuItemRepository;
import com.example.restaurantvoting.repository.MenuRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class MenuItemService {

    private MenuItemRepository menuItemRepository;
    private MenuRepository menuRepository;

    public List<MenuItem> getAllByMenuId(int menuId) {
        return menuItemRepository.getAllByMenuId(menuId);
    }

    public Optional<MenuItem> getByMenuIdAndMenuItemId(int menuId, int menuItemId) {
        return menuItemRepository.getByMenuIdAndMenuItemId(menuId, menuItemId);
    }

    @Transactional
    public MenuItem save(MenuItem menuItem, int menuId) {
        menuItem.setMenu(menuRepository.getReferenceById(menuId));
        return menuItemRepository.save(menuItem);
    }
}
