package com.inventario.inventario2.repository;

import com.inventario.inventario2.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

  // Este método es el que usa tu Service para buscar
  List<Producto> findByNombreContainingIgnoreCaseOrCategoriaNombreContainingIgnoreCase(String nombre, String categoria);
}
