/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.project.dao;

import br.com.project.jdbc.ConnectionFactory;
import br.com.project.model.Employee;
import br.com.project.view.FrmLogin;
import br.com.project.view.FrmMenu;
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
public class EmployeeDao {

    private final Connection conn;

    public EmployeeDao() {

        this.conn = new ConnectionFactory().getConnection();
    }

    public void registerEmployee(Employee obj) {

        try {
            String sql = "insert into tb_funcionarios(nome, rg, cpf, email,"
                    + "senha, cargo, nivel_acesso, telefone"
                    + ", celular, cep, endereco, numero, complemento, bairro, "
                    + "cidade, estado) "
                    + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, obj.getNome());
            st.setString(2, obj.getRg());
            st.setString(3, obj.getCpf());
            st.setString(4, obj.getEmail());
            st.setString(5, obj.getSenha());
            st.setString(6, obj.getCargo());
            st.setString(7, obj.getNivel_acesso());
            st.setString(8, obj.getTelefone());
            st.setString(9, obj.getCelular());
            st.setString(10, obj.getCep());
            st.setString(11, obj.getEndereco());
            st.setInt(12, obj.getNumero());
            st.setString(13, obj.getComplemento());
            st.setString(14, obj.getBairro());
            st.setString(15, obj.getCidade());
            st.setString(16, obj.getUf());

            st.execute();
            st.close();

            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Erro: " + error);
        }
    }

    public List<Employee> toListEmployees() {

        List<Employee> list = new ArrayList<>();
        String sql = "select * from tb_funcionarios";

        try {

            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Employee obj = new Employee();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));
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

    public void deleteEmployee(Employee obj) {
        try {
            String sql = "delete from tb_funcionarios where id = ?";

            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, obj.getId());

            st.execute();
            st.close();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Erro: " + error);
        }
    }

    public void alterEmployee(Employee obj) {
        try {
            String sql = "update tb_funcionarios set nome = ?, rg = ?, cpf = ?, "
                    + "email = ?, senha = ?, cargo = ?, nivel_acesso = ?  , telefone = ?"
                    + ", celular = ?, cep = ? , endereco = ?, numero = ? , "
                    + "complemento = ?, bairro = ?, cidade = ?, estado = ?"
                    + "where id = ?";

            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, obj.getNome());
            st.setString(2, obj.getRg());
            st.setString(3, obj.getCpf());
            st.setString(4, obj.getEmail());
            st.setString(5, obj.getSenha());
            st.setString(6, obj.getCargo());
            st.setString(7, obj.getNivel_acesso());
            st.setString(8, obj.getTelefone());
            st.setString(9, obj.getCelular());
            st.setString(10, obj.getCep());
            st.setString(11, obj.getEndereco());
            st.setInt(12, obj.getNumero());
            st.setString(13, obj.getComplemento());
            st.setString(14, obj.getBairro());
            st.setString(15, obj.getCidade());
            st.setString(16, obj.getUf());
            st.setInt(17, obj.getId());

            st.execute();
            st.close();

            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Erro: " + error);
        }
    }

    public List<Employee> searchEmployeeByName(String nome) {

        List<Employee> list = new ArrayList<>();
        String sql = "select * from tb_funcionarios where nome like ?";

        try {

            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, nome);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Employee obj = new Employee();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
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
    public Employee searchEmployeeByNome2(String nome) {

        String sql = "select * from tb_funcionarios where nome = ? ";

        try {
            Employee obj = new Employee();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, nome);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
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
            JOptionPane.showMessageDialog(null, "Funcionário não encontrado");
            return null;
        }

    }

    public void login(String email, String senha) {

        String sql = "select * from tb_funcionarios where email = ? and senha = ?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, email);
            st.setString(2, senha);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                //Logado
                
                if (rs.getString("nivel_acesso").equals("Admin")) {
                    JOptionPane.showMessageDialog(null, "Seja bem vindo");
                    FrmMenu view = new FrmMenu();
                    view.usarioLogado = rs.getString("nome");
                    view.setVisible(true);
                } 
                else if (rs.getString("nivel_acesso").equals("Usuário")) {
                    JOptionPane.showMessageDialog(null, "Seja bem vindo");
                    FrmMenu view = new FrmMenu();
                    view.usarioLogado = rs.getString("nome");
                    
                    //Desabilitar
                    view.menuPosicao.setVisible(false);
                    view.menuHistorico.setVisible(false);
                    view.menuFuncionario.setVisible(false);
                    view.menuFornecedores.setVisible(false);
                    
                    
                    view.setVisible(true);
                }

            } else {
                //Incorreto
                JOptionPane.showMessageDialog(null, "Usuário ou senha incorreto");
                new FrmLogin().setVisible(true);
            }
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Erro: " + error);
        }
    }
}
