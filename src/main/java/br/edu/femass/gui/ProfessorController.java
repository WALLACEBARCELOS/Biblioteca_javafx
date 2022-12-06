package br.edu.femass.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import br.edu.femass.dao.DaoProfessor;
import br.edu.femass.model.Leitor;
import br.edu.femass.model.Professor;
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


public class ProfessorController implements Initializable {

    @FXML
    private TextField TxtNome;
    @FXML
    private TextField TxtEndereco;
    @FXML
    private TextField TxtTelefone;
    @FXML
    private TextField TxtDisciplina;
    @FXML
    private Button BntAlterar;
    @FXML
    private Button BntIncluir;
    @FXML
    private Button BntExcluir;
    @FXML
    private Button BtnGravar;
    @FXML
    private TableView<Professor> tabela = new TableView<Professor>();
    @FXML
    private TableColumn<Professor,String > colNome = new TableColumn<>();
    @FXML
    private TableColumn<Professor,String > colEndereco = new TableColumn<>();
    @FXML
    private TableColumn<Professor,String > colTelefone = new TableColumn<>();
    @FXML
    private TableColumn<Leitor,Integer > colPrazo = new TableColumn<>();
    @FXML
    private TableColumn<Professor,String > colDisciplina = new TableColumn<>();
    @FXML
    private TableColumn<Professor,Long > colId = new TableColumn<>();



    private DaoProfessor dao = new DaoProfessor();
    private Professor professor;
    private Boolean incluindo;


    @FXML
    private void Gravar_Click(ActionEvent event) {
        professor.setNome(TxtNome.getText());
        professor.setDisciplina(TxtDisciplina.getText());
        professor.setEndereco(TxtEndereco.getText());
        professor.setTelefone(TxtTelefone.getText());

        if (incluindo) {
            dao.inserir(professor);
        } else {
            dao.alterar(professor);
        }
        preencherTabela();
        editar(false);
    }

    @FXML
    private void Incluir_Click(ActionEvent event) {
        editar(true);

        incluindo = true;

        professor = new Professor();
        TxtNome.setText("");
        TxtDisciplina.setText("");
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
        dao.apagar(professor);
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
        TxtDisciplina.setDisable(!habilitar);
        BtnGravar.setDisable(!habilitar);
        BntAlterar.setDisable(habilitar);
        BntExcluir.setDisable(habilitar);
        BntIncluir.setDisable(habilitar);

    }


    //Selecionar o elemento na lista e depois exibir nas caixas
    private void exibirDados(){
        this.professor = tabela.getSelectionModel().getSelectedItem();

        if (professor == null ) return;

        TxtDisciplina.setText(professor.getDisciplina());
        TxtNome.setText(professor.getNome());
        TxtTelefone.setText(professor.getTelefone());
        TxtEndereco.setText(professor.getEndereco());
    }


    private void preencherTabela(){
        List<Professor> professores = dao.buscarTodos();

        ObservableList<Professor> data = FXCollections.observableArrayList(professores);
        tabela.setItems(data);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colNome.setCellValueFactory(new PropertyValueFactory<Professor, String>("nome"));
        colEndereco.setCellValueFactory(new PropertyValueFactory<Professor, String>("endereco"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<Professor, String>("telefone"));
        colId.setCellValueFactory(new PropertyValueFactory<Professor, Long>("id"));
        colDisciplina.setCellValueFactory(new PropertyValueFactory<Professor, String>("disciplina"));
        colPrazo.setCellValueFactory(new PropertyValueFactory<Leitor, Integer>("prazoMaximoDevolucao"));
        preencherTabela();
    }
}



