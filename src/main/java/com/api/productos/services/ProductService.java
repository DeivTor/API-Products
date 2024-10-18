package com.api.productos.services;

// Importaciones necesarias para el funcionamiento del servicio.
import com.api.productos.entities.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

// Anotación que indica que esta clase es un servicio para que pueda ser llamada con el @Autowired en otras partes.
@Service
public class ProductService {
    // Lista que almacena los productos
    private List<ProductEntity> products;

    // Constructor que inicializa la lista de productos con algunos datos
    public ProductService() {
        products = new ArrayList<>(); // Inicializa la lista
        // Agrega productos a la lista
        products.add(new ProductEntity(UUID.randomUUID(), "Nevera", "Electrodomestico", 1000000, 10 ));
        products.add(new ProductEntity(UUID.randomUUID(), "Televisor", "Electrodoméstico", 1500000, 15));
        products.add(new ProductEntity(UUID.randomUUID(), "Lavadora", "Electrodoméstico", 1200000, 8));
        products.add(new ProductEntity(UUID.randomUUID(), "Smartphone", "Tecnología", 900000, 25));
        products.add(new ProductEntity(UUID.randomUUID(), "Computador Portátil", "Tecnología", 2300000, 5));
        products.add(new ProductEntity(UUID.randomUUID(), "Microondas", "Electrodoméstico", 350000, 12));
        products.add(new ProductEntity(UUID.randomUUID(), "Aire Acondicionado", "Electrodoméstico", 1800000, 7));
        products.add(new ProductEntity(UUID.randomUUID(), "Tablet", "Tecnología", 750000, 20));
        products.add(new ProductEntity(UUID.randomUUID(), "Auriculares Bluetooth", "Accesorios", 150000, 30));
        products.add(new ProductEntity(UUID.randomUUID(), "Licuadora", "Electrodoméstico", 200000, 18));
        products.add(new ProductEntity(UUID.randomUUID(), "Cámara Fotográfica", "Tecnología", 1200000, 6));
        products.add(new ProductEntity(UUID.randomUUID(), "Consola de Videojuegos", "Entretenimiento", 2200000, 10));
        products.add(new ProductEntity(UUID.randomUUID(), "Reloj Inteligente", "Accesorios", 450000, 25));
        products.add(new ProductEntity(UUID.randomUUID(), "Parlante Bluetooth", "Entretenimiento", 300000, 20));
        products.add(new ProductEntity(UUID.randomUUID(), "Plancha de Ropa", "Electrodoméstico", 90000, 40));
        products.add(new ProductEntity(UUID.randomUUID(), "Cafetera", "Electrodoméstico", 160000, 15));
        products.add(new ProductEntity(UUID.randomUUID(), "Ventilador", "Electrodoméstico", 120000, 35));
        products.add(new ProductEntity(UUID.randomUUID(), "Cámara de Seguridad", "Tecnología", 250000, 10));
        products.add(new ProductEntity(UUID.randomUUID(), "Horno Eléctrico", "Electrodoméstico", 400000, 12));
        products.add(new ProductEntity(UUID.randomUUID(), "Monitor", "Tecnología", 550000, 10));
        products.add(new ProductEntity(UUID.randomUUID(), "Teclado Mecánico", "Accesorios", 180000, 20));
    }

    // (POST){CREATE} Metodo crea e inserta un objeto en la lista 'products'
    public String createProduct(ProductEntity product) {
        product.setId(UUID.randomUUID()); // Asigna un nuevo ID único al producto
        products.add(product); // Añade el producto a la lista
        return product.getName(); // Retorna el nombre del producto creado
    }

    // (GET){READ - ALL} Metodo lee y retorna todos los productos que se encuentran en la lista
    public List<ProductEntity> getAllProducts() {
        return products; // Retorna la lista completa de productos
    }

    // (GET){READ - ID} Metodo busca y retorna el objeto que contenga un 'id' igual al suministrado
    public Optional<ProductEntity> getProductById(UUID id) {
        // Busca en la lista el producto con el ID proporcionado y lanza el primero de la busqueda.
        return products.stream().filter(product -> product.getId().equals(id)).findFirst();
    }

    // (PUT){UPDATE} Metodo para actualizar un producto existente dentro de 'products'
    public Optional<ProductEntity> updateProductById (UUID id, ProductEntity product) {
        // Busca el producto existente en la lista
        Optional<ProductEntity> productList = getProductById(id);
        // Si el producto existe, actualiza sus atributos
        if(productList.isPresent()) {
            productList.get().setName(product.getName());
            productList.get().setPrice(product.getPrice());
            productList.get().setCategory(product.getCategory());
            productList.get().setStock(product.getStock());
        }
        return productList; // Retorna el producto actualizado
    }

    // (DELETE){DELETE} Metodo para eliminar un producto existente dentro de 'products'
    public boolean deleteProductById(UUID id) {
        // Elimina el producto de la lista si se encuentra
        return products.removeIf(product -> product.getId().equals(id));
    }
}

