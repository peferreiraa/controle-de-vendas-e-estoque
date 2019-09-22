package br.com.project.dao;

import br.com.project.model.Client;
import java.sql.Connection;
import br.com.project.jdbc.ConnectionFactory;
import br.com.project.model.WebServiceCep;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ClientDao {

    private final Connection conn;

    public ClientDao() {

        this.conn = new ConnectionFactory().getConnection();
    }

    public void registerClient(Client obj) {

        try {
            String sql = "insert into tb_clientes(nome, rg, cpf, email, telefone"
                    + ", celular, cep, endereco, numero, complemento, bairro, "
                    + "cidade, estado) "
                    + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, obj.getNome());
            st.setString(2, obj.getRg());
            st.setString(3, obj.getCpf());
            st.setString(4, obj.getEmail());
            st.setString(5, obj.getTelefone());
            st.setString(6, obj.getCelular());
            st.setString(7, obj.getCep());
            st.setString(8, obj.getEndereco());
            st.setInt(9, obj.getNumero());
            st.setString(10, obj.getComplemento());
            st.setString(11, obj.getBairro());
            st.setString(12, obj.getCidade());
            st.setString(13, obj.getUf());

            st.execute();
            st.close();

            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Erro: " + error);
        }
    }

    public void alterClient(Client obj) {
        try {
            String sql = "update tb_clientes set nome = ?, rg = ?, cpf = ?, "
                    + "email = ? , telefone = ?"
                    + ", celular = ?, cep = ? , endereco = ?, numero = ? , "
                    + "complemento = ?, bairro = ?, cidade = ?, estado = ?"
                    + "where id = ?";

            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, obj.getNome());
            st.setString(2, obj.getRg());
            st.setString(3, obj.getCpf());
            st.setString(4, obj.getEmail());
            st.setString(5, obj.getTelefone());
            st.setString(6, obj.getCelular());
            st.setString(7, obj.getCep());
            st.setString(8, obj.getEndereco());
            st.setInt(9, obj.getNumero());
            st.setString(10, obj.getComplemento());
            st.setString(11, obj.getBairro());
            st.setString(12, obj.getCidade());
            st.setString(13, obj.getUf());
            st.setInt(14, obj.getId());

            st.execute();
            st.close();

            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Erro: " + error);
        }
    }

    public void deleteClient(Client obj) {
        try {
            String sql = "delete from tb_clientes where id = ?";

            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, obj.getId());

            st.execute();
            st.close();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Erro: " + error);
        }
    }

    public List<Client> toListClient() {

        List<Client> list = new ArrayList<>();
        String sql = "select * from tb_clientes";

        try {

            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Client obj = new Client();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));

                list.add(obj);
            }

            return list;
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Erro: " + error);
            return null;
        }

    }

    public List<Client> searchByName(String nome) {

        List<Client> list = new ArrayList<>();
        String sql = "select * from tb_clientes where nome like ?";

        try {

            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, nome);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Client obj = new Client();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));

                list.add(obj);
            }

            return list;
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Erro: " + error);
            return null;
        }

    }

    //Para o botão pesquisar2 (sem lista)
    public Client searchByNome2(String nome) {

        String sql = "select * from tb_clientes where nome = ? ";

        try {
            Client obj = new Client();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, nome);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));
            }

            return obj;
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado");
            return null;
        }
        

    }
    
    public Client searchByCPF(String cpf) {

        String sql = "select * from tb_clientes where cpf = ?";

        try {
            Client obj = new Client();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, cpf);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));
            }

            return obj;
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado");
            return null;
        }

    }
    
    
    

    //Buscar cep
    public Client buscaCep(String cep) {

        WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);

        Client obj = new Client();

        if (webServiceCep.wasSuccessful()) {
            obj.setEndereco(webServiceCep.getLogradouroFull());
            obj.setCidade(webServiceCep.getCidade());
            obj.setBairro(webServiceCep.getBairro());
            obj.setUf(webServiceCep.getUf());
            return obj;
        } else {
            JOptionPane.showMessageDialog(null, "Erro numero: " + webServiceCep.getResulCode());
            JOptionPane.showMessageDialog(null, "Descrição do erro: " + webServiceCep.getResultText());
            return null;
        }

    }
    
    

}
