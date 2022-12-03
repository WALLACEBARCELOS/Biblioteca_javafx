package br.edu.femass.dao;

import br.edu.femass.model.Livro;

import java.util.List;

public class DaoLivro extends Dao<Livro>{

    public List<Livro> buscarTodos(){
        return em.createQuery("select c from Livro c order by c.titulo").getResultList();
    }
    public List<Livro> buscarTodosPorId(){
        return em.createQuery("select c from Livro c order by c.id").getResultList();
    }
}
