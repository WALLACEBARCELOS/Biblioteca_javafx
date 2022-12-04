package br.edu.femass.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import br.edu.femass.dao.DaoExemplar;
import br.edu.femass.dao.DaoLivro;
import br.edu.femass.model.Autor;
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

public class ExemplarController implements Initializable{

    @FXML
    private ComboBox<Livro> cboLivro;
    @FXML
    private Button BntAlterar;
    @FXML
    private Button BntIncluir;
    @FXML
    private Button BntExcluir;
    @FXML
    private Button BntGravar;
    @FXML
    private TableView<Exemplar> tabela = new TableView<Exemplar>();
    @FXML
    private TableColumn<Exemplar, Long> colid = new TableColumn<>();
    @FXML
    private TableColumn<Exemplar,String > colData = new TableColumn<>();
    @FXML
    private TableColumn<Exemplar,String > colTitulo = new TableColumn<>();

    private DaoExemplar dao = new DaoExemplar();
    private DaoLivro daoLivro = new DaoLivro();
    private Livro livro;
    private Exemplar exemplar;
    private Boolean incluindo;

    @FXML
    private void Gravar_Click(ActionEvent event) {
        //Livro livro = cboLivro.getSelectionModel().getSelectedItem();
        Exemplar exemplar = new Exemplar(cboLivro.getSelectionModel().getSelectedItem());
        //exemplar.setLivro(cboLivro.getSelectionModel().getSelectedItem());

        if (incluindo) {
            dao.inserir(exemplar);
        } else {
            dao.alterar(exemplar);
        }
        preencherTabela();
        preencherCombo();
        editar(false);
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

    @FXML
    private void Excluir_Click(ActionEvent event) {
        dao.apagar(exemplar);
        preencherTabela();
    }

    private void editar(boolean habilitar) {
        cboLivro.setDisable(!habilitar);
        BntGravar.setDisable(!habilitar);
        BntAlterar.setDisable(habilitar);
        BntExcluir.setDisable(habilitar);
        BntIncluir.setDisable(habilitar);

    }
    private void exibirDados(){
        this.exemplar = tabela.getSelectionModel().getSelectedItem();

        if (exemplar==null) return;

    }

    private void preencherTabela(){
        List<Exemplar> exemplar = dao.buscarTodosPorId();

        ObservableList<Exemplar> data = FXCollections.observableArrayList(exemplar);
        tabela.setItems(data);
    }

    private void preencherCombo(){
        List<Livro> livros = daoLivro.buscarTodosPorId();

        ObservableList<Livro> data = FXCollections.observableArrayList(livros);
        cboLivro.setItems(data);

    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {

        colid.setCellValueFactory(new PropertyValueFactory<Exemplar, Long>("id"));
        colTitulo.setCellValueFactory(new PropertyValueFactory<Exemplar, String>("livro"));
        colData.setCellValueFactory(new PropertyValueFactory<Exemplar, String>("dataAquisicao"));

        preencherTabela();
    }

}
