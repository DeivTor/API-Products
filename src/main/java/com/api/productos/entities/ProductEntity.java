package com.api.productos.entities;

// Importaciones necesarias para la clase
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

// Anotaciones de Lombok para generar automáticamente métodos
@Data // Genera getters, setters, toString, hashCode y equals para todos los campos
@NoArgsConstructor // Genera un constructor sin parámetros
@AllArgsConstructor // Genera un constructor con todos los parámetros
public class ProductEntity {
    private UUID id;
    private String name;
    private String category;
    private double price;
    private int stock;
}

