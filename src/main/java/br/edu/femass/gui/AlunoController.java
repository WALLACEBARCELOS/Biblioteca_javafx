package br.edu.femass.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.DaoAluno;
import br.edu.femass.model.Aluno;
import br.edu.femass.model.Leitor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class AlunoController implements Initializable {

    @FXML
    private TextField TxtNome;
    @FXML
    private TextField TxtEndereco;
    @FXML
    private TextField TxtTelefone;
    @FXML
    private TextField TxtMatricula;
    @FXML
    private Button BntAlterar;
    @FXML
    private Button BntIncluir;
    @FXML
    private Button BntExcluir;
    @FXML
    private Button BtnGravar;
    @FXML
    private TableView<Aluno> tabela = new TableView<Aluno>();
    @FXML
    private TableColumn<Aluno,String > colNome = new TableColumn<>();
    @FXML
    private TableColumn<Aluno,String > colEndereco = new TableColumn<>();
    @FXML
    private TableColumn<Aluno,String > colTelefone = new TableColumn<>();
    @FXML
    private TableColumn<Leitor,Integer > colPrazo = new TableColumn<>();
    @FXML
    private TableColumn<Aluno,String > colMatricula = new TableColumn<>();
    @FXML
    private TableColumn<Aluno,Long > colId = new TableColumn<>();



    private DaoAluno dao = new DaoAluno();
    private Aluno aluno;
    private Boolean incluindo;


    @FXML
    private void Gravar_Click(ActionEvent event) {
        aluno.setNome(TxtNome.getText());
        aluno.setMatricula(TxtMatricula.getText());
        aluno.setEndereco(TxtEndereco.getText());
        aluno.setTelefone(TxtTelefone.getText());

        if (incluindo) {
            dao.inserir(aluno);
        } else {
            dao.alterar(aluno);
        }
        preencherTabela();
        editar(false);
    }

    @FXML
    private void Incluir_Click(ActionEvent event) {
        editar(true);

        incluindo = true;

        aluno = new Aluno();
        TxtNome.setText("");
        TxtMatricula.setText("");
        TxtEndereco.setText("");
        TxtTelefone.setText("");
        TxtNome.requestFocus();
    }

    @FXML
    private void Alterar_Click(ActionEvent event) {
        editar(true);

        incluindo = false;
    }

    @FXML
    private void Excluir_Click(ActionEvent event) {
        dao.apagar(aluno);
        preencherTabela();
    }

    @FXML
    private void tabela_KeyPressed(KeyEvent event){
        exibirDados();
    }
    @FXML
    private void tabela_MouseClicked(MouseEvent event){
        exibirDados();
    }

    private void editar(boolean habilitar) {
        tabela.setDisable(habilitar);
        TxtNome.setDisable(!habilitar);
        TxtTelefone.setDisable(!habilitar);
        TxtEndereco.setDisable(!habilitar);
        TxtMatricula.setDisable(!habilitar);
        BtnGravar.setDisable(!habilitar);
        BntAlterar.setDisable(habilitar);
        BntExcluir.setDisable(habilitar);
        BntIncluir.setDisable(habilitar);

    }


    //Selecionar o elemento na lista e depois exibir nas caixas
    private void exibirDados(){
        this.aluno = tabela.getSelectionModel().getSelectedItem();

        if (aluno == null ) return;

        TxtMatricula.setText(aluno.getMatricula());
        TxtNome.setText(aluno.getNome());
        TxtTelefone.setText(aluno.getTelefone());
        TxtEndereco.setText(aluno.getEndereco());
    }


    private void preencherTabela(){
        List<Aluno> alunos = dao.buscarTodos();

        ObservableList<Aluno> data = FXCollections.observableArrayList(alunos);
        tabela.setItems(data);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colNome.setCellValueFactory(new PropertyValueFactory<Aluno, String>("nome"));
        colEndereco.setCellValueFactory(new PropertyValueFactory<Aluno, String>("endereco"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<Aluno, String>("telefone"));
        colId.setCellValueFactory(new PropertyValueFactory<Aluno, Long>("id"));
        colMatricula.setCellValueFactory(new PropertyValueFactory<Aluno, String>("matricula"));
        colPrazo.setCellValueFactory(new PropertyValueFactory<Leitor, Integer>("prazoMaximoDevolucao"));
        preencherTabela();
    }
}



