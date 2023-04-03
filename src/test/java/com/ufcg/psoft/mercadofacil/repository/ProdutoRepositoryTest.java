package com.ufcg.psoft.mercadofacil.repository;

import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@DisplayName("Teste do repositório do produto")
public class ProdutoRepositoryTest {

    @Autowired
    ProdutoRepository<Produto, Long> driver;

    Produto produto;

    @BeforeEach
    void setup() {
        produto = TestUtils.criarProduto(10L, "Produto Dez", "Fabricante Dez",
                "123456789", 100.00);
    }

    @Test
    @DisplayName("Quandro criar um novo produto com dados válidos")
    void test01() {
        Produto resultado = driver.save(produto);
        assertNotNull(resultado.getId());
        assertEquals("Produto Dez", resultado.getNome());
        assertEquals("Fabricante Dez", resultado.getFabricante());
        assertEquals("123456789", resultado.getCodigoBarra());
        assertEquals(100.00, resultado.getNome());
    }

}
