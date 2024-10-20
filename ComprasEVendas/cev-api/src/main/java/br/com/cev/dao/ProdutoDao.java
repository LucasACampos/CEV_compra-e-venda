package br.com.cev.dao;

import br.com.cev.entity.Produto;

import java.math.BigDecimal;

public record ProdutoDao(
    Long id,
    String nome,
    BigDecimal preco,
    String descricao
) {

    public ProdutoDao(Produto produto) {
        this(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getDescricao()
        );
    }
}
