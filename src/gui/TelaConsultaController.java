package src.gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import src.dao.ClienteDao;
import src.modelo.ClienteModelo;

public class TelaConsultaController {
    @FXML private Button buttonVoltar;

    @FXML private TextField textNome;
    @FXML private TextField textCpf;

    @FXML private TableView tableCliente;
    
    @FXML private TableColumn colNome;
    @FXML private TableColumn colCpf;
    @FXML private TableColumn colData;
    @FXML private TableColumn colTelefone;
    @FXML private TableColumn colEndereco;
    @FXML private TableColumn colBairro;
    @FXML private TableColumn colCidade;
    @FXML private TableColumn colEstado;
    @FXML private TableColumn colCep;

    @FXML
    public void voltar(ActionEvent event)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/src/view/telaCadastro.fxml"));
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

    @FXML
    public void enviarConsulta(ActionEvent event) throws IOException{
        ClienteModelo consulte = new ClienteModelo();
        consulte.setNome(textNome.getText());
        consulte.setCpf(textCpf.getText());

        ClienteDao dao = new ClienteDao();
        dao.consultarCliente(consulte);


    }
}
