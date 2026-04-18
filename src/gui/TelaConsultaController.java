package src.gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TelaConsultaController {
    @FXML private Button buttonVoltar;
    @FXML private Button buttonAlterar;

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
}
