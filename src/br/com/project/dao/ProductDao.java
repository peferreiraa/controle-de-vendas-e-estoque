/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.project.dao;

import br.com.project.jdbc.ConnectionFactory;
import br.com.project.model.Product;
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
public class ProductDao {
    
    private final Connection conn;

    public ProductDao() {

        this.conn = new ConnectionFactory().getConnection();
    }
    
    public void registerProduct(Product obj){
        
        String sql = "insert into tb_produtos"
                + "(descricao, preco, qtd_estoque, for_id)"
                + "values(?, ?, ?, ?)";
        try{
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, obj.getDescricao());
            st.setDouble(2, obj.getPreco());
            st.setInt(3, obj.getQtdEstoque());
            st.setInt(4, obj.getFornecedor().getId());
            
            st.execute();
            st.close();
            
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso");
        }
        catch(SQLException error){
            JOptionPane.showMessageDialog(null, "Erro: " + error);
        }
    }
    
    public void alterProduct(Product obj){
        
        String sql = "update tb_produtos set "
                + "descricao = ? , preco = ? , qtd_estoque = ?, for_id = ? "
                + "where id = ? ";
        try{
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, obj.getDescricao());
            st.setDouble(2, obj.getPreco());
            st.setInt(3, obj.getQtdEstoque());
            st.setInt(4, obj.getFornecedor().getId());
            
            st.setInt(5, obj.getId());
            
            st.execute();
            st.close();
            
            JOptionPane.showMessageDialog(null, "Produto alterado com sucesso");
        }
        catch(SQLException error){
            JOptionPane.showMessageDialog(null, "Erro: " + error);
        }
    }
    
    
    public void deleteProduct(Product obj){
        String sql = "delete from tb_produtos where id = ?";
        
        try{
            PreparedStatement st = conn.prepareStatement(sql);
            
            st.setInt(1, obj.getId());
            
            st.execute();
            st.close();
            
            JOptionPane.showMessageDialog(null, "Produto excluido com sucesso");
        }
        catch(SQLException error){
            JOptionPane.showMessageDialog(null, "Erro: " + error);
        }
    }
    
    
    public List<Product> toListProduct(){
        
        List<Product> list = new ArrayList<>();
        String sql =  "select p.id, p.descricao, p.preco, p.qtd_estoque"
                + ", f.nome from tb_produtos as p inner join tb_fornecedores "
                + "as f on(p.for_id = f.id)";
        
        try{
           
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                Product obj = new Product();
                Provider provider = new Provider();
                
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtdEstoque(rs.getInt("p.qtd_estoque"));
                
                provider.setNome(rs.getString(("f.nome")));
                
                obj.setFornecedor(provider);
                
                list.add(obj);
            }
            
            return list;
            
        }
        catch(SQLException error){
            JOptionPane.showMessageDialog(null, "Erro: " + error);
            return null;
        }
    }
    
    public List<Product> searchProductByName(String nome){
        
        List<Product> list = new ArrayList<>();
        String sql =  "select p.id, p.descricao, p.preco, p.qtd_estoque"
                + ", f.nome from tb_produtos as p inner join tb_fornecedores "
                + "as f on(p.for_id = f.id) where p.descricao like ?";
        
        try{
           
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, nome);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                Product obj = new Product();
                Provider provider = new Provider();
                
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtdEstoque(rs.getInt("p.qtd_estoque"));
                
                provider.setNome(rs.getString(("f.nome")));
                
                obj.setFornecedor(provider);
                
                list.add(obj);
            }
            
            return list;
            
        }
        catch(SQLException error){
            JOptionPane.showMessageDialog(null, "Erro: " + error);
            return null;
        }
    }
    
    
    public Product searchProductByName2(String nome){
        
        List<Product> list = new ArrayList<>();
        String sql =  "select p.id, p.descricao, p.preco, p.qtd_estoque"
                + ", f.nome from tb_produtos as p inner join tb_fornecedores "
                + "as f on(p.for_id = f.id) where p.descricao = ?";
        
        try{
           
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, nome);
            
            ResultSet rs = st.executeQuery();
            Product obj = new Product();
            Provider provider = new Provider();
            
            if(rs.next()){
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtdEstoque(rs.getInt("p.qtd_estoque"));
                
                provider.setNome(rs.getString(("f.nome")));
                
                obj.setFornecedor(provider);
                
                
            }
            
           return obj;
            
        }
        catch(SQLException error){
            JOptionPane.showMessageDialog(null, "Não encontrado!");
            return null;
        }
    }
    
    
    public Product searchProductByCode(int code){
        
        List<Product> list = new ArrayList<>();
        String sql =  "select p.id, p.descricao, p.preco, p.qtd_estoque"
                + ", f.nome from tb_produtos as p inner join tb_fornecedores "
                + "as f on(p.for_id = f.id) where p.id = ?";
        try{
           
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, code);
            
            ResultSet rs = st.executeQuery();
            Product obj = new Product();
            Provider provider = new Provider();
            
            if(rs.next()){
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtdEstoque(rs.getInt("p.qtd_estoque"));
                
                provider.setNome(rs.getString(("f.nome")));
                
                obj.setFornecedor(provider);
                
                
            }
            
           return obj;
            
        }
        catch(SQLException error){
            JOptionPane.showMessageDialog(null, "Não encontrado!");
            return null;
        }
    }
    
    //Baixa no estoque
    public void lowStock(int id, int qtd){
        
        String sql = "update tb_produtos set qtd_estoque = ? where id = ?";
        
        try{
            PreparedStatement st = conn.prepareStatement(sql);
            
            st.setInt(1, qtd);
            st.setInt(2, id);
            
            st.execute();
            st.close();
        }
        catch(SQLException error){
            JOptionPane.showMessageDialog(null, "Erro: " + error);
        }
    }
    
    public int returnStock(int id){
        
        int qtdEstoque = 0;
        String sql = "Select qtd_estoque from tb_produtos where id = ?";
        
        try{
            PreparedStatement st = conn.prepareStatement(sql);
            
            st.setInt(1, id);
            
            ResultSet rs = st.executeQuery();
            
            if(rs.next()){
                Product product = new Product();
                
                qtdEstoque = (rs.getInt("qtd_estoque"));
            }
            
            return qtdEstoque;
        }
        catch(SQLException error){
            throw new RuntimeException(error);
        }
        
        
    }
    
    //Adicionar ao estoque
    
    public void addStock(int id, int qtdNova){
        
        String sql = "update tb_produtos set qtd_estoque = ? where id = ?";
        
        try{
            PreparedStatement st = conn.prepareStatement(sql);
            
            st.setInt(1, qtdNova);
            st.setInt(2, id);
            
            st.execute();
            st.close();
        }
        catch(SQLException error){
            JOptionPane.showMessageDialog(null, "Erro: " + error);
        }
    }
    
}
