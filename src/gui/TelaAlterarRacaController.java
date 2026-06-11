package src.gui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import src.dao.RacaDao;
import src.modelo.RacaModelo;

public class TelaAlterarRacaController {
    @FXML
    private TextField colNome;

    @FXML
    private ComboBox<String> comboTipo;

    @FXML
    private CheckBox checkAtivo;

    @FXML
    private AnchorPane anchorPane;

    private int id;

    @FXML
    public void initialize(){
        comboTipo.setItems(FXCollections.observableArrayList("Cachorro", "Gato"));
    }

    @FXML
    public void fecharTela(ActionEvent event)throws IOException{
        Stage curStage = (Stage) anchorPane.getScene().getWindow();
        curStage.close();
    }

    @FXML
    public void enviarCadastro(ActionEvent event) throws IOException{
        RacaDao racaDao = new RacaDao();
        RacaModelo r = new RacaModelo();

        racaDao.alterarRaca(colNome.getText(), comboTipo.getValue().toString(), checkAtivo.isSelected(), id);
        fecharTela(event);
    }

    public void enviarDadosCadastro(RacaModelo r){
        colNome.setText(r.getNome_raca());

        id = r.getId_raca();
    }
}
