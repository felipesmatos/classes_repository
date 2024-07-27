package com.example.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private String nome;

    @OneToMany(mappedBy = "cliente")
    private List<Locacao> locacoes;

    @OneToMany(mappedBy = "cliente")
    private List<Aluguel> alugueis;

    public void setCpf( ) {
    }

    public void setCpf(String number) {
    }

    public void setNome(String joão) {
    }

    public boolean getNome() {
        return false;
    }

    // Getters e setters
}

