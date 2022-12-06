package br.edu.femass.dao;

import br.edu.femass.model.Emprestimo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Dao<E> {
    protected static EntityManagerFactory emf;
    protected static EntityManager em;

    static {
        try {
            emf = Persistence.createEntityManagerFactory("jpa_ProvaN2");
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Dao() {
        em = emf.createEntityManager();
    }

    public void inserir(E entidade) {
        em.getTransaction().begin();
        em.persist(entidade);
        em.getTransaction().commit();
    }


    public void apagar(E entidade) {
        em.getTransaction().begin();
        em.remove(entidade);
        em.getTransaction().commit();
    }

    public void alterar(E entidade) {
        em.getTransaction().begin();
        em.merge(entidade);
        em.getTransaction().commit();
    }

    public void inserirP(Emprestimo emprestimo) {
        em.getTransaction().begin();
        em.persist(emprestimo);
        em.getTransaction().commit();
    }
}
