package br.edu.femass.dao;

import br.edu.femass.model.Exemplar;

import java.util.List;

public class DaoExemplar extends Dao<Exemplar>{

    public List<Exemplar> buscarTodosPorId(){
        return em.createQuery("select c from Exemplar c order by c.dataAquisicao").getResultList();
    }
}