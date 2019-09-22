/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.project.dao;

import br.com.project.jdbc.ConnectionFactory;
import br.com.project.model.Provider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author whoami
 */
public class ProviderDao {
    
    private final Connection conn;

    public ProviderDao() {

        this.conn = new ConnectionFactory().getConnection();
    }
    
    public void registerProvider(Provider obj) {

        try {
            String sql = "insert into tb_fornecedores(nome, cnpj, email, telefone"
                    + ", celular, cep, endereco, numero, complemento, bairro, "
                    + "cidade, estado) "
                    + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, obj.getNome());
            st.setString(2, obj.getCnpj());
            st.setString(3, obj.getEmail());
            st.setString(4, obj.getTelefone());
            st.setString(5, obj.getCelular());
            st.setString(6, obj.getCep());
            st.setString(7, obj.getEndereco());
            st.setInt(8, obj.getNumero());
            st.setString(9, obj.getComplemento());
            st.setString(10, obj.getBairro());
            st.setString(11, obj.getCidade());
            st.setString(12, obj.getUf());

            st.execute();
            st.close();

            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Erro: " + error);
        }
    }
    
    public void deleteProvider(Provider obj) {
        try {
            String sql = "delete from tb_fornecedores where id = ?";

            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, obj.getId());

            st.execute();
            st.close();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Erro: " + error);
        }
    }
    
    public void alterProvider(Provider obj) {
        try {
            String sql = "update tb_fornecedores set nome = ?, cnpj = ? , "
                    + "email = ? , telefone = ?"
                    + ", celular = ?, cep = ? , endereco = ?, numero = ? , "
                    + "complemento = ?, bairro = ?, cidade = ?, estado = ?"
                    + "where id = ?";

            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, obj.getNome());
            st.setString(2, obj.getCnpj());
            st.setString(3, obj.getEmail());
            st.setString(4, obj.getTelefone());
            st.setString(5, obj.getCelular());
            st.setString(6, obj.getCep());
            st.setString(7, obj.getEndereco());
            st.setInt(8, obj.getNumero());
            st.setString(9, obj.getComplemento());
            st.setString(10, obj.getBairro());
            st.setString(11, obj.getCidade());
            st.setString(12, obj.getUf());
            st.setInt(13, obj.getId());

            st.execute();
            st.close();

            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Erro: " + error);
        }
    }
    
    public List<Provider> toListProviders() {

        List<Provider> list = new ArrayList<>();
        String sql = "select * from tb_fornecedores";

        try {

            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Provider obj = new Provider();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
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
    
    public List<Provider> searchByName(String nome) {

        List<Provider> list = new ArrayList<>();
        String sql = "select * from tb_fornecedores where nome like ?";

        try {

            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, nome);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Provider obj = new Provider();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
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
    
    public Provider searchByNome2(String nome) {

        String sql = "select * from tb_fornecedores where nome = ? ";

        try {
            Provider obj = new Provider();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, nome);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
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
            JOptionPane.showMessageDialog(null, "Fornecedor não encontrado");
            return null;
        }

    }
}
