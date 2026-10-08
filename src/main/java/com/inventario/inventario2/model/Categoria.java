package com.inventario.inventario2.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "categorias")
public class Categoria {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 100)
  private String nombre;

  // Relación: Una categoría tiene MUCHOS productos
  @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Producto> productos;

  // Constructores, Getters y Setters (Genera con Alt+Insert en IntelliJ)
  public Categoria() {}
  public Categoria(String nombre) { this.nombre = nombre; }

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getNombre() { return nombre; }
  public void setNombre(String nombre) { this.nombre = nombre; }
  public List<Producto> getProductos() { return productos; }
  public void setProductos(List<Producto> productos) { this.productos = productos; }
}
