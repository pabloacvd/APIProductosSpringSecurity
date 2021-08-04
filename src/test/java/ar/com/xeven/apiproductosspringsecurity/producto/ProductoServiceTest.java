package ar.com.xeven.apiproductosspringsecurity.producto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepositoryTest;
    private ProductoService productoServiceTest;

    @BeforeEach
    void setUp() {
        productoServiceTest = new ProductoService(productoRepositoryTest);
    }

    @Test
    void canGetProductos() {
        // given
            /* productoService and productoRepository */
        // when
            productoServiceTest.getProductos();
        // then
            verify(productoRepositoryTest).findAll();
    }

    @Test
    void getNombreProductos() {
    }

    @Test
    void getProductoPorId() {
    }

    @Test
    void puedeEncontrarProductosConUnNombreExistente(){
        //Given
            // un repositorio y un servicio
            String nombre="silla";
        //When
            productoServiceTest.getProductoPorNombre(nombre);
        //Then
        verify(productoRepositoryTest).findProductosByNombreContaining(nombre);
    }


    @Test
    void deleteById() {
    }

    @Test
    void save() {
        // given
        Producto newProducto = new Producto(1, "silla", 20.5, 200);
        // when
        productoServiceTest.save(newProducto);
        // then
        ArgumentCaptor<Producto> productoCaptor = ArgumentCaptor.forClass(Producto.class);
        verify(productoRepositoryTest).save(productoCaptor.capture());
        Producto productoCapturado = productoCaptor.getValue();
        assertThat(productoCapturado).isEqualTo(newProducto);
    }
    @Test
    void actualizarProductoConNombreNull() {
        // given
        Producto newProducto = new Producto(
                1, null, 20.5, 200);
        // when
        productoServiceTest.save(newProducto);
        // then
        assertThat(newProducto.getNombre()).isNull();
        assertThat(newProducto.getPrecio()).isNotNull();
    }

}