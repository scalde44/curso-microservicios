package com.formacionbdi.app.productos.domain.dto;

import com.formacionbdi.app.productos.domain.entity.Producto;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
public class ProductoDto {
    private Long id;
    private String nombre;
    private Double precio;
    private Date fechaCreacion;

    public ProductoDto(Producto producto) {
        this.id = producto.getId();
        this.nombre = producto.getNombre();
        this.precio = producto.getDoubleValuePrecio();
        this.fechaCreacion = producto.getCreateAt();
    }
}
