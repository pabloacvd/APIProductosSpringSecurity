package ar.com.xeven.apiproductosspringsecurity.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping()
    public List<Producto> getProductos(){
        return productoService.getProductos();
    }

    @GetMapping("{productoId}")
    public Producto getProductoPorId(@PathVariable Integer productoId){
        return productoService.getProductoPorId(productoId);
    }

    @GetMapping("buscar/{nombre}")
    public List<Producto> getProductoPorNombre(@PathVariable String nombre){
        return productoService.getProductoPorNombre(nombre);
    }

    @PostMapping("guardar")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void crearProducto(@RequestBody Producto producto){
        productoService.save(producto);
    }

    @PutMapping("guardar")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void guardarProducto(@RequestBody Producto producto){
        productoService.save(producto);
    }

    @DeleteMapping("{productoId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteProducto(@PathVariable Integer productoId){
        productoService.deleteById(productoId);
    }

    @GetMapping("/mi-rol")
    public String getMiRol(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return securityContext.getAuthentication().getAuthorities().toString();
    }

}
