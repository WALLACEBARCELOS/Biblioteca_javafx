package br.edu.femass.model;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;


public class Exemplar {

        @Id
        @GeneratedValue
        private Long id;
        private LocalDateTime dataAquisicao;
        private String titulo;
        @ManyToOne
        private Livro livro;

        public Exemplar(){

        }

        public Exemplar(LocalDateTime dataAquisicao, String titulo) {
                this.dataAquisicao = LocalDateTime.now();
                this.titulo = livro.getTitulo();
        }

        public LocalDateTime getDataAquisicao() {
                return dataAquisicao;
        }


        public String getTitulo() {
                return titulo;
        }

        public String toString() {
                return (this.id + " - " + this.titulo);
        }





}
