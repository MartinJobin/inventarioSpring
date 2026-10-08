package com.inventario.inventario2.model;

import jakarta.persistence.*;

@Entity
@Table(name = "productos")
public class Producto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 100)
  private String nombre;

  @Column(nullable = false)
  private Double precio;

  @Column(nullable = false)
  private Integer cantidad;

  @ManyToOne
  @JoinColumn(name = "categoria_id", nullable = false)
  private Categoria categoria;

  // Constructor vacío (obligatorio para JPA)
  public Producto() {}

  // Constructor con parámetros (opcional)
  public Producto(String nombre, Double precio, Integer cantidad, Categoria categoria) {
    this.nombre = nombre;
    this.precio = precio;
    this.cantidad = cantidad;
    this.categoria = categoria;
  }

  // ==================== GETTERS Y SETTERS ====================
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public String getNombre() { return nombre; }
  public void setNombre(String nombre) { this.nombre = nombre; }

  // ¡ESTOS SON LOS QUE FALTABAN!
  public Double getPrecio() { return precio; }
  public void setPrecio(Double precio) { this.precio = precio; }

  public Integer getCantidad() { return cantidad; }
  public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

  public Categoria getCategoria() { return categoria; }
  public void setCategoria(Categoria categoria) { this.categoria = categoria; }
}
