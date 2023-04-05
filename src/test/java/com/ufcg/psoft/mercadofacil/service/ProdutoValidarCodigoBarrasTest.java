package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.repository.ProdutoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class ProdutoValidarCodigoBarrasTest {

    @Autowired
    ProdutoValidarCodigoBarras validador;

    @MockBean
    ProdutoRepository<Produto, Long> produtoRepository;
    Produto produto;

    @Test
    @DisplayName("Valida código de barras válido")
    void test01() {
        produto = Produto.builder()
                .id(10L)
                .nome("Produto A")
                .codigoBarra("4012345678901")
                .fabricante("ABC")
                .preco(15.5)
                .build();

        Assertions.assertTrue(validador.isValidCodigoBarras(produto));
    }

    @Test
    @DisplayName("Valida código de barras vazio")
    void test02() {
        produto = Produto.builder()
                .id(10L)
                .nome("Produto A")
                .codigoBarra("")
                .fabricante("ABC")
                .preco(15.5)
                .build();

        Assertions.assertFalse(validador.isValidCodigoBarras(produto));
    }

    @Test
    @DisplayName("Valida código de barras inválido")
    void test03() {
        produto = Produto.builder()
                .id(10L)
                .nome("Produto A")
                .codigoBarra("1234567891234")
                .fabricante("ABC")
                .preco(15.5)
                .build();

        Assertions.assertFalse(validador.isValidCodigoBarras(produto));
    }

}
