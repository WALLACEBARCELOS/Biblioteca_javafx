package br.edu.femass.gui;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.DaoEmprestimo;
import br.edu.femass.dao.DaoExemplar;
import br.edu.femass.dao.DaoLivro;
import br.edu.femass.model.Autor;
import br.edu.femass.model.Emprestimo;
import br.edu.femass.model.Exemplar;
import br.edu.femass.model.Livro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ComboBox;

public class DevolucaoController implements Initializable{

    @FXML
    private TableColumn<Emprestimo,String > colDataDevolucao = new TableColumn<>();
    @FXML
    private TableView<Emprestimo> tabela = new TableView<Emprestimo>();
    @FXML
    private TableColumn<Emprestimo, Long> colid = new TableColumn<>();
    @FXML
    private TableColumn<Emprestimo,String > colExemplar = new TableColumn<>();
    @FXML
    private TableColumn<Emprestimo,String > colEmprestimo = new TableColumn<>();
    @FXML
    private TableColumn<Emprestimo,String > colPrevisao = new TableColumn<>();


    private DaoEmprestimo dao = new DaoEmprestimo();
    private DaoExemplar daoExemplar = new DaoExemplar();
    private Exemplar exemplar;
    private Emprestimo emprestimo;
    private Boolean incluindo;

    @FXML
    private void Gravar_Click(ActionEvent event) {
        preencherTabela();
        //Livro livro = cboLivro.getSelectionModel().getSelectedItem();
        Emprestimo emprestimo = tabela.getSelectionModel().getSelectedItem();
        emprestimo.setDataDevolucao(LocalDate.now());
        //exemplar.setLivro(cboLivro.getSelectionModel().getSelectedItem());



            dao.alterar(emprestimo);

        preencherTabela();

    }

    private void preencherTabela(){
        List<Emprestimo> emprestimo = dao.buscarTodosPorId();

        ObservableList<Emprestimo> data = FXCollections.observableArrayList(emprestimo);
        tabela.setItems(data);
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        colDataDevolucao.setCellValueFactory(new PropertyValueFactory<Emprestimo, String>("dataDevolucao"));
       // colEmprestimo.setCellValueFactory(new PropertyValueFactory<Emprestimo, String>("dataEmprestimo"));
        colExemplar.setCellValueFactory(new PropertyValueFactory<Emprestimo, String>("exemplar"));
        colPrevisao.setCellValueFactory(new PropertyValueFactory<Emprestimo, String>("dataPrevistaDevolucao"));
        colid.setCellValueFactory(new PropertyValueFactory<Emprestimo, Long>("id"));

        preencherTabela();
    }

}
