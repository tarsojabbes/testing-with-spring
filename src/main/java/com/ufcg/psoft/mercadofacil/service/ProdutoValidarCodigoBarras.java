package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.Produto;

@FunctionalInterface
public interface ProdutoValidarCodigoBarras {
    public boolean isValidCodigoBarras(Produto p);
}
