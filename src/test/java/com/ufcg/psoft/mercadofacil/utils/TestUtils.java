package com.ufcg.psoft.mercadofacil.utils;

import com.ufcg.psoft.mercadofacil.model.Produto;

public class TestUtils {

    public static Produto criarProduto(Long id, String nome, String fabricante,
                                       String codigoBarras, Double preco) {
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setPreco(preco);
        produto.setId(id);
        produto.setCodigoBarra(codigoBarras);
        produto.setFabricante(fabricante);

        return produto;
    }
}
