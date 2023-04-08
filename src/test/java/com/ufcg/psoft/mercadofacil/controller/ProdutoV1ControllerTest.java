package com.ufcg.psoft.mercadofacil.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.repository.ProdutoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Teste do Controlador de Produtos")
public class ProdutoV1ControllerTest {

    @Autowired
    MockMvc driver;
    Produto produto;

    @Autowired
    ProdutoRepository<Produto, Long> produtoRepository;
    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        produto = produtoRepository.find(10L);
    }

    @Test
    @DisplayName("Quando altero o produto com nome válido")
    void test01() throws Exception {
        produto.setNome("Produto Dez Alterado");

        // Act
        String responseJsonString = driver.perform(put("/v1/produtos/" + produto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produto)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        Produto resultado = objectMapper.readValue(responseJsonString, Produto.ProdutoBuilder.class).build();

        // Assert
        assertEquals(resultado.getNome(), "Produto Dez Alterado");
    }

    @Test
    @DisplayName("Quando altero o produto com nome inválido")
    void test02() throws Exception {
        produto.setNome("");

        ResultActions response = driver.perform(put("/v1/produtos/" + produto.getId()))
                .andExpect(status().isBadRequest());

        response.andExpect(result -> assertTrue(result.getResolvedException() instanceof RuntimeException));
        assertEquals(produtoRepository.find(10L).getNome(), "Produto Dez");

    }

    @Test
    @DisplayName("Quando altero o produto com nome de fabricante válido")
    void test03() throws Exception{
        produto.setFabricante("Fabricante Dez Alterado");

        // Act
        String responseJsonString = driver.perform(put("/v1/produtos/" + produto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produto)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        Produto resultado = objectMapper.readValue(responseJsonString, Produto.ProdutoBuilder.class).build();

        // Assert
        assertEquals(resultado.getFabricante(), "Fabricante Dez Alterado");
    }

    @Test
    @DisplayName("Quando altero o produto com nome de fabricante inválido")
    void test04() throws Exception{
        produto.setFabricante("");

        ResultActions response = driver.perform(put("/v1/produtos/" + produto.getId()))
                .andExpect(status().isBadRequest());

        response.andExpect(result -> assertTrue(result.getResolvedException() instanceof RuntimeException));
        assertEquals(produtoRepository.find(10L).getFabricante(), "Fabricante Dez");


    }

    @Test
    @DisplayName("Quando altero o produto com preço inválido")
    void test05() throws Exception{
        produto.setPreco(-1);

        ResultActions response = driver.perform(put("/v1/produtos/" + produto.getId()))
                .andExpect(status().isBadRequest());

        response.andExpect(result -> assertTrue(result.getResolvedException() instanceof RuntimeException));
        assertEquals(produtoRepository.find(10L).getPreco(), 150);
    }

    @Test
    @DisplayName("Quando altero o produto com preço válido")
    void test06() throws Exception{
        produto.setPreco(125.36);

        // Act
        String responseJsonString = driver.perform(put("/v1/produtos/" + produto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produto)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        Produto resultado = objectMapper.readValue(responseJsonString, Produto.ProdutoBuilder.class).build();

        // Assert
        assertEquals(resultado.getPreco(),125.36);
    }

    @Test
    @DisplayName("Quando altero o produto com código de barras inválido")
    void test08() throws Exception{
        produto.setCodigoBarra("");

        ResultActions response = driver.perform(put("/v1/produtos/" + produto.getId()))
                .andExpect(status().isBadRequest());

        response.andExpect(result -> assertTrue(result.getResolvedException() instanceof RuntimeException));
        assertEquals(produtoRepository.find(10L).getCodigoBarra(), "4012345678901");
    }

    @Test
    @DisplayName("Quando altero o produto com código de barras válido")
    void test09() throws Exception{
        produto.setCodigoBarra("4012345678901");

        // Act
        String responseJsonString = driver.perform(put("/v1/produtos/" + produto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produto)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        Produto resultado = objectMapper.readValue(responseJsonString, Produto.ProdutoBuilder.class).build();

        // Assert
        assertEquals(resultado.getCodigoBarra(), "4012345678901");
    }
}
