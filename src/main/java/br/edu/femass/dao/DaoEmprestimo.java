package br.edu.femass.dao;

import br.edu.femass.model.Emprestimo;


import java.util.List;

public class DaoEmprestimo extends Dao<Emprestimo>{

    public List<Emprestimo> buscarTodosPorId(){
        return em.createQuery("select c from Emprestimo c order by c.id").getResultList();
    }
}
