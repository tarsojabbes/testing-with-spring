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

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        assertEquals(resultado.getNome(), "Produto Dez Alterado");}
}
