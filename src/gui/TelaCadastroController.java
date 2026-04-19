package src.gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import src.dao.ClienteDao;
import src.modelo.ClienteModelo;

public class TelaCadastroController {
    // botões do fxml
    @FXML
    private Button buttonFechar;
    @FXML
    private Button buttonCadastrar;
    @FXML
    private Button buttonConsultar;
    @FXML
    private Button buttonExcluir;
    @FXML
    private Button buttonAlterar;

    // text field do fxml
    @FXML
    private TextField nome;
    @FXML
    private TextField cpf;
    @FXML
    private DatePicker dataNascimento;
    @FXML
    private TextField telefone;
    @FXML
    private TextField cep;
    @FXML
    private TextField estado;
    @FXML
    private TextField cidade;
    @FXML
    private TextField bairro;
    @FXML
    private TextField endereco;

    // metodos fxml
    @FXML
    public void fecharTela(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void enviarCadastro(ActionEvent event) {
        ClienteModelo p = new ClienteModelo();

        p.setNome(nome.getText());
        p.setCpf(cpf.getText());
        p.setDataNascimento(dataNascimento.getEditor().getText());
        p.setTelefone(telefone.getText());
        p.setEndereco(endereco.getText());
        p.setBairro(bairro.getText());
        p.setCidade(cidade.getText());
        p.setEstado(estado.getText());
        p.setCep(cep.getText());

        try {
            ClienteDao dao = new ClienteDao();
            dao.cadastrarCliente(p);
            alerta("Cliente Cadastrado", "O cliente foi cadastrado com sucesso");
            nome.clear();
            cpf.clear();
            telefone.clear();
            cep.clear();
            estado.clear();
            cidade.clear();
            bairro.clear();
            endereco.clear();
            dataNascimento.setValue(null);

        } catch (Exception e) {
            System.out.println(e);
            alerta("Erro", "Não foi possivel cadastrar o cliente");
        }

    }

    public void alerta(String titulo, String mensagem) {
        Alert alertar = new Alert(Alert.AlertType.INFORMATION);
        alertar.setTitle(titulo);
        alertar.setContentText(mensagem);
        alertar.showAndWait();
    }

    @FXML
    public void consultar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/src/view/telaConsulta.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void alterar(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/src/view/telaAlterar.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.show();
    }
}