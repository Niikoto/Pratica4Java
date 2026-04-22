package src.gui;

import java.io.IOException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import src.dao.ClienteDao;
import src.modelo.ClienteModelo;

public class TelaAlterarContoller {
    @FXML
    private Button buttonVoltar;
    @FXML
    private Button buttonCacelar;

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtCpf;
    @FXML
    private TextField txtCep;
    @FXML
    private DatePicker dateData;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextField txtEstado;
    @FXML
    private TextField txtCidade;
    @FXML
    private TextField txtBairro;
    @FXML
    private TextField txtEndereco;

    @FXML
    private AnchorPane anchorPane;//AchorPane para saber se a pagina está aberta

    private ClienteModelo clienteEnvio;// guarda dados de um cliente para alterar

    public void enviarDadosCadastro(ClienteModelo cliente) {// metodo que salva os dados do usuario em uma variavel
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
    public void voltar(ActionEvent event) throws IOException {//botao para voltar
        Stage curStage = (Stage) anchorPane.getScene().getWindow();
        curStage.close();
    }

    @FXML
    public void salvarAlteracoes(ActionEvent event) throws IOException {// funcao dao para aleterar
        ClienteDao dao = new ClienteDao();

        dao.alterarCliente(txtNome.getText(), txtCep.getText(), dateData.getValue(), txtTelefone.getText(),
                txtEndereco.getText(), txtBairro.getText(), txtCidade.getText(), txtEstado.getText(), txtCep.getText(),
                clienteEnvio.getId());
        alerta("Sucesso", "Aleração feita com sucesso");
        fechar();
    }

    public void fechar(){
        Stage curStage = (Stage) anchorPane.getScene().getWindow();
        curStage.close();
    }

    @FXML
    public void cancelar(ActionEvent event)throws IOException{
        fechar();
    }

    public void alerta(String titulo, String mensagem){
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
