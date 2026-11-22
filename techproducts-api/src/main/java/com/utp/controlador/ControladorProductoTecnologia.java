package com.utp.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.utp.interfaces.ServicioProductoTecnologia;
import com.utp.modelo.ProductoTecnologia;

@Controller
public class ControladorProductoTecnologia {

    @Autowired
    private ServicioProductoTecnologia servicio;

    @GetMapping("/")
    public String listar(Model modelo) {
        modelo.addAttribute("listado", servicio.findAll());
        return "index";  // Asegúrate de que este archivo index.html esté en /src/main/resources/templates
    }

    @GetMapping("/nuevo")
    public String nuevoProducto(Model modelo) {
        modelo.addAttribute("producto", new ProductoTecnologia());
        return "form";
    }

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute ProductoTecnologia producto) {
        servicio.save(producto);
        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    public String editarProducto(@PathVariable("id") int id, Model modelo) {
        Optional<ProductoTecnologia> producto = servicio.findById(id);
        if (producto.isPresent()) {
            modelo.addAttribute("producto", producto.get());
            return "form";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable("id") int id) {
        servicio.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/buscar")
    public String buscarProducto(@RequestParam("search") String search, Model model) {
        List<ProductoTecnologia> productos;

        if (search.matches("[0-9]+")) { // Si es un número, buscar por ID
            int id = Integer.parseInt(search);
            ProductoTecnologia producto = servicio.findById(id).orElse(null);
            productos = producto != null ? List.of(producto) : List.of(); // Si el producto existe, lo muestra, si no, lista vacía
        } else { // Si es texto, buscar por nombre
            productos = servicio.findByNombreContaining(search);
        }

        model.addAttribute("listado", productos); // Cambié 'productos' por 'listado'
        return "index"; // Regresa a la misma vista con los resultados
    }

}
