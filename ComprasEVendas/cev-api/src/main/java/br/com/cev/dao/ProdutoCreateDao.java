package br.com.cev.dao;

import java.math.BigDecimal;

public record ProdutoCreateDao(
    String nome,
    BigDecimal preco,
    String descricao
){}
