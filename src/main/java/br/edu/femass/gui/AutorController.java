package br.edu.femass.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import br.edu.femass.dao.DaoAutor;
import br.edu.femass.model.Autor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ComboBox;

public class AutorController implements Initializable {


    @FXML
    private TextField TxtNome;
    @FXML
    private TextField TxtSobrenome;
    @FXML
    private TextField TxtNascionalidade;
    @FXML
    private ListView<Autor> LstAutor;
    @FXML
    private Button BntAlterar;
    @FXML
    private Button BntIncluir;
    @FXML
    private Button BntExcluir;
    @FXML
    private Button BtnGravar;
    @FXML
    private TableView<Autor> tabela = new TableView<Autor>();
    @FXML
    private TableColumn<Autor,String > colNome = new TableColumn<>();
    @FXML
    private TableColumn<Autor,String > colSobrenome = new TableColumn<>();
    @FXML
    private TableColumn<Autor,String > colNacionalidade = new TableColumn<>();
    @FXML
    private TableColumn<Autor,Long > colid = new TableColumn<>();



    private DaoAutor dao = new DaoAutor();
    private Autor autor;
    private Boolean incluindo;


    @FXML
    private void Gravar_Click(ActionEvent event) {
        autor.setNome(TxtNome.getText());
        autor.setSobrenome(TxtSobrenome.getText());
        autor.setNacionalidade(TxtNascionalidade.getText());

        if (incluindo) {
            dao.inserir(autor);
        } else {
            dao.alterar(autor);
        }
        preencherLista();
        preencherTabela();
        editar(false);
    }

    @FXML
    private void Incluir_Click(ActionEvent event) {
        editar(true);

        incluindo = true;

        autor = new Autor();
        TxtNome.setText("");
        TxtSobrenome.setText("");
        TxtNascionalidade.setText("");
        TxtNome.requestFocus();
    }

    @FXML
    private void Alterar_Click(ActionEvent event) {
        editar(true);

        incluindo = false;
    }

    @FXML
    private void Excluir_Click(ActionEvent event) {
        dao.apagar(autor);
        preencherLista();
    }

    @FXML
    private void LstAutor_KeyPressed(KeyEvent event){
        exibirDados();
    }
    @FXML
    private void LstAutor_MouseClicked(MouseEvent event){
        exibirDados();
    }

    private void editar(boolean habilitar) {
        LstAutor.setDisable(habilitar);
        TxtNome.setDisable(!habilitar);
        TxtSobrenome.setDisable(!habilitar);
        TxtNascionalidade.setDisable(!habilitar);
        BtnGravar.setDisable(!habilitar);
        BntAlterar.setDisable(habilitar);
        BntExcluir.setDisable(habilitar);
        BntIncluir.setDisable(habilitar);

    }


    //Selecionar o elemento na lista e depois exibir nas caixas
    private void exibirDados(){
         this.autor = LstAutor.getSelectionModel().getSelectedItem();

        if (autor == null ) return;

        TxtNascionalidade.setText(autor.getNacionalidade());
        TxtNome.setText(autor.getNome());
        TxtSobrenome.setText(autor.getSobrenome());
    }


    private void preencherTabela(){
        List<Autor> autores = dao.buscarTodosPorId();

        ObservableList<Autor> data = FXCollections.observableArrayList(autores);
        tabela.setItems(data);
    }



    private void preencherLista(){
        List<Autor> autores = dao.buscarTodos();

        ObservableList<Autor> data = FXCollections.observableArrayList(autores);
        LstAutor.setItems(data);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colNome.setCellValueFactory(new PropertyValueFactory<Autor, String>("nome"));
        colSobrenome.setCellValueFactory(new PropertyValueFactory<Autor, String>("sobrenome"));
        colNacionalidade.setCellValueFactory(new PropertyValueFactory<Autor, String>("nacionalidade"));
        colid.setCellValueFactory(new PropertyValueFactory<Autor, Long>("id"));
        preencherLista();
        preencherTabela();
    }
}
