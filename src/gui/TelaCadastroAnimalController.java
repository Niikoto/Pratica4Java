package src.gui;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import src.dao.AnimalDao;
import src.dao.ClienteDao;
import src.dao.RacaDao;
import src.modelo.AnimalModelo;
import src.modelo.ClienteModelo;
import src.modelo.RacaModelo;

public class TelaCadastroAnimalController {
    @FXML
    private ComboBox<ClienteModelo> comboCliente;
    @FXML
    private ComboBox<RacaModelo> comboRaca;
    @FXML
    private ComboBox<String> comboSexo;
    
    @FXML
    private CheckBox checkAtivo;

    @FXML
    private TextField colNome;
    @FXML
    private TextField txtCor;

    @FXML
    private DatePicker dataNascimento;

    @FXML
    private TextArea txtObs;

    @FXML
    public void initialize(){
        ClienteDao dao = new ClienteDao();
        RacaDao daoRaca = new RacaDao();

        comboCliente.setItems(FXCollections.observableArrayList(dao.listarClientes()));

        comboRaca.setItems(FXCollections.observableArrayList(daoRaca.listarRaca()));

        comboSexo.setItems(FXCollections.observableArrayList("Macho","Femea"));
    }

    @FXML
    public void fecharTela(ActionEvent event)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/src/view/telaCadastro.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void enviarCadastro(ActionEvent event){
        AnimalModelo animal = new AnimalModelo();
        AnimalDao daoAnimal = new AnimalDao();

        animal.setNome_animal(colNome.getText());
        animal.setData_nascimento(dataNascimento.getValue().toString());
        animal.setCod_cliente(comboCliente.getValue().getId());
        animal.setCod_raca(comboRaca.getValue().getId_raca());
        animal.setObservacoes(txtObs.getText());
        animal.setStatus_animal(checkAtivo.isSelected());
        animal.setCor(txtCor.getText());
        String sexo = comboSexo.getValue().toString();
        if (sexo.equals("Femea")) {
            sexo = "f";
        }
        else{
            sexo = "m";
        }
        animal.setSexo(sexo);

        daoAnimal.cadastrarAnimal(animal);
    }

    @FXML
    public void consultar(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/src/view/telaConsultaAnimal.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void cadRaca(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/src/view/TelaCadastroRaca.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.show();
    }
}   