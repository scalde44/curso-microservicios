package com.formacionbdi.app.items.infraestructure.item.controller;

import com.formacionbdi.app.items.domain.item.models.entity.Item;
import com.formacionbdi.app.items.domain.item.ports.service.ItemService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("item")
public class ItemController {
    @Qualifier("itemServiceFeign")
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> findAll() {
        return this.itemService.findAll();
    }

    @GetMapping("/{id}/{cantidad}")
    public Item findById(@PathVariable(name = "id") Long id, @PathVariable(name = "cantidad") Integer cantidad) {
        return this.itemService.findById(id, cantidad);
    }
}
