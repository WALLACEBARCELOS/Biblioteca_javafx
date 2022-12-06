package br.edu.femass.gui;

import br.edu.femass.dao.*;
import br.edu.femass.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EmprestimoController implements Initializable{

    @FXML
    private ComboBox<Exemplar> cboExemplar;
    @FXML
    private ComboBox<Professor>  cboProfessor;
    @FXML
    private ComboBox<Aluno>  cboAluno;
    @FXML
    private Button BntAlterar;
    @FXML
    private Button BntIncluir;
    @FXML
    private Button BntGravar_Professor;
    @FXML
    private Button BntGravar_Aluno;
    @FXML
    private Button BntProfessor;
    @FXML
    private Button BntAluno;

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
    private DaoAluno daoAluno = new DaoAluno();
    private DaoProfessor daoProfessor = new DaoProfessor();
    private DaoLeitor daoLeitor = new DaoLeitor();
    private Leitor leitor;
    private Emprestimo emprestimo;
    private Aluno aluno;

    private Professor professor;
    private Exemplar exemplar;
    private Boolean incluindo;

    @FXML
    private void Gravar_Click_Professor(ActionEvent event) {

        emprestimo = new Emprestimo();
        Exemplar exemplar = cboExemplar.getSelectionModel().getSelectedItem();
        Professor professor = (Professor) cboProfessor.getSelectionModel().getSelectedItem();
        emprestimo = new Emprestimo(exemplar, professor);

        if (incluindo) {
            dao.inserir(emprestimo);
        } else {
            dao.alterar(emprestimo);
        }
        cboProfessor.setDisable(true);
        preencherTabela();
        preencherCombo();

        editar(false);
        BntGravar_Professor.setDisable(true);

    }

    @FXML
    private void Gravar_Click_Aluno(ActionEvent event) {

        emprestimo = new Emprestimo();
        Exemplar exemplar = cboExemplar.getSelectionModel().getSelectedItem();
        Aluno aluno = (Aluno) cboAluno.getSelectionModel().getSelectedItem();
        emprestimo = new Emprestimo(exemplar, aluno);
        if (incluindo) {
            dao.inserir(emprestimo);
        } else {
            dao.alterar(emprestimo);
        }
        preencherTabela();
        preencherCombo();

        cboAluno.setDisable(true);
        editar(false);

        BntGravar_Aluno.setDisable(true);

    }

    @FXML
    private void Clicar_professor(ActionEvent event) {

        cboProfessor.setDisable(false);
        BntAluno.setDisable(true);
        cboAluno.setDisable(true);
        BntGravar_Professor.setDisable(false);
    }
    @FXML
    private void Clicar_Aluno(ActionEvent event) {

        cboAluno.setDisable(false);
        BntProfessor.setDisable(true);
        cboProfessor.setDisable(true);
        BntGravar_Aluno.setDisable(false);

    }

    @FXML
    private void Incluir_Click(ActionEvent event) {
        editar(true);
        preencherCombo();
        incluindo = true;

        // livro = new Livro();
        // exemplar = new Exemplar();
        //cboLivro.requestFocus();
        preencherTabela();
    }

    @FXML
    private void Alterar_Click(ActionEvent event) {
        editar(true);
        // preencherTabela();
        preencherCombo();
        incluindo = false;

    }


    private void editar(boolean habilitar) {
        //cboProfessor.setDisable(!habilitar);
        //cboAluno.setDisable(!habilitar);
        BntAluno.setDisable(!habilitar);
        BntProfessor.setDisable(!habilitar);
        cboExemplar.setDisable(!habilitar);
        BntAlterar.setDisable(habilitar);
        BntIncluir.setDisable(habilitar);




    }
    private void exibirDados(){
        this.emprestimo = tabela.getSelectionModel().getSelectedItem();

        if (exemplar==null) return;

    }

    private void preencherTabela(){
        List<Emprestimo> emprestimo = dao.buscarTodosPorId();

        ObservableList<Emprestimo> data = FXCollections.observableArrayList(emprestimo);
        tabela.setItems(data);
    }

    private void preencherCombo(){
        List<Exemplar> exemplar = daoExemplar.buscarTodosPorId();
        ObservableList<Exemplar> data = FXCollections.observableArrayList(exemplar);
        cboExemplar.setItems(data);

        List<Aluno> aluno = daoAluno.buscarTodosPorId();
        ObservableList<Aluno> data1 = FXCollections.observableArrayList(aluno);
        cboAluno.setItems(data1);

        List<Professor> professor = daoProfessor.buscarTodosPorId();
        ObservableList<Professor> data3 = FXCollections.observableArrayList(professor);
        cboProfessor.setItems(data3);


    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {


        colEmprestimo.setCellValueFactory(new PropertyValueFactory<Emprestimo, String>("dataEmprestimo"));
        colExemplar.setCellValueFactory(new PropertyValueFactory<Emprestimo, String>("exemplar"));
        colPrevisao.setCellValueFactory(new PropertyValueFactory<Emprestimo, String>("dataPrevistaDevolucao"));
        colid.setCellValueFactory(new PropertyValueFactory<Emprestimo, Long>("id"));

        preencherTabela();
    }


}
