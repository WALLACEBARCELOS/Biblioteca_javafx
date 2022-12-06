package br.edu.femass.model;
import javax.persistence.Entity;

@Entity
public class Aluno extends Leitor {
    private String matricula;

    public Aluno(){
        setPrazoMaximoDevolucao(prazoMaximoDevolucao = 15);
    }
    public Aluno(String nome, String endereco, String telefone, String matricula) {
        super(nome, endereco, telefone);
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
