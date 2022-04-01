package com.formacionbdi.app.productos.domain.service;

import com.formacionbdi.app.productos.domain.dto.ProductoDto;
import com.formacionbdi.app.productos.domain.entity.Producto;
import com.formacionbdi.app.productos.domain.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceJpa implements ProductoService {
    private final ProductoRepository productoRepository;

    public ProductoServiceJpa(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<ProductoDto> findAll() {
        return this.productoRepository.findAll().stream()
                .map(producto -> new ProductoDto(producto)).collect(Collectors.toList());
    }

    @Override
    public ProductoDto findById(Long id) {
        return this.productoRepository.findById(id)
                .map(producto -> new ProductoDto(producto)).orElse(null);
    }
}
