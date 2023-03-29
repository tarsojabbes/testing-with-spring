package com.ufcg.psoft.mercadofacil.repository;

import com.ufcg.psoft.mercadofacil.model.Produto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProdutoStubRepository implements ProdutoRepository<Produto, Long>{
    @Override
    public Produto save(Produto produto) {
        return null;
    }

    @Override
    public Produto find(Long aLong) {
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
                    .codigoBarra("31256289102")
                    .nome("Chiclete")
                    .fabricante("Empresa Dez Alterado")
                    .preco(500.00)
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
