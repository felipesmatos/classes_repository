package com.example.repository;

import com.example.model.Aluguel;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import java.util.List;

public class AluguelRepository {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab_04");
    private EntityManager em = emf.createEntityManager();

    @Transactional
    public void inserir(Aluguel aluguel) {
        em.getTransaction().begin();
        em.persist(aluguel);
        em.getTransaction().commit();
    }

    @Transactional
    public void atualizar(Aluguel aluguel) {
        em.getTransaction().begin();
        em.merge(aluguel);
        em.getTransaction().commit();
    }

    public List<Aluguel> listarTodos() {
        return em.createQuery("FROM Aluguel", Aluguel.class).getResultList();
    }

    public Aluguel buscarPorId(Long id) {
        return em.find(Aluguel.class, id);
    }

    public List<Aluguel> listarPorCliente(Long clienteId) {
        return em.createQuery("FROM Aluguel WHERE cliente.id = :clienteId", Aluguel.class)
                .setParameter("clienteId", clienteId)
                .getResultList();
    }
}
