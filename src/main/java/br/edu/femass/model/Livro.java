package br.edu.femass.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String ano;
    @ManyToOne
    private Autor autor;


    public Livro(String titulo, String ano) {
        this.titulo = titulo;
        this.ano = ano;
    }

    public Livro (){

    }

    public String getTitulo() {
        return titulo;
    }

    public String getAno() {
        return ano;
    }

    public Autor getAutor() {
        return autor;
    }

    public Long getId() {
        return id;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        return this.getTitulo();
    }
}
