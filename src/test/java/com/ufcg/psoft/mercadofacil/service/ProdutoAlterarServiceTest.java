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
                        .codigoBarra("4012345678901")
                        .nome("Produto Dez")
                        .fabricante("Empresa 10")
                        .preco(450.00)
                        .build());

        produto = produtoRepository.find(10L);
    }

    @Test
    @DisplayName("Quando altero o nome do produto com um nome válido")
    void test01() {
        produto.setNome("Nome Produto Alterado");

        Mockito.when(produtoRepository.update(produto))
                .thenReturn(Produto.builder()
                        .id(10L)
                        .codigoBarra("4012345678901")
                        .nome("Nome Produto Alterado")
                        .fabricante("Empresa 10")
                        .preco(450.00)
                        .build());

        Produto resultado = driver.alterar(produto);

        assertEquals("Nome Produto Alterado", resultado.getNome());
    }

    @Test
    @DisplayName("Quando o preço é menor ou igual a zero")
    void test02() {
        produto.setPreco(0);

        Mockito.when(produtoRepository.update(produto))
                .thenThrow(RuntimeException.class);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> driver.alterar(produto));

        assertEquals("Preço Inválido", thrown.getMessage());
    }

    @Test
    @DisplayName("Quando altero o nome do produto é um nome inválido")
    void test03() {
        produto.setNome("");

        Mockito.when(produtoRepository.update(produto))
                .thenThrow(RuntimeException.class);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> driver.alterar(produto));

        assertEquals("Nome Inválido", thrown.getMessage());
    }

    @Test
    @DisplayName("Quando altero o nome do fabricante para um nome inválido")
    void test04(){
        produto.setFabricante("");

        Mockito.when(produtoRepository.update(produto))
                .thenThrow(RuntimeException.class);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> driver.alterar(produto));

        assertEquals("Fabricante Inválido", thrown.getMessage());
    }

    @Test
    @DisplayName("Quando altero o nome do fabricante para um nome válido")
    void test05(){
        produto.setFabricante("Fabricante Alterado");

        Mockito.when(produtoRepository.update(produto))
                .thenReturn(Produto.builder()
                        .id(10L)
                        .codigoBarra("4012345678901")
                        .nome("Nome Produto Alterado")
                        .fabricante("Fabricante Alterado")
                        .preco(450.00)
                        .build());


        Produto produtoAlterado = driver.alterar(produto);

        assertEquals("Fabricante Alterado", produtoAlterado.getFabricante());
    }

    @Test
    @DisplayName("Quando altero o código de barras para um código inválido")
    void test06(){
        produto.setCodigoBarra("123456789123");

        Mockito.when(produtoRepository.update(produto))
                .thenThrow(RuntimeException.class);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> driver.alterar(produto));

        assertEquals("Código de barras Inválido", thrown.getMessage());
    }

    @Test
    @DisplayName("Quando altero o código de barras para um código inválido")
    void test07(){
        produto.setCodigoBarra("4012345678901");

        Mockito.when(produtoRepository.update(produto))
                .thenReturn(Produto.builder()
                        .id(10L)
                        .codigoBarra("4012345678901")
                        .nome("Produto Dez")
                        .fabricante("Empresa 10")
                        .preco(450.00)
                        .build());

        Produto produtoAlterado = driver.alterar(produto);

        assertEquals("4012345678901", produtoAlterado.getCodigoBarra());
    }

    @Test
    @DisplayName("Quando altero o preço do produto para um preço válido")
    void test08(){
        produto.setPreco(1250.0);

        Mockito.when(produtoRepository.update(produto))
                .thenReturn(Produto.builder()
                        .id(10L)
                        .codigoBarra("4012345678901")
                        .nome("Produto Dez")
                        .fabricante("Empresa 10")
                        .preco(1250.0)
                        .build());

        Produto produtoAlterado = driver.alterar(produto);

        assertEquals(1250.0, produtoAlterado.getPreco());
    }








}
