package ie.atu.week8.projectexercise;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAllProducts() {
    }

    @Test
    void testGetProductById()  throws Exception {
        Product product =  new Product(1l, "kettle", "Boils", 35);
        when(productService.getProductById(1l)).thenReturn(Optional.of(product));

        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("kettle"));
    }

    @Test
    void testCreateProduct() throws Exception {
        Product product =  new Product(null, "kettle", "Boils", 35);
        when(productService.saveProduct(any(Product.class))).thenReturn(product);

        ObjectMapper om = new ObjectMapper();
        String valueJson = om.writeValueAsString(product);

        mockMvc.perform(post("/products").contentType("application/json").content(valueJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("kettle"));



    }

    @Test
    void testUpdateProduct() throws Exception {
        Product product =  new Product(1l, "kettle", "Boils", 35);
        when(productService.getProductById(1l)).thenReturn(Optional.of(product));

        ObjectMapper om = new ObjectMapper();
        String valueJson = om.writeValueAsString(product);

        mockMvc.perform(put("/products/1").contentType("application/json").content(valueJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("kettle"));
    }

    @Test
    void deleteProduct() {
    }
}