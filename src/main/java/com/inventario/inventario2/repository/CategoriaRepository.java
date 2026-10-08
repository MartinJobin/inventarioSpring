package com.inventario.inventario2.repository;

import com.inventario.inventario2.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List; // <--- ¡ESTA ES LA LÍNEA QUE FALTABA!

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
  // Aquí ya reconoce qué es List
  List<Categoria> findByNombreContainingIgnoreCase(String nombre);
}
