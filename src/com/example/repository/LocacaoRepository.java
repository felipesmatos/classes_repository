package com.example.repository;

import com.example.model.Locacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import java.util.List;

public class LocacaoRepository {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab_04");
    private EntityManager em = emf.createEntityManager();

    @Transactional
    public void inserir(Locacao locacao) {
        em.getTransaction().begin();
        em.persist(locacao);
        em.getTransaction().commit();
    }

    @Transactional
    public void atualizar(Locacao locacao) {
        em.getTransaction().begin();
        em.merge(locacao);
        em.getTransaction().commit();
    }

    public List<Locacao> listarTodos() {
        return em.createQuery("FROM Locacao", Locacao.class).getResultList();
    }

    public Locacao buscarPorId(Long id) {
        return em.find(Locacao.class, id);
    }

    public List<Locacao> listarPorCliente(Long clienteId) {
        return em.createQuery("FROM Locacao WHERE cliente.id = :clienteId", Locacao.class)
                .setParameter("clienteId", clienteId)
                .getResultList();
    }
}
