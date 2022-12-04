package br.edu.femass.model;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Exemplar {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private LocalDate dataAquisicao;

        private String dataCompleta;
        @ManyToOne(cascade = CascadeType.ALL)
        private Livro livro;


        public Exemplar(){

        }

        public Exemplar(Livro livro) {
                this.dataAquisicao = LocalDate.now();
                this.livro = livro;
        }


        public LocalDate getDataAquisicao() {
                return dataAquisicao;
        }

        public void setDataAquisicao(LocalDate dataAquisicao) {
                this.dataAquisicao = dataAquisicao;
        }

        public void setLivro(Livro livro) {
                this.livro = livro;
        }


        public Long getId() {
                return id;
        }

        public Livro getLivro() {
                return livro;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String toString() {
                return (this.id + " - " + this.livro);
        }





}
