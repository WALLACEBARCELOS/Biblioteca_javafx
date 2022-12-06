package br.edu.femass.dao;

import br.edu.femass.model.Professor;

import java.util.List;

public class DaoProfessor extends Dao<Professor> {

    public List<Professor> buscarTodos() {
        return em.createQuery("select c from Professor c order by c.nome").getResultList();
    }

    public List<Professor> buscarTodosPorId() {
        return em.createQuery("select c from Professor c order by c.id").getResultList();
    }
}