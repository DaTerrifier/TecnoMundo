package com.utp.interfaces;
import org.springframework.data.repository.CrudRepository;
import com.utp.modelo.ProductoTecnologia;
import java.util.*;

public interface ServicioProductoTecnologia extends CrudRepository<ProductoTecnologia, Integer>{
    public List<ProductoTecnologia> findByNombreContaining(String palabra);
}
