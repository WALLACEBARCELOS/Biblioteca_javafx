package br.edu.femass.gui;

import java.awt.*;
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

public class AutorController implements Initializable {


    @FXML
    private TextField TxtNome;
    @FXML
    private TextField TxtSobrenome;
    @FXML
    private TextField TxtNascionalidade;
    @FXML
    private ListView<Autor> LstAutor;
    @FXML Button BtnGravar;


    private DaoAutor dao = new DaoAutor();
    private Autor autor;
    private Boolean incluindo;
    @FXML
    private void Gravar_Click(ActionEvent event) {
        autor.setNome(TxtNome.getText());
        autor.setNome(TxtSobrenome.getText());
        autor.setNome(TxtNascionalidade.getText());

        if (incluindo) {
            dao.inserir(autor);
        } else {
            dao.alterar(autor);
        }
        preencherLista();
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
    private void LstAutor_KeyPressed(javafx.scene.input.KeyEvent  event){
        exibirDados();
    }
    @FXML
    private void LstAutor_MouseClicked(javafx.scene.input.MouseEvent event){
        exibirDados();
    }
    //Selecionar o elemento na lista e depois exibir nas caixas
    private void exibirDados(){
         this.autor = LstAutor.getSelectionModel().getSelectedItem();

        if (autor == null ) return;

        TxtNascionalidade.setText(autor.getNacionalidade());
        TxtNome.setText(autor.getNome());
        TxtSobrenome.setText(autor.getSobrenome());
    }

    private void editar(boolean habilitar) {
        LstAutor.setDisable(habilitar);
        TxtNome.setDisable(!habilitar);
        TxtSobrenome.setDisable(!habilitar);
        TxtNascionalidade.setDisable(!habilitar);
        BtnGravar.setEnabled(habilitar);

    }

    private void preencherLista(){
        List<Autor> autores = dao.buscarTodos();

        ObservableList<Autor> data = FXCollections.observableArrayList(autores);
        LstAutor.setItems(data);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherLista();
    }


}
