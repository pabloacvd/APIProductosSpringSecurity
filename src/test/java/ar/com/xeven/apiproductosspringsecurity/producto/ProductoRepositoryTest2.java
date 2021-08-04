package ar.com.xeven.apiproductosspringsecurity.producto;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductoRepositoryTest2 {

    @Autowired
    private ProductoRepository repositorioPruebas;

    @AfterEach
    void tearDown() {
        repositorioPruebas.deleteAll();
    }

    @Test
    void findProductosByNombreContaining() {
        // dado:
        Producto producto = new Producto(
                1, "un producto", 22.5, 100);
        repositorioPruebas.save(producto);
        // cuando:
        boolean expected = !repositorioPruebas.findProductosByNombreContaining("prod").isEmpty();
        // deberia:
        assertTrue(expected);
    }
}