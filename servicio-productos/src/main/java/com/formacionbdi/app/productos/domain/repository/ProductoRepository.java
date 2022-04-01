package com.formacionbdi.app.productos.domain.repository;

import com.formacionbdi.app.productos.domain.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
