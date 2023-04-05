package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoAlterarPadraoService implements ProdutoAlterarService {

    @Autowired
    ProdutoRepository<Produto, Long> produtoRepository;
    ProdutoValidarCodigoBarrasImpl validarCodigoBarras;
    @Override
    public Produto alterar(Produto produtoAlterado) {
        if (produtoAlterado.getNome() == "") {
            throw new RuntimeException("Nome Inválido");
        }
        if (produtoAlterado.getFabricante() == "") {
            throw new RuntimeException("Fabricante Inválido");
        }
        if (!validarCodigoBarras.isValidCodigoBarras(produtoAlterado)) {
            throw new RuntimeException("Código de barras Inválido");
        }
        if (produtoAlterado.getPreco() <= 0) {
            throw new RuntimeException("Preço Inválido");
        }
        return produtoRepository.update(produtoAlterado);
    }
}
