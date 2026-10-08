package com.inventario.inventario2.controller;

import com.inventario.inventario2.model.Producto;
import com.inventario.inventario2.service.CategoriaService;
import com.inventario.inventario2.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos")
public class ProductoController {

  @Autowired
  private ProductoService productoService;

  @Autowired
  private CategoriaService categoriaService;

  // 1. LISTAR Y BUSCAR
  @GetMapping
  public String listar(@RequestParam(value = "buscar", required = false) String buscar, Model model) {
    model.addAttribute("productos", productoService.listarTodos(buscar));
    model.addAttribute("totalValor", productoService.calcularValorTotal());
    model.addAttribute("buscar", buscar);
    return "index"; // Va a index.html
  }

  // 2. MOSTRAR FORMULARIO NUEVO
  @GetMapping("/nuevo")
  public String formularioNuevo(Model model) {
    model.addAttribute("producto", new Producto());
    model.addAttribute("categorias", categoriaService.listarTodos(null)); // Para el <select>
    return "formulario-producto";
  }

  // 3. GUARDAR (CREAR O ACTUALIZAR)
  @PostMapping("/guardar")
  public String guardar(@ModelAttribute Producto producto) {
    productoService.guardar(producto);
    return "redirect:/productos"; // Evita duplicados al recargar
  }

  // 4. MOSTRAR FORMULARIO EDITAR
  @GetMapping("/editar/{id}")
  public String formularioEditar(@PathVariable Long id, Model model) {
    Producto producto = productoService.obtenerPorId(id)
      .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
    model.addAttribute("producto", producto);
    model.addAttribute("categorias", categoriaService.listarTodos(null));  // Para el <select>
    return "formulario-producto";
  }

  // 5. VER DETALLE
  @GetMapping("/detalle/{id}")
  public String verDetalle(@PathVariable Long id, Model model) {
    Producto producto = productoService.obtenerPorId(id)
      .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
    model.addAttribute("producto", producto);
    return "detalle-producto";
  }

  // 6. ELIMINAR
  @GetMapping("/eliminar/{id}")
  public String eliminar(@PathVariable Long id) {
    productoService.eliminar(id);
    return "redirect:/productos";
  }
}
