package ar.com.xeven.apiproductosspringsecurity.producto;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductoRepositoryTest {

    @Autowired
    private ProductoRepository productoRepository;

    @AfterEach
    void tearDown() {
        productoRepository.deleteAll();
    }

    @Test
    void shouldNotReturnProductsIfTheNameIsNotFound() {
        // Given
        String nombre = "asdfgasdfgsdfg";
        // When
        List<Producto> productosEncontrados = productoRepository.findProductosByNombreContaining(nombre);
        // Then
        assertThat(productosEncontrados).isEmpty();
    }

    @Test
    void shouldReturnProductsIfTheNameExists(){
        // Given
        String nombre = "silla";
        Producto producto1 = new Producto(1, "silla", 10.5, 10);
        Producto producto2 = new Producto(2, "sillon", 20.75, 15);
        productoRepository.save(producto1);
        productoRepository.save(producto2);
        // When
        List<Producto> productosEncontrados = productoRepository.findProductosByNombreContaining(nombre);
        // Then
        assertThat(productosEncontrados).isNotEmpty();

        assertThat(productosEncontrados.get(0)).isEqualTo(producto1);

        assertThat(productosEncontrados.get(0))
                .hasFieldOrPropertyWithValue("producto_id",1)
                .hasFieldOrPropertyWithValue("nombre","silla");

    }
}
