package br.edu.femass.dao;

import br.edu.femass.model.Aluno;

import java.util.List;

public class DaoAluno extends Dao<Aluno> {

    public List<Aluno> buscarTodos() {
        return em.createQuery("select c from Aluno c order by c.nome").getResultList();
    }

    public List<Aluno> buscarTodosPorId() {
        return em.createQuery("select c from Aluno c order by c.id").getResultList();
    }
}