package com.example.repository;

import com.example.model.ServicoImovel;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import java.util.List;

public class ServicoImovelRepository {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab_04");
    private EntityManager em = emf.createEntityManager();

    @Transactional
    public void inserir(ServicoImovel servicoImovel) {
        em.getTransaction().begin();
        em.persist(servicoImovel);
        em.getTransaction().commit();
    }

    @Transactional
    public void atualizar(ServicoImovel servicoImovel) {
        em.getTransaction().begin();
        em.merge(servicoImovel);
        em.getTransaction().commit();
    }

    public List<ServicoImovel> listarTodos() {
        return em.createQuery("FROM ServicoImovel", ServicoImovel.class).getResultList();
    }

    public ServicoImovel buscarPorId(Long id) {
        return em.find(ServicoImovel.class, id);
    }

    public List<ServicoImovel> listarPorLocacao(Long locacaoId) {
        return em.createQuery("FROM ServicoImovel WHERE locacao.id = :locacaoId", ServicoImovel.class)
                .setParameter("locacaoId", locacaoId)
                .getResultList();
    }
}
