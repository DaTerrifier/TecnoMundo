package com.utp.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.utp.interfaces.ServicioProductoTecnologia;
import com.utp.modelo.ProductoTecnologia;

@RestController
@RequestMapping("/tecnologia")
public class ApiRestProductoTecnologia {

    @Autowired
    private ServicioProductoTecnologia servicio;

    @GetMapping("/listar")
    public List<ProductoTecnologia> listar() {
        return (List<ProductoTecnologia>) servicio.findAll();
    }

    @PostMapping("/guardar")
    public void guardar(@RequestBody ProductoTecnologia producto) {
        servicio.save(producto);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ProductoTecnologia> actualizar(@PathVariable("id") int id, @RequestBody ProductoTecnologia producto) {
        Optional<ProductoTecnologia> productoExistente = servicio.findById(id);
        if (productoExistente.isPresent()) {
            ProductoTecnologia actualizado = productoExistente.get();
            actualizado.setNombre(producto.getNombre());
            actualizado.setPrecio(producto.getPrecio());
            actualizado.setImagen(producto.getImagen());
            servicio.save(actualizado);
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable("id") int id) {
        servicio.deleteById(id);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<ProductoTecnologia> buscarPorId(@PathVariable int id) {
        Optional<ProductoTecnologia> producto = servicio.findById(id);
        return producto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/buscarPorNombre")
    public List<ProductoTecnologia> buscarPorNombre(@RequestParam("palabra") String palabra) {
        return servicio.findByNombreContaining(palabra);
    }
}
