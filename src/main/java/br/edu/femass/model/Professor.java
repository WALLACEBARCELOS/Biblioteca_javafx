package br.edu.femass.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
public class Professor extends Leitor{

    private String diciplina;
    public Professor(){

    }

    public Professor (String diciplina, String nome, String endereco, String telefone){
        this.diciplina = diciplina;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.prazoMaximoDevolucao = 30;
    }

    public String getDiciplina() {
        return diciplina;
    }

    public void setDiciplina(String diciplina) {
        this.diciplina = diciplina;
    }
}
