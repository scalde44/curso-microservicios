package com.formacionbdi.app.productos.infraestructure.producto.controller;

import com.formacionbdi.app.productos.domain.producto.models.dto.ProductoDto;
import com.formacionbdi.app.productos.domain.producto.ports.service.ProductoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public List<ProductoDto> findAll() {
        return this.productoService.findAll();
    }

    @GetMapping("/{id}")
    public ProductoDto findById(@PathVariable(name = "id") Long id) {
        return this.productoService.findById(id);
    }

    @PostMapping
    public ProductoDto save(@RequestBody ProductoDto productoDto) {
        return this.productoService.save(productoDto);
    }
}
