package com.ufcg.psoft.mercadofacil.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Produto {

    private Long id;
    private String nome;
    private double preco;
    private String codigoBarra;
    private String fabricante;

    public Produto(Long id, String nome, double preco, String codigoBarra, String fabricante) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.codigoBarra = codigoBarra;
        this.fabricante = fabricante;
    }

    public Produto() {}
}

