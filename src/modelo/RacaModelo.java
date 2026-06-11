package src.modelo;

public class RacaModelo {
    private int id_raca;
    private String nome_raca;
    private String tipo_animal;
    private boolean status_raca;
    
    public RacaModelo() {
    }

    public RacaModelo(int id_raca, String nome_raca, String tipo_animal, boolean status_raca) {
        this.id_raca = id_raca;
        this.nome_raca = nome_raca;
        this.tipo_animal = tipo_animal;
        this.status_raca = status_raca;
    }

    public int getId_raca() {
        return id_raca;
    }

    public String getNome_raca() {
        return nome_raca;
    }

    public String getTipo_animal() {
        return tipo_animal;
    }

    public boolean isStatus_raca() {
        return status_raca;
    }


    public void setId_raca(int id_raca) {
        this.id_raca = id_raca;
    }

    public void setNome_raca(String nome_raca) {
        this.nome_raca = nome_raca;
    }

    public void setTipo_animal(String tipo_animal) {
        this.tipo_animal = tipo_animal;
    }

    public void setStatus_raca(boolean status_raca) {
        this.status_raca = status_raca;
    }

    public String toString(){
        return getNome_raca();
    }
}
