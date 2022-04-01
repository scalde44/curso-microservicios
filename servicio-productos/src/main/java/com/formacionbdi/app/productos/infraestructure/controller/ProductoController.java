package com.formacionbdi.app.productos.infraestructure.controller;

import com.formacionbdi.app.productos.domain.dto.ProductoDto;
import com.formacionbdi.app.productos.domain.service.ProductoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping()
    public List<ProductoDto> findAll() {
        return this.productoService.findAll();
    }

    @GetMapping("/{id}")
    public ProductoDto findById(@PathVariable(name = "id") Long id) {
        return this.productoService.findById(id);
    }
}
