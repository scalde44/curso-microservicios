package com.formacionbdi.app.items.infraestructure.client;

import com.formacionbdi.app.items.domain.producto.models.dto.ProductoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "servicio-productos")
@RequestMapping("product")
public interface ProductoClienteFeignRest {

    @GetMapping
    public List<ProductoDto> listar();

    @GetMapping("/{id}")
    public ProductoDto listarPorId(@PathVariable(name = "id") Long id);
}