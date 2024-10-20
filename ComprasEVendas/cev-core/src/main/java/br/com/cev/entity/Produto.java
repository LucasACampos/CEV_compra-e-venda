package br.com.cev.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Long id;

    @Column
    private String nome;

    @Column
    private BigDecimal preco; //TODO talvez deveria ser outra entidade para ter o log de preços com o passar do tempo, mas de momento ficara aqui

    @Column
    private String descricao;

}
