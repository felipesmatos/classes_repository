package com.example.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Imovel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private boolean disponivel;

    @OneToMany(mappedBy = "imovel")
    private List<Locacao> locacoes;

    public void setEndereco(String s) {
    }

    public void setDisponivel(boolean b) {
    }

    public boolean getEndereco() {
        return false;
    }

    // Getters e setters
}

