package com.example.repository;

import com.example.model.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import java.util.List;

public class ClienteRepository {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab_04");
    private EntityManager em = emf.createEntityManager();

    @Transactional
    public void inserir(Cliente cliente) {
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
    }

    @Transactional
    public void atualizar(Cliente cliente) {
        em.getTransaction().begin();
        em.merge(cliente);
        em.getTransaction().commit();
    }

    public List<Cliente> listarTodos() {
        return em.createQuery("FROM Cliente", Cliente.class).getResultList();
    }

    public Cliente buscarPorId(Long id) {
        return em.find(Cliente.class, id);
    }

    public Cliente buscarPorCpf(String cpf) {
        return em.createQuery("FROM Cliente WHERE cpf = :cpf", Cliente.class)
                .setParameter("cpf", cpf)
                .getSingleResult();
    }
}
