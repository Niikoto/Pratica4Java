package src.modelo;

public class AnimalModelo {
    private int id_animal;
    private String nome_animal;
    private String data_nascimento;
    private String sexo;
    private String cor;
    private String observacoes;
    private int cod_cliente;
    private int cod_raca;
    private boolean status_animal;
    private ClienteModelo cliente;
    private RacaModelo raca;

    public AnimalModelo() {
    }

    public AnimalModelo(int id_animal, String nome_animal, String data_nascimento, String sexo, String cor, String observacoes, int cod_cliente, int cod_raca, boolean status_animal) {
        this.id_animal = id_animal;
        this.nome_animal = nome_animal;
        this.data_nascimento = data_nascimento;
        this.sexo = sexo;
        this.cor = cor;
        this.observacoes = observacoes;
        this.cod_cliente = cod_cliente;
        this.cod_raca = cod_raca;
        this.status_animal = status_animal;
    }
    public int getId_animal() {
        return id_animal;
    }
    public String getNome_animal() {
        return nome_animal;
    }
    public String getData_nascimento() {
        return data_nascimento;
    }
    public String getSexo() {
        return sexo;
    }
    public String getCor() {
        return cor;
    }
    public String getObservacoes() {
        return observacoes;
    }
    public int getCod_cliente() {
        return cod_cliente;
    }
    public int getCod_raca() {
        return cod_raca;
    }
    public boolean isStatus_animal() {
        return status_animal;
    }
    public ClienteModelo getCliente() {
        return cliente;
    }
    public RacaModelo getRaca() {
        return raca;
    }

    public void setId_animal(int id_animal) {
        this.id_animal = id_animal;
    }
    public void setNome_animal(String nome_animal) {
        this.nome_animal = nome_animal;
    }
    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public void setCor(String cor) {
        this.cor = cor;
    }
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
    public void setCod_cliente(int cod_cliente) {
        this.cod_cliente = cod_cliente;
    }
    public void setCod_raca(int cod_raca) {
        this.cod_raca = cod_raca;
    }
    public void setStatus_animal(boolean status_animal) {
        this.status_animal = status_animal;
    }
    public void setCliente(ClienteModelo cliente) {
        this.cliente = cliente;
    }
    public void setRaca(RacaModelo raca) {
        this.raca = raca;
    }
}