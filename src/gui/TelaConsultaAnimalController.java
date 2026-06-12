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
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
    private TableColumn<AnimalModelo,Void> colAlterar, colExcluir;

    @FXML
    public void initialize(){
        
    }

    @FXML
    public void enviarConsulta(ActionEvent event){
        colId.setCellValueFactory(new PropertyValueFactory<>("id_animal"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome_animal"));
        colRaca.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getRaca().getNome_raca()));
        colIdade.setCellValueFactory(new PropertyValueFactory<>("idade"));
        colSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status_animal"));

        colAlterar.setCellFactory(param -> new TableCell<>() {//função anonima que pega a celula da tabela e cria o botão
            private final Button btnAlterar = new Button("Alterar");
            {
                btnAlterar.setOnAction(event -> {//Evento acionado quando o botão for criado
                    AnimalModelo animal = getTableView().getItems().get(getIndex());

                    telaConsultaAnimal(animal);//Metodo sem o objeto que leva a tela de alteração
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {//Caso a celula esteja varia o botão saira, caso tenha algo dentro o botão é gerado
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btnAlterar);
                }
            }
        });
        // seta onde o botão vai ficar
        colExcluir.setCellFactory(param -> new TableCell<>() {
            private final Button btnExcluir = new Button("Excluir");// Cria um botão na linha
            {
                btnExcluir.setOnAction(event -> {
                    AnimalModelo animal = getTableView().getItems().get(getIndex());// verifica onde cada botão deve

                    AnimalDao dao = new AnimalDao();// cria um objeto para usar o metodo de exclusão
                    dao.excluirAnimal(animal.getId_animal());

                    getTableView().getItems().remove(animal);// verifica onde está a linha e exclui a linha toda
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {// isso daqui vai setar onde o botão deve ficar na
                                                                 // tabela corretamente
                super.updateItem(item, empty);

                if (empty) {// verifica se tem ou não informação na linha para adcionar o botão visualmente
                    setGraphic(null);
                } else {
                    setGraphic(btnExcluir);
                }
            }
        });

        AnimalDao dao = new AnimalDao();
        List<AnimalModelo> lista = dao.listarAnimal(textNome.getText(), textCpf.getText());
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

    public void telaConsultaAnimal(AnimalModelo a){//Metodo que abre a tela de alterar
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/view/telaAlterarAnimal.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
    
            TelaAlterarAnimalController contoller = loader.getController();//Pega o controler da tela de alteração
    
            contoller.enviarDadosCadastroAnimal(a);//Chama o metodo de enviar dados da tela de consulta
            
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
