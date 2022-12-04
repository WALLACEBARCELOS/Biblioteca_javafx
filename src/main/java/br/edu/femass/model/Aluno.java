package br.edu.femass.model;
import javax.persistence.Entity;

@Entity
public class Aluno extends Leitor {
    private String matricula;

    public Aluno(){

    }
    public Aluno (String nome, String endereco, String telefone){
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.prazoMaximoDevolucao = 15;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
