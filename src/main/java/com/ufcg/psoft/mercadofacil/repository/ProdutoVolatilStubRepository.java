package com.ufcg.psoft.mercadofacil.repository;

import com.ufcg.psoft.mercadofacil.model.Produto;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ProdutoVolatilStubRepository implements ProdutoRepository<Produto, Long> {

    @Override
    public Produto save(Produto produto) {
        return null;
    }

    @Override
    public Produto find(Long id) {
        if (id == 10L) {
            return Produto.builder()
                    .id(10L)
                    .codigoBarra("4012345678901")
                    .nome("Produto Dez")
                    .fabricante("Fabricante Dez")
                    .preco(125.36)
                    .build();
        }
        return null;
    }

    @Override
    public List<Produto> findAll() {
        return null;
    }

    @Override
    public Produto update(Produto produto) {
        if (produto.getId() == 10L) {
            return Produto.builder()
                    .id(10L)
                    .codigoBarra("123456789")
                    .nome("Produto Dez Alterado")
                    .fabricante("Fabricante Dez")
                    .preco(125.36)
                    .build();
        }
        return null;
    }

    @Override
    public void delete(Produto produto) {

    }

    @Override
    public void deleteAll() {

    }
}
