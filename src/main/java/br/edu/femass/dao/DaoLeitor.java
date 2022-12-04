package br.edu.femass.dao;

import br.edu.femass.model.Leitor;
import br.edu.femass.model.Livro;

import java.util.List;

public class DaoLeitor extends Dao<Leitor>{

    public List<Leitor> buscarTodosPorId(){
        return em.createQuery("select c from Leitor c order by c.id").getResultList();
    }
}
