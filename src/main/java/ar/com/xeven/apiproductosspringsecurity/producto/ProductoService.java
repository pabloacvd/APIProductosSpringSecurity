package ar.com.xeven.apiproductosspringsecurity.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> getProductos() {
            return productoRepository.findAll();
    }

    public List<String> getNombreProductos() {
        List<Producto> productos = productoRepository.findAll();
        return productos.stream()
                .map(producto -> producto.getNombre())
                .collect(Collectors.toList());
    }

    public Producto getProductoPorId(Integer productoId) {
        return productoRepository.findById(productoId).orElse(null);
    }

    public List<Producto> getProductoPorNombre(String nombre) {
        return productoRepository.findProductosByNombreContaining(nombre);
    }

    public void deleteById(Integer productoId) {
        productoRepository.deleteById(productoId);
    }

    public void save(Producto producto) {
        if (producto.getProducto_id() != null) {
            Producto productoExistente = productoRepository.findById(producto.getProducto_id()).orElse(null);
            if (productoExistente != null) {
                if (producto.getNombre() != null) productoExistente.setNombre(producto.getNombre());
                if (producto.getPrecio() != null) productoExistente.setPrecio(producto.getPrecio());
                if (producto.getStock() != null) productoExistente.setStock(producto.getStock());
                productoRepository.save(productoExistente);
                return;
            } else producto.setProducto_id(null);
        }
        productoRepository.save(producto);
    }
}
