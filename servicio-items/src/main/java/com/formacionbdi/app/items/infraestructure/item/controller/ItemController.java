package com.formacionbdi.app.items.infraestructure.item.controller;

import com.formacionbdi.app.items.domain.item.models.entity.Item;
import com.formacionbdi.app.items.domain.item.ports.service.ItemService;
import com.formacionbdi.app.items.domain.producto.models.dto.ProductoDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
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

    //@HystrixCommand(fallbackMethod = "metodoAlternativo")
    @GetMapping("/{id}/{cantidad}")
    public Item findById(@PathVariable(name = "id") Long id, @PathVariable(name = "cantidad") Integer cantidad) {
        return this.itemService.findById(id, cantidad);
    }

    public Item metodoAlternativo(Long id, Integer cantidad) {
        return new Item(new ProductoDto(id, "Prueba", 202.12, LocalDate.now()), cantidad);
    }
}
