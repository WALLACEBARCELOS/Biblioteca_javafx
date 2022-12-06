package br.edu.femass.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucao;
    @ManyToOne(cascade = {CascadeType.DETACH})
    private Exemplar exemplar;
    @ManyToOne(cascade = {CascadeType.DETACH})
    private Leitor leitor;

    public Emprestimo(Exemplar exemplar, Leitor leitor) {
        this.dataEmprestimo = LocalDate.now();
        this.dataPrevistaDevolucao = LocalDate.now().plusDays(leitor.getPrazoMaximoDevolucao());
        this.exemplar = exemplar;
        this.leitor = leitor;
    }

    public Emprestimo(){

    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public Leitor getLeitor() {
        return leitor;
    }

    public void setLeitor(Leitor leitor) {
        this.leitor = leitor;
    }

    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public void setDataPrevistaDevolucao(LocalDate dataPrevistaDevolucao) {
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }

    //verifica se a data e anterior a data especificada
    public boolean atraso(){
        return this.getDataPrevistaDevolucao().isBefore(LocalDate.now());
    }
}
