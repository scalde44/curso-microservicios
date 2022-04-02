package com.formacionbdi.app.productos.infraestructure.producto.adapters.service;

import com.formacionbdi.app.productos.domain.producto.models.dto.ProductoDto;
import com.formacionbdi.app.productos.domain.producto.models.entity.Producto;
import com.formacionbdi.app.productos.domain.producto.ports.repository.ProductoRepository;
import com.formacionbdi.app.productos.domain.producto.ports.service.ProductoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {
    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<ProductoDto> findAll() {
        return this.productoRepository.findAll().stream().map(producto -> producto.toProductoDto()).collect(Collectors.toList());
    }

    @Override
    public ProductoDto findById(Long id) {
        return this.productoRepository.findById(id).toProductoDto();
    }

    @Override
    public ProductoDto save(ProductoDto productoDto) {
        return this.productoRepository.save(new Producto(productoDto)).toProductoDto();
    }
}
