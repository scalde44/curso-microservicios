package com.formacionbdi.app.productos.domain.service;

import com.formacionbdi.app.productos.domain.dto.ProductoDto;
import com.formacionbdi.app.productos.domain.entity.Producto;

import java.util.List;

public interface ProductoService {
    public List<ProductoDto> findAll();

    public ProductoDto findById(Long id);
}
