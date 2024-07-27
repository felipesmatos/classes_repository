package com.example.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Locacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "imovel_id", nullable = false)
    private Imovel imovel;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "locacao")
    private List<ServicoImovel> servicos;

    @Column(nullable = false)
    private boolean ativa;

    // Getters e setters
}
