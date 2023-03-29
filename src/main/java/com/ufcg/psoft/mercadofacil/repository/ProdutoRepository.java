package com.ufcg.psoft.mercadofacil.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository<T, ID> {
    T save(T produto);
    T find(ID id);

    List<T> findAll();
    T update(T produto);
    void delete(T produto);
    void deleteAll();
}
