package com.ufcg.psoft.mercadofacil.repository;

import com.ufcg.psoft.mercadofacil.model.Lote;
import com.ufcg.psoft.mercadofacil.model.Produto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class VolatilLoteRepositoryTest {

    VolatilLoteRepository driver = new VolatilLoteRepository();
    Lote lote;
    Produto produto;

    @Test
    @DisplayName("Inserir o primeiro lote no banco")
    void teste01() {
        // Definir dados dos testes
        driver.deleteAll();
        produto = Produto.builder()
                .id(1L)
                .nome("Alface")
                .preco(1.00)
                .fabricante("Sr. Zé")
                .codigoBarra("123456789")
                .build();

        lote = Lote.builder()
                .id(10L)
                .numeroDeItens(100)
                .produto(produto)
                .build();

        // Realizar o procedimento do teste
        Lote resultado = driver.save(lote);

        // Verificar
        assertEquals(resultado.getId().longValue(), lote.getId().longValue());
        assertEquals(driver.findAll().size(), 1);
    }

    // Inserir um lote em um banco cheio
    @Test
    @DisplayName("Inserir produto em banco cheio")
    void test02() {
        produto = Produto.builder()
                    .id(2L)
                    .nome("Manteiga")
                    .preco(7.99)
                    .fabricante("Camponesa")
                    .codigoBarra("123456789")
                    .build();

        Lote lote1 = Lote.builder()
                    .id(10L)
                    .numeroDeItens(2)
                    .produto(produto)
                    .build();
        driver.save(lote1);

        Lote lote2 = Lote.builder()
                    .id(10L)
                    .numeroDeItens(2)
                    .produto(produto)
                    .build();
        Lote segundoLoteInserido = driver.save(lote2);
        assertEquals(segundoLoteInserido, lote2);
    }

    @Test
    @DisplayName("Procura lote existente por ID")
    void test03() {
        lote = Lote.builder()
                .id(10L)
                .numeroDeItens(100)
                .produto(produto)
                .build();

        driver.save(lote);
        Lote resultado = driver.find(10L);
        assertEquals(lote, resultado);
    }

    @Test
    @DisplayName("Procura lote por ID em banco vazio")
    void test04() {
        Lote resultado = driver.find(10L);
        assertNull(resultado);
    }

    @Test
    @DisplayName("Recupera todos os lotes salvos no banco")
    void test05() {
        Lote lote1 = Lote.builder()
                    .id(10L)
                    .numeroDeItens(2)
                    .produto(produto)
                    .build();

        Lote lote2 = Lote.builder()
                    .id(10L)
                    .numeroDeItens(2)
                    .produto(produto)
                    .build();

        driver.save(lote1);
        driver.save(lote2);

        ArrayList<Lote> lotesCadastrados = new ArrayList<Lote>();
        lotesCadastrados.add(lote1);
        lotesCadastrados.add(lote2);

        assertArrayEquals(lotesCadastrados.toArray(), driver.findAll().toArray());
        
    }

    @Test
    @DisplayName("Atualiza informação de um lote em banco cheio")
    void test06() {
        Lote lote1 = Lote.builder()
        .id(10L)
        .numeroDeItens(2)
        .produto(produto)
        .build();

        Lote lote2 = Lote.builder()
                .id(10L)
                .numeroDeItens(2)
                .produto(produto)
                .build();

        driver.save(lote1);
        driver.save(lote2);

        lote2.setNumeroDeItens(4);
        Lote resultado = driver.update(lote2);

        assertEquals(resultado, lote2);
        assertEquals(2, driver.findAll().size());
    }

    @Test
    @DisplayName("Exclui um lote em banco com mais de um elemento")
    void test07() {
        Lote lote1 = Lote.builder()
        .id(10L)
        .numeroDeItens(2)
        .produto(produto)
        .build();

        Lote lote2 = Lote.builder()
                .id(10L)
                .numeroDeItens(2)
                .produto(produto)
                .build();

        driver.save(lote1);
        driver.save(lote2);

        driver.delete(lote1);
        assertEquals(1, driver.findAll().size());
    }

    @Test
    @DisplayName("Exclui todos os lotes de um banco")
    void test08() {
        Lote lote = Lote.builder()
        .id(10L)
        .numeroDeItens(2)
        .produto(produto)
        .build();

        driver.save(lote);

        driver.deleteAll();
        assertEquals(0, driver.findAll().size());
    }


}