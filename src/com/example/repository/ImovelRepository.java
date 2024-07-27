package com.example.repository;

import com.example.model.Imovel;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import java.util.List;

public class ImovelRepository {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab_04");
    private EntityManager em = emf.createEntityManager();

    @Transactional
    public void inserir(Imovel imovel) {
        em.getTransaction().begin();
        em.persist(imovel);
        em.getTransaction().commit();
    }

    @Transactional
    public void atualizar(Imovel imovel) {
        em.getTransaction().begin();
        em.merge(imovel);
        em.getTransaction().commit();
    }

    public List<Imovel> listarTodos() {
        return em.createQuery("FROM Imovel", Imovel.class).getResultList();
    }

    public Imovel buscarPorId(Long id) {
        return em.find(Imovel.class, id);
    }
}
