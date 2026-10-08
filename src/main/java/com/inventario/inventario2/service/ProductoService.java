package com.inventario.inventario2.service;

import com.inventario.inventario2.model.Producto;
import com.inventario.inventario2.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

  @Autowired
  private ProductoRepository productoRepository;

  // 1. LISTAR TODOS (con o sin filtro de búsqueda)
  public List<Producto> listarTodos(String buscar) {
    if (buscar != null && !buscar.trim().isEmpty()) {
      return productoRepository.findByNombreContainingIgnoreCaseOrCategoriaNombreContainingIgnoreCase(buscar, buscar);
    }
    return productoRepository.findAll();
  }

  // 2. GUARDAR (crea o actualiza)
  public Producto guardar(Producto producto) {
    return productoRepository.save(producto);
  }

  // 3. OBTENER POR ID
  public Optional<Producto> obtenerPorId(Long id) {
    return productoRepository.findById(id);
  }

  // 4. ELIMINAR
  public void eliminar(Long id) {
    productoRepository.deleteById(id);
  }

  // 5. LÓGICA DE NEGOCIO: Calcular valor total del inventario
  public double calcularValorTotal() {
    return productoRepository.findAll().stream()
      .mapToDouble(p -> p.getPrecio() * p.getCantidad())
      .sum();
  }
}
