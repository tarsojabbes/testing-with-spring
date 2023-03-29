package com.ufcg.psoft.mercadofacil.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufcg.psoft.mercadofacil.model.Produto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Teste do Controlador de Produtos")
public class ProdutoV1Controller {

    @Autowired
    MockMvc mockMvc;
    Produto produto;
    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        produto = Produto.builder()
                .id(10L)
                .nome("Produto Dez")
                .codigoBarra("123456789")
                .preco(125.36)
                .build();
    }

    @Test
    @DisplayName("Quando altero o produto com nome válido")
    void test01() throws Exception {
        produto.setNome("Chiclete");

        String produtoModificadoJSONString = mockMvc.perform(put("/produtos/" + 10)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produto)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        Produto produtoModificado = objectMapper.readValue(produtoModificadoJSONString, Produto.class);

        Assertions.assertEquals("Chiclete", produtoModificado.getNome());

    }
}
