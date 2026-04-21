package src.gui;

import java.io.IOException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import src.dao.ClienteDao;
import src.modelo.ClienteModelo;

public class TelaAlterarContoller {
    @FXML
    Button buttonVoltar;

    @FXML
    TextField txtNome;
    @FXML
    TextField txtCpf;
    @FXML
    TextField txtCep;
    @FXML
    DatePicker dateData;
    @FXML
    TextField txtTelefone;
    @FXML
    TextField txtEstado;
    @FXML
    TextField txtCidade;
    @FXML
    TextField txtBairro;
    @FXML
    TextField txtEndereco;

    private ClienteModelo clienteEnvio;

    public void enviarDadosCadastro(ClienteModelo cliente) {
        this.clienteEnvio = cliente;

        txtNome.setText(cliente.getNome());
        txtCpf.setText(cliente.getCpf());
        txtCep.setText(cliente.getCep());
        dateData.setValue(LocalDate.parse(cliente.getDataNascimento()));
        txtTelefone.setText(cliente.getTelefone());
        txtEstado.setText(cliente.getEstado());
        txtCidade.setText(cliente.getCidade());
        txtBairro.setText(cliente.getBairro());
        txtEndereco.setText(cliente.getEndereco());
    }

    @FXML
    public void voltar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/src/view/telaCadastro.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void salvarAlteracoes(ActionEvent event) throws IOException{
        ClienteDao dao = new ClienteDao();

        dao.alterarCliente(txtNome.getText(), txtCep.getText(), dateData.getValue(),txtTelefone.getText(), txtEndereco.getText(), txtBairro.getText(), txtCidade.getText(), txtEstado.getText(), txtCep.getText(), clienteEnvio.getId());
    }
}
