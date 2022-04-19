package com.formacionbdi.app.items.infraestructure.item.controller;

import com.formacionbdi.app.items.domain.item.models.entity.Item;
import com.formacionbdi.app.items.domain.item.ports.service.ItemService;
import com.formacionbdi.app.items.domain.producto.models.dto.ProductoDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("item")
public class ItemController {
    private static final String NAME_CIRCUIT = "item";
    private static final Logger log = LoggerFactory.getLogger(ItemController.class);
    @Qualifier("itemServiceFeign")
    private final ItemService itemService;
    private final CircuitBreakerFactory circuitBreakerFactory;

    public ItemController(ItemService itemService, CircuitBreakerFactory circuitBreakerFactory) {
        this.itemService = itemService;
        this.circuitBreakerFactory = circuitBreakerFactory;
    }

    @GetMapping
    public List<Item> findAll(@RequestParam(name = "nombre", required = false) String nombre,
                              @RequestHeader(name = "token-request", required = false) String token) {
        log.info("nombre = " + nombre);
        log.info("token = " + token);
        return this.itemService.findAll();
    }

    // Configuracion circuitbreaker manual
    //@HystrixCommand(fallbackMethod = "metodoAlternativo")
    @GetMapping("/{id}/{cantidad}")
    public Item detalleImplementacionConfig(@PathVariable(name = "id") Long id, @PathVariable(name = "cantidad") Integer cantidad) {
        return this.circuitBreakerFactory.create(NAME_CIRCUIT)
                .run(() -> this.itemService.findById(id, cantidad),
                        (error) -> metodoAlternativo(id, cantidad, error));
    }

    // Configuracion circuitbreaker automatica con anotacion
    @CircuitBreaker(name = NAME_CIRCUIT, fallbackMethod = "metodoAlternativo")
    @GetMapping("/alternativo/{id}/{cantidad}")
    public Item detalleImplementacionAuto(@PathVariable(name = "id") Long id, @PathVariable(name = "cantidad") Integer cantidad) {
        return this.itemService.findById(id, cantidad);
    }

    // Configuracion circuitbreaker automatica con anotacion
    @TimeLimiter(name = NAME_CIRCUIT, fallbackMethod = "segundoMetodoAlternativo")
    @GetMapping("/timelimiter/{id}/{cantidad}")
    public CompletableFuture<Item> detalleImplementacionTimeLimiter(@PathVariable(name = "id") Long id, @PathVariable(name = "cantidad") Integer cantidad) {
        return CompletableFuture.supplyAsync(() -> this.itemService.findById(id, cantidad));
    }

    public Item metodoAlternativo(Long id, Integer cantidad, Throwable error) {
        log.error(error.getMessage());
        return new Item(new ProductoDto(id, "CORTOCIRCUITO", 202.12, LocalDate.now()), cantidad);
    }

    public CompletableFuture<Item> segundoMetodoAlternativo(Long id, Integer cantidad, Throwable error) {
        log.error(error.getMessage());
        return CompletableFuture.supplyAsync(() -> new Item(new ProductoDto(id, "CORTOCIRCUITO", 202.12, LocalDate.now()), cantidad));
    }
}
