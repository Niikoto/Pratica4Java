package src.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaCadastroController {
    //botões do fxml
    @FXML private Button buttonFechar;
    @FXML private Button buttonCadastrar;
    @FXML private Button buttonConsultar;
    @FXML private Button buttonExcluir;

    //text field do fxml
    @FXML private TextField nome;
    @FXML private TextField cpf;
    @FXML private DatePicker dataNascimento;
    @FXML private TextField telefone;
    @FXML private TextField cep;
    @FXML private TextField estado;
    @FXML private TextField cidade;
    @FXML private TextField bairro;
    @FXML private TextField endereco;

    //metodos fxml
    @FXML
    public void fecharTela(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}