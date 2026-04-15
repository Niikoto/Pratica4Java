package src.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import src.modelo.ClienteModelo;

import src.factory.ConnectionFactory;

public class ClienteDao {
    Connection connection = ConnectionFactory.getConnection();

    public void cadastrarCliente(ClienteModelo c)throws SQLException{
        String sql = "insert into cliente(nome, cpf, data_nascimento, telefone, endereco, bairro, cidade, estado, cep) values (?,?,STR_TO_DATE(?,'%d/%m/%Y'),?,?,?,?,?,?);";
        try(PreparedStatement executa = connection.prepareStatement(sql)) {
            executa.setString(1, c.getNome());
            executa.setString(2, c.getCpf());
            executa.setString(3, c.getDataNascimento());
            executa.setString(4, c.getTelefone());
            executa.setString(5, c.getEndereco());
            executa.setString(6, c.getBairro());
            executa.setString(7, c.getCidade());
            executa.setString(8, c.getEstado());
            executa.setString(9, c.getCep());
            
            executa.executeUpdate();
            connection.close();
        }
    }
}
