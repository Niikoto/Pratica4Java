package src.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<ClienteModelo> consultarCliente(ClienteModelo c){
        List<ClienteModelo> clientes = new ArrayList<>();

        Connection connection = ConnectionFactory.getConnection();

        ResultSet resultado = null;

        String sql = "select * from cliente where cpf Like ? and nome Like ?;";

        try (PreparedStatement ordem = connection.prepareStatement(sql)){
            ordem.setString(1,"%" + c.getCpf() + "%");
            ordem.setString(2, "%" + c.getNome() + "%");

            resultado = ordem.executeQuery(sql);

            if(!resultado.next()){
                return clientes;
            }else{
                do{
                    ClienteModelo cliente = new ClienteModelo();
                    cliente.setId(resultado.getInt(1));
                    cliente.setNome(resultado.getString(2));
                    cliente.setCpf(resultado.getString(3));
                    cliente.setDataNascimento(resultado.getString(3));
                    cliente.setTelefone(resultado.getString(4));
                    cliente.setEndereco(resultado.getString(5));
                    cliente.setBairro(resultado.getString(6));
                    cliente.setCidade(resultado.getString(7));
                    cliente.setEstado(resultado.getString(8));
                    cliente.setCep(resultado.getString(9));

                    clientes.add(cliente);
                }while (resultado.next());
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return clientes;

    }
}
