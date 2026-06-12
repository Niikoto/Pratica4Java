package src.gui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import src.dao.RacaDao;
import src.modelo.RacaModelo;

public class TelaCadastroRacaController {
    @FXML
    private TextField colNome;

    @FXML
    private ComboBox<String> comboTipo;

    @FXML
    private CheckBox checkAtivo;

    @FXML
    public void initialize(){
        comboTipo.setItems(FXCollections.observableArrayList("Cachorro", "Gato"));
    }

    @FXML
    public void fecharTela(ActionEvent event)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/src/view/telaCadastroAnimal.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void enviarCadastro(ActionEvent event){
        RacaModelo raca = new RacaModelo();
        RacaDao daoRaca = new RacaDao();

        raca.setNome_raca(colNome.getText());
        raca.setTipo_animal(comboTipo.getValue().toString());
        raca.setStatus_raca(checkAtivo.isSelected());

        try {
            daoRaca.cadastrarRaca(raca);
            alerta("Sucesso", "Cadastro realizado com sucesso");            
        } catch (Exception e) {
            e.printStackTrace();
            alerta("Erro", "Não foi possivel executar o cadastro");
        }
    }

    @FXML
    public void consultar(ActionEvent event)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/src/view/telaConsultaRaca.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.show();
    }

    public void alerta(String titulo, String mensagem) {
        Alert alertar = new Alert(Alert.AlertType.INFORMATION);
        alertar.setTitle(titulo);
        alertar.setContentText(mensagem);
        alertar.showAndWait();
    }
}
