package ar.com.xeven.apiproductosspringsecurity.producto;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ProductoController.class})
@ExtendWith(SpringExtension.class)
public class ProductoControllerTest {
    @Autowired
    private ProductoController productoController;

    @MockBean
    private ProductoService productoService;

    @Test
    public void testConstructor() {
        // Arrange
        ProductoService productoService = new ProductoService(mock(ProductoRepository.class));

        // Act
        ProductoController actualProductoController = new ProductoController(productoService);

        // Assert
        assertTrue(actualProductoController.getProductos().isEmpty());
        assertTrue(productoService.getNombreProductos().isEmpty());
        assertTrue(productoService.getProductos().isEmpty());
    }

    @Test
    public void testGetProductos() throws Exception {
        // Arrange
        when(this.productoService.getProductos()).thenReturn(new ArrayList<Producto>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/productos");
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(this.productoController).build();

        // Act
        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testGetProductos2() throws Exception {
        // Arrange
        when(this.productoService.getProductos()).thenReturn(new ArrayList<Producto>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/productos");
        getResult.contentType("Not all who wander are lost");
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(this.productoController).build();

        // Act
        ResultActions actualPerformResult = buildResult.perform(getResult);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testGetProductoPorId() throws Exception {
        // Arrange
        Producto producto = new Producto();
        producto.setProducto_id(1);
        producto.setStock(1);
        producto.setNombre("Nombre");
        producto.setPrecio(10.0);
        when(this.productoService.getProductoPorId((Integer) any())).thenReturn(producto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/productos/{productoId}", 123);
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(this.productoController).build();

        // Act
        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"producto_id\":1,\"nombre\":\"Nombre\",\"precio\":10.0,\"stock\":1}"));
    }

    @Test
    public void testGetProductoPorNombre() throws Exception {
        // Arrange
        when(this.productoService.getProductoPorNombre(anyString())).thenReturn(new ArrayList<Producto>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/productos/buscar/{nombre}",
                "Nombre");
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(this.productoController).build();

        // Act
        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testGetProductoPorNombre2() throws Exception {
        // Arrange
        when(this.productoService.getProductoPorNombre(anyString())).thenReturn(new ArrayList<Producto>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/productos/buscar/{nombre}", "Nombre");
        getResult.contentType("Not all who wander are lost");
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(this.productoController).build();

        // Act
        ResultActions actualPerformResult = buildResult.perform(getResult);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testCrearProducto() throws Exception {
        // Arrange
        doNothing().when(this.productoService).save((Producto) any());

        Producto producto = new Producto();
        producto.setProducto_id(1);
        producto.setStock(1);
        producto.setNombre("Nombre");
        producto.setPrecio(10.0);
        String content = (new ObjectMapper()).writeValueAsString(producto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/productos/guardar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(this.productoController).build();

        // Act
        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteProducto() throws Exception {
        // Arrange
        doNothing().when(this.productoService).deleteById((Integer) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/productos/{productoId}", 123);
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(this.productoController).build();

        // Act
        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteProducto2() throws Exception {
        // Arrange
        doNothing().when(this.productoService).deleteById((Integer) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/productos/{productoId}", 123);
        deleteResult.contentType("Not all who wander are lost");
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(this.productoController).build();

        // Act
        ResultActions actualPerformResult = buildResult.perform(deleteResult);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetMiRol() throws Exception {
        // Arrange
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(this.productoController).build();

        // Act
        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGuardarProducto() throws Exception {
        // Arrange
        doNothing().when(this.productoService).save((Producto) any());

        Producto producto = new Producto();
        producto.setProducto_id(1);
        producto.setStock(1);
        producto.setNombre("Nombre");
        producto.setPrecio(10.0);
        String content = (new ObjectMapper()).writeValueAsString(producto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/productos/guardar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(this.productoController).build();

        // Act
        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk());
    }
}

