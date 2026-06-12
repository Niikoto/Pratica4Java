package src.gui;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import src.dao.AnimalDao;
import src.dao.ClienteDao;
import src.dao.RacaDao;
import src.modelo.AnimalModelo;
import src.modelo.ClienteModelo;
import src.modelo.RacaModelo;

public class TelaAlterarAnimalController {
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField colNome, colCor;

    @FXML
    private DatePicker dataNascimento;

    @FXML
    private TextArea txtObs;
    
    @FXML
    private ComboBox<String> comboSexo;
    @FXML
    private ComboBox<ClienteModelo> comboCliente;
    @FXML
    private ComboBox<RacaModelo> comboRaca;

    @FXML
    private CheckBox checkAtivo;

    private int id;

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
        Stage curStage = (Stage) anchorPane.getScene().getWindow();
        curStage.close();
    }

    @FXML
    public void enviarCadastro(ActionEvent event)throws IOException{
        AnimalDao dao = new AnimalDao();

        String sexo = comboSexo.getValue().toString();
        if (sexo.equals("Femea")) {
            sexo = "f";
        }
        else{
            sexo = "m";
        }

        dao.alterarAnimal(colNome.getText(), dataNascimento.getValue(), sexo, colCor.getText(), txtObs.getText(), comboCliente.getValue().getId(), comboRaca.getValue().getId_raca(), checkAtivo.isSelected(), id);

        fecharTela(event);
    }

    public void enviarDadosCadastroAnimal(AnimalModelo a){
        id = a.getId_animal();

        colNome.setText(a.getNome_animal());
        txtObs.setText(a.getObservacoes());
        colCor.setText(a.getCor());
    }
}

