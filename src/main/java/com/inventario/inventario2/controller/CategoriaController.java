package com.inventario.inventario2.controller;

import com.inventario.inventario2.model.Categoria;
import com.inventario.inventario2.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

  @Autowired
  private CategoriaService categoriaService;

  @GetMapping
  public String listar(@RequestParam(value = "buscar", required = false) String buscar, Model model) {
    /// Ahora usa el método con búsqueda que tú creaste
    model.addAttribute("categorias", categoriaService.listarTodos(buscar));
    model.addAttribute("buscar", buscar);
    return "categorias";
  }

  @GetMapping("/nuevo")
  public String formularioNuevo(Model model) {
    model.addAttribute("categoria", new Categoria());
    return "formulario-categoria";
  }

  @PostMapping("/guardar")
  public String guardar(@ModelAttribute Categoria categoria) {
    categoriaService.guardar(categoria);
    return "redirect:/categorias";
  }
}
