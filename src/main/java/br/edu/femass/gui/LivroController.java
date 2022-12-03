package br.edu.femass.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import br.edu.femass.dao.DaoAutor;
import br.edu.femass.dao.DaoLivro;
import br.edu.femass.model.Autor;
import br.edu.femass.model.Livro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ComboBox;

public class LivroController implements Initializable{

    @FXML
    private ComboBox<Autor> cboAutor;
    @FXML
    private TextField txtTitulo;
    @FXML
    private TextField txtAno;
    @FXML
    private Button BntAlterar;
    @FXML
    private Button BntIncluir;
    @FXML
    private Button BntExcluir;
    @FXML
    private Button BntGravar;
    @FXML
    private TableView<Livro> tabela = new TableView<Livro>();
    @FXML
    private TableColumn<Livro,String > colautor = new TableColumn<>();
    @FXML
    private TableColumn<Livro,String > colTitulo = new TableColumn<>();
    @FXML
    private TableColumn<Livro,String > colAno = new TableColumn<>();
    @FXML
    private TableColumn<Livro,Long > colid = new TableColumn<>();

    private DaoLivro dao = new DaoLivro();
    private DaoAutor daoAutor = new DaoAutor();
    private Livro livro;
    private Boolean incluindo;

    @FXML
    private void Gravar_Click(ActionEvent event) {
        Autor autor = cboAutor.getSelectionModel().getSelectedItem();
        livro.setTitulo(txtTitulo.getText());
        livro.setAno(txtAno.getText());
        livro.setAutor(autor);

        if (incluindo) {
            dao.inserir(livro);
        } else {
            dao.alterar(livro);
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

        livro = new Livro();
        txtTitulo.setText("");
        txtAno.setText("");
        txtTitulo.requestFocus();
        preencherTabela();
    }

    @FXML
    private void Alterar_Click(ActionEvent event) {
        editar(true);

        incluindo = false;
        preencherTabela();
        preencherCombo();
    }

    @FXML
    private void Excluir_Click(ActionEvent event) {
        dao.apagar(livro);
        preencherTabela();
    }

    private void editar(boolean habilitar) {
        cboAutor.setDisable(!habilitar);
        txtAno.setDisable(!habilitar);
        txtTitulo.setDisable(!habilitar);
        BntGravar.setDisable(!habilitar);
        BntAlterar.setDisable(habilitar);
        BntExcluir.setDisable(habilitar);
        BntIncluir.setDisable(habilitar);

    }


    private void exibirDados(){
        this.livro = tabela.getSelectionModel().getSelectedItem();

        if (livro == null ) return;

        txtTitulo.setText(livro.getTitulo());
        txtAno.setText(livro.getAno());
        cboAutor.setValue(livro.getAutor());
    }

    private void preencherTabela(){
        List<Livro> livros = dao.buscarTodosPorId();

        ObservableList<Livro> data = FXCollections.observableArrayList(livros);
        tabela.setItems(data);
    }

    private void preencherCombo(){
        List<Autor> autores = daoAutor.buscarTodosPorId();

        ObservableList<Autor> data = FXCollections.observableArrayList(autores);
        cboAutor.setItems(data);

    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {

        colautor.setCellValueFactory(new PropertyValueFactory<Livro, String>("autor"));
        colTitulo.setCellValueFactory(new PropertyValueFactory<Livro, String>("titulo"));
        colAno.setCellValueFactory(new PropertyValueFactory<Livro, String>("ano"));
        colid.setCellValueFactory(new PropertyValueFactory<Livro, Long>("id"));
        preencherTabela();
    }

}
