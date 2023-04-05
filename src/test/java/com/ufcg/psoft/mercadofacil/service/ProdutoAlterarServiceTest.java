package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testes para alteração do Produto")
public class ProdutoAlterarServiceTest {

    @Autowired
    ProdutoAlterarService driver;
    @MockBean
    ProdutoRepository<Produto, Long> produtoRepository;
    Produto produto;

    @BeforeEach
    void setup() {
        Mockito.when(produtoRepository.find(10L))
                .thenReturn(Produto.builder()
                        .id(10L)
                        .codigoBarra("123456789")
                        .nome("Produto Dez")
                        .fabricante("Empresa 10")
                        .preco(450.00)
                        .build());

        produto = produtoRepository.find(10L);

        Mockito.when(produtoRepository.update(produto))
                .thenReturn(Produto.builder()
                        .id(10L)
                        .codigoBarra("123456789")
                        .nome("Nome Produto Alterado")
                        .fabricante("Empresa 10")
                        .preco(450.00)
                        .build());
    }

    @Test
    @DisplayName("Quando altero o nome do produto com um nome válido")
    void test01() {
        produto.setNome("Nome Produto Alterado");

        Produto resultado = driver.alterar(produto);

        assertEquals("Nome Produto Alterado", resultado.getNome());
    }

    @Test
    @DisplayName("Quando o preço é menor ou igual a zero")
    void test02() {
        produto.setPreco(0);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> driver.alterar(produto));

        assertEquals("Preço Inválido", thrown.getMessage());
    }

    @Test
    @DisplayName("Quando altero o nome do produto é um nome inválido")
    void test03() {
        produto.setNome("");

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> driver.alterar(produto));

        assertEquals("Nome Inválido", thrown.getMessage());
    }

    @Test
    @DisplayName("Quando altero o nome do fabricante para um nome válido")
    void test04(){
        produto.setFabricante("");

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> driver.alterar(produto));

        assertEquals("Fabricante Inválido", thrown.getMessage());
    }

    @Test
    @DisplayName("Quando altero o nome do fabricante para um nome inválido")
    void test05(){
        produto.setFabricante("Fabricante Alterado");

        Produto produtoAlterado = driver.alterar(produto);

        assertEquals("Fabricante Alterado", produtoAlterado.getFabricante());
    }

    @Test
    @DisplayName("Quando altero o código de barras para um código válido")
    void test06(){}

    @Test
    @DisplayName("Quando altero o código de barras para um código inválido")
    void test07(){}

    @Test
    @DisplayName("Quando altero o preço do produto para um preço válido")
    void test08(){}








}
