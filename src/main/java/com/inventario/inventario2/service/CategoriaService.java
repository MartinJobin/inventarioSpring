package com.inventario.inventario2.service;

import com.inventario.inventario2.model.Categoria;
import com.inventario.inventario2.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

  @Autowired
  private CategoriaRepository categoriaRepository;

  public List<Categoria> listarTodos(String buscar) {
    if (buscar != null && !buscar.trim().isEmpty()) {
      return categoriaRepository.findByNombreContainingIgnoreCase(buscar);
    }
    return categoriaRepository.findAll();
  }

  public Categoria guardar(Categoria categoria) {
    return categoriaRepository.save(categoria);
  }

  public Optional<Categoria> obtenerPorId(Long id) {
    return categoriaRepository.findById(id);
  }

  public void eliminar(Long id) {
    categoriaRepository.deleteById(id);
  }
}
