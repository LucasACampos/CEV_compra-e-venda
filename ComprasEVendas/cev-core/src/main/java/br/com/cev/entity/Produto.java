package br.com.cev.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "PRODUTO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id
    @SequenceGenerator(name = "produto_seq", sequenceName = "produto_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_seq")
    @Column
    private Long id;

    @Column
    private String nome;

    @Column
    private BigDecimal preco; //TODO talvez deveria ser outra entidade para ter o log de preços com o passar do tempo, mas de momento ficara aqui

    @Column
    private String descricao;

}
