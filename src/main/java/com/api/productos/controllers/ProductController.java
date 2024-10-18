package com.api.productos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

import com.api.productos.services.ProductService;
import com.api.productos.entities.ProductEntity;

@RestController // Indica que esta clase es un controlador REST.
@RequestMapping("/api/v1/products") // Se establece una ruta para recibir las solicitudes HTTP.

public class ProductController {

    // Declara una variable de tipo ProductService para lograr utilizar los metodos de la clase.
    private final ProductService productsService;

    // El Constructor permite la inyección de dependencias de ProductService.
    @Autowired
    public ProductController(ProductService productsService) {
        this.productsService = productsService; // Asigna el servicio a la variable de instancia.
    }


    // { GET - ALL }  Este es un endpoint para devolver todos los productos.
    @GetMapping
    public List<ProductEntity> getProducts() {
        return productsService.getAllProducts(); // Llama al servicio para obtener todos los productos.
    }


    // { GET - ID }  Este endpoint es para obtener un producto específico por su ID.
    @GetMapping("/{id}") // El ID del producto se pasa como parte de la URL.

    public Optional<ProductEntity> getProduct(@PathVariable UUID id) {
        // Verifica si el producto existe
        if (productsService.getProductById(id).isPresent()) {
            return productsService.getProductById(id); // Retorna el nombre del producto si se encuentra.
        }
        return productsService.getProductById(id); //Retorna un null si no se encuentra el producto que se busca por medio del ID.
    }

    // { POST }  Este endpoint es para crear un nuevo producto.
    @PostMapping

    public String postProduct(@RequestBody ProductEntity product) { //El producto será enviado como un JSON en el cuerpo de la solicitud.

        //Se realiza una validacion de los campos para que no ingresen vacios.
        if(!(product.getCategory().isEmpty()) && !(product.getName().isEmpty())){

            // El metodo 'createProduct' crea un nuevo producto y a la vez, retornara el nombre del producto.
            String nameProduct = productsService.createProduct(product);
            return "El objeto " + nameProduct + " ha sido creado correctamente";
        }
        return "Los campos no son correctos para la creacion de un producto.";
    }

    // { PUT } Este endpoint es para actualizar un producto existente
    @PutMapping("/{id}") // El ID del producto se pasa como parte de la URL

    public String putProduct(@PathVariable UUID id, @RequestBody ProductEntity product) { //El producto será enviado como un JSON en el cuerpo de la solicitud.

        if(!(product.getCategory().isEmpty()) && !(product.getName().isEmpty())){

            // Intenta actualizar el producto y guarda el resultado
            Optional<ProductEntity> productUpdate = productsService.updateProductById(id, product);

            // Verifica si la actualización fue exitosa, verificando que el Optional no este null.
            if (productUpdate.isPresent()) {
                return "El producto con ID (" + id + ") se actualizó correctamente.";
            }
            return "El producto con ID (" + id + ") no se encontro."; // Mensaje de error si no se encuentra el producto
        }
        return "Los campos no son correctos para la actualizacion de un producto.";
    }

    // { DELETE } Este endpoint es para eliminar un producto existente por medio del ID
    @DeleteMapping("/{id}") // El ID del producto se pasa como parte de la URL

    public String deleteProduct(@PathVariable UUID id) {
        // Verifica si se eliminó el producto
        if (productsService.deleteProductById(id)) {
            return "Se eliminó correctamente el objeto con ID (" + id + ").";
        }
        return "No se logró encontrar el producto con ID (" + id + ")."; // Mensaje de error si no se encuentra el producto
    }
}

