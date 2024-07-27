package com.example.model;

import javax.persistence.*;

@Entity
public class ServicoImovel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "locacao_id", nullable = false)
    private Locacao locacao;

    @Column(nullable = false)
    private String descricao;

    // Getters e setters
}
