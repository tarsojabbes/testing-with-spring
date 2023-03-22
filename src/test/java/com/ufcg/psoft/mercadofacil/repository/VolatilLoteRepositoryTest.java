package com.ufcg.psoft.mercadofacil.repository;

import com.ufcg.psoft.mercadofacil.model.Lote;
import com.ufcg.psoft.mercadofacil.model.Produto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

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


}