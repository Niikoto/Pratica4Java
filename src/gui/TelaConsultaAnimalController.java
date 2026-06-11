package src.gui;

import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import src.dao.AnimalDao;
import src.modelo.AnimalModelo;

public class TelaConsultaAnimalController {
    @FXML
    private TextField textNome;
    @FXML
    private TextField textCpf;

    @FXML
    private TableView<AnimalModelo> tableAnimal;
    @FXML
    private TableColumn<AnimalModelo,Integer> colId, colIdade;
    @FXML
    private TableColumn<AnimalModelo,String> colNome, colDataNas, colRaca, colSexo, colStatus;

    @FXML
    public void initialize(){
        
    }

    @FXML
    public void enviarConsulta(ActionEvent event){
        colId.setCellValueFactory(new PropertyValueFactory<>("id_animal"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome_animal"));
        colRaca.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getRaca().getNome_raca()));
        colIdade.setCellValueFactory(new PropertyValueFactory<>("Data_nascimento"));
        colSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        AnimalDao dao = new AnimalDao();
        List<AnimalModelo> lista = dao.listarAnimal();
        tableAnimal.setItems(FXCollections.observableArrayList(lista));
    }

    @FXML
    public void limpar(ActionEvent event){}

    @FXML
    public void voltar(ActionEvent event)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/src/view/telaCadastro.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.show();
    }
}
