package br.edu.femass.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
public class Professor extends Leitor{

    private String disciplina;
    public Professor(){
        setPrazoMaximoDevolucao(prazoMaximoDevolucao = 30);
    }

    public Professor (String diciplina, String nome, String endereco, String telefone){
        super(nome, endereco, telefone);
        this.prazoMaximoDevolucao = 30;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String diciplina) {
        this.disciplina = diciplina;
    }
}
